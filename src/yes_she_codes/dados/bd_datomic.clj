(ns yes-she-codes.dados.bd-datomic
  (:require [datomic.api :as d]
            [yes-she-codes.util :as y.util]
            [yes-she-codes.csv.processa-csv :as y.csv])
  (:use clojure.pprint))

(def schema [{:db/ident       :compra/data
              :db/valueType   :db.type/instant
              :db/cardinality :db.cardinality/one
              :db/doc         "A data em que uma compra foi realizada"}
             {:db/ident       :compra/valor
              :db/valueType   :db.type/bigdec
              :db/cardinality :db.cardinality/one
              :db/doc         "O valor da compra"}
             {:db/ident       :compra/estabelecimento
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              :db/doc         "Local da compra"}
             {:db/ident       :compra/categoria
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              :db/doc         "Tipo da compra"}
             {:db/ident       :compra/cartao
              :db/valueType   :db.type/ref
              :db/cardinality :db.cardinality/one
              :db/doc         "Cartão utilizado na compra"}


             {:db/ident       :cartao/numero
              :db/valueType   :db.type/long
              :db/cardinality :db.cardinality/one
              :db/unique      :db.unique/identity
              :db/doc         "Número do cartão"}
             {:db/ident       :cartao/cvv
              :db/valueType   :db.type/long
              :db/cardinality :db.cardinality/one
              :db/doc         "Código de segurança do cartão"}
             {:db/ident       :cartao/validade
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              :db/doc         "Validade do cartão"}
             {:db/ident       :cartao/limite
              :db/valueType   :db.type/bigdec
              :db/cardinality :db.cardinality/one
              :db/doc         "Limite do cartão"}
             {:db/ident       :cartao/cliente
              :db/valueType   :db.type/string
              :db/cardinality :db.cardinality/one
              :db/doc         "Dono do cartão"}])

(def db-url "datomic:dev://localhost:4334/yes-she-codes")

(defn abre-conexao []
  (d/create-database db-url)
  (d/connect db-url))

(defn cria-schema [conn]
  (d/transact conn schema))

(defn apaga-banco []
  (d/delete-database db-url))

(def conn (abre-conexao))

(defn compra->registro-datomic [compra]
  (-> compra
      (clojure.set/rename-keys {:id              :db/id
                                :data            :compra/data
                                :valor           :compra/valor
                                :estabelecimento :compra/estabelecimento
                                :categoria       :compra/categoria
                                :cartao          :compra/cartao})
      (dissoc :id)
      y.util/converte-java-time-para-date))

(defn registro-datomic->compra [registro]
  (-> registro
      (clojure.set/rename-keys {:db/id                  :id
                                :compra/data            :data
                                :compra/valor           :valor
                                :compra/estabelecimento :estabelecimento
                                :compra/categoria       :categoria
                                :compra/cartao          :cartao})
      y.util/converte-date-para-java-time))


(defn cartao->registro-datomic [cartao]
  (-> cartao
      (assoc :validade (.toString (:validade cartao)))
      (clojure.set/rename-keys {:id       :db/id
                                :numero   :cartao/numero
                                :cvv      :cartao/cvv
                                :validade :cartao/validade
                                :limite   :cartao/limite
                                :cliente  :cartao/cliente})
      (dissoc :id)))

(defn registro-datomic->cartao [registro]
  (-> registro
      (clojure.set/rename-keys {:db/id           :id
                                :cartao/numero   :numero
                                :cartao/cvv      :cvv
                                :cartao/validade :validade
                                :cartao/limite   :limite
                                :cartao/cliente  :cliente})))

; sem relacionar com o cartão
(defn antigo-salva-compra! [conn, compra]
  (let [registro (compra->registro-datomic compra)]
    (d/transact conn [registro])))

(defn pesquisa-cartao [snapshot numero]
  (-> (d/pull snapshot '[*] [:cartao/numero numero])
      registro-datomic->cartao))

(defn salva-compra! [conn compra]
  (some->> (:cartao compra)
           (pesquisa-cartao (d/db conn))
           :id
           (assoc compra :cartao)                           ; relaciona compra com ID do cartão
           (compra->registro-datomic)
           (conj [])
           (d/transact conn)
           deref))

(defn salva-cartao! [conn, cartao]
  (let [registro (cartao->registro-datomic cartao)]
    (d/transact conn [registro])))

(defn carrega-compras-no-banco! [conn]
  (map (partial salva-compra! conn)
       (y.csv/processa-arquivo-de-compras!)))

(defn carrega-cartoes-no-banco! [conn]
  (map (partial salva-cartao! conn)
       (y.csv/processa-arquivo-de-cartoes!)))

(defn lista-compras! [snapshot]
  (vec (map registro-datomic->compra
            (flatten (d/q '[:find (pull ?compra [*])
                            :where [?compra :compra/valor]]
                          snapshot)))))

(defn lista-cartoes! [snapshot]
  (vec (map registro-datomic->cartao
            (flatten (d/q '[:find (pull ?cartoes [*])
                            :where [?cartoes :cartao/numero]]
                          snapshot)))))

; n serve mais pq agora compra/cartao n é o número em si
; e sim, a referência do db/ib do cartao
(defn antigo-lista-compras-por-cartao! [snapshot, cartao]
  (vec (map registro-datomic->compra
            (flatten (d/q '[:find (pull ?compra [*])
                            :in $ ?cartao
                            :where [?compra :compra/cartao ?cartao]]
                          snapshot, cartao)))))

(defn retorna-id-do-cartao [snapshot cartao]
  (->> cartao
       (pesquisa-cartao snapshot)
       :id))

(defn lista-compras-por-cartao! [snapshot, cartao]
  (vec (map registro-datomic->compra
            (flatten (d/q '[:find (pull ?compra [*])
                            :in $ ?cartao
                            :where [?compra :compra/cartao ?cartao]]
                          snapshot, (retorna-id-do-cartao snapshot cartao))))))


; (defn lista-compras-por-cartao-e-mes! [snapshot, cartao, mes]
; (vec (map registro-datomic->compra
; (flatten (d/q '[:find (pull ?compra [*])
;              :in $ ?cartao ?mes
;               :where [?compra :compra/cartao ?cartao]
;               ; converter a data da compra para java-time (y.util/converte-date-para-java-time)
;               ; pegar o mês (y.util/mes-da-data) e comparar com o mes?
;               ]
;             snapshot, cartao, mes)) ) ) )

; (defn lista-gastos-por-categoria!)

