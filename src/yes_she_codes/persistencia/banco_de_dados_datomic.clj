(ns yes-she-codes.persistencia.banco-de-dados-datomic
  (:require [datomic.api :as d]
            [yes-she-codes.util :as y.util]
            [yes-she-codes.csv.leitor-csv :as y.csv]
            [yes-she-codes.dominio.compra :as y.compra]
            [java-time :as t]))

(def schema-datomic
  [{:db/ident       :compra/data
    :db/valueType   :db.type/instant
    :db/cardinality :db.cardinality/one
    :db/doc         "Data em que a compra foi realizada."}
   {:db/ident       :compra/valor
    :db/valueType   :db.type/bigdec
    :db/cardinality :db.cardinality/one
    :db/doc         "Valor total da compra."}
   {:db/ident       :compra/estabelecimento
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "Local onde a compra foi realizada."}
   {:db/ident       :compra/categoria
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "Grupo de gastos ao qual a compra pertence."}
   {:db/ident       :compra/cartao
    :db/valueType   :db.type/ref
    :db/cardinality :db.cardinality/one
    :db/doc         "Cartão utilizado na compra."}

   {:db/ident       :cartao/numero
    :db/valueType   :db.type/long
    :db/cardinality :db.cardinality/one
    :db/unique      :db.unique/identity
    :db/doc         "Número identificador do cartão."}
   {:db/ident       :cartao/cvv
    :db/valueType   :db.type/long
    :db/cardinality :db.cardinality/one
    :db/doc         "Número de segurança do cartão."}
   {:db/ident       :cartao/validade
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "Número de segurança do cartão."}
   {:db/ident       :cartao/limite
    :db/valueType   :db.type/bigdec
    :db/cardinality :db.cardinality/one
    :db/doc         "Valor máximo de compras para um cartão."}
   {:db/ident       :cartao/cliente
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "Dono do cartão."}])

(defn- db-url
  []
  "datomic:dev://localhost:4334/she-codes")

(defn cria-conexao
  []
  (d/create-database (db-url))
  (d/connect (db-url)))

(defn apaga-banco!
  []
  (d/delete-database (db-url)))

(defn cria-schema!
  [conn]
  @(d/transact conn schema-datomic))


(defn cartao->registro-datomic
  [cartao]
  (let [validade-str (.toString (:validade cartao))]
    (-> cartao
        (assoc :validade validade-str)
        (clojure.set/rename-keys {:id       :db/id
                                  :numero   :cartao/numero
                                  :cvv      :cartao/cvv
                                  :validade :cartao/validade
                                  :limite   :cartao/limite
                                  :cliente  :cartao/cliente}))))

(defn registro-datomic->cartao
  [registro]
  (-> registro
      (clojure.set/rename-keys {:db/id           :id
                                :cartao/numero   :numero
                                :cartao/cvv      :cvv
                                :cartao/validade :validade
                                :cartao/limite   :limite
                                :cartao/cliente  :cliente})))

(defn pesquisa-cartao
  [snapshot numero]
  (-> (d/q '[:find (pull ?id-cartao [*])
             :in $ ?numero
             :where [?id-cartao :cartao/numero ?numero]]
           snapshot numero)
      ffirst
      registro-datomic->cartao))

(defn salva-cartao!
  [conn cartao]
  (let [registro (cartao->registro-datomic cartao)]
    @(d/transact conn [registro])))

(defn lista-cartoes!
  [snapshot]
  (mapv registro-datomic->cartao
        (flatten (d/q '[:find (pull ?id-cartao [*])
                        :where [?id-cartao :cartao/numero]]
                      snapshot))))

(defn compra->registro-datomic
  [compra]
  (-> compra
      (clojure.set/rename-keys {:id              :db/id
                                :data            :compra/data
                                :valor           :compra/valor
                                :cartao          :compra/cartao
                                :categoria       :compra/categoria
                                :estabelecimento :compra/estabelecimento})
      y.util/converte-java-time-para-date))

(defn registro-datomic->compra
  [registro]
  (-> registro
      (clojure.set/rename-keys {:db/id                  :id
                                :compra/data            :data
                                :compra/valor           :valor
                                :compra/cartao          :cartao
                                :compra/categoria       :categoria
                                :compra/estabelecimento :estabelecimento})
      y.util/converte-date-para-java-time))

(defn salva-compra!
  [conn compra]
  (some->> (:cartao compra)
           (pesquisa-cartao (d/db conn))
           :id
           (assoc compra :cartao)
           (compra->registro-datomic)
           (conj [])
           (d/transact conn)
           deref))

(defn lista-compras!
  [snapshot]
  (vec (map registro-datomic->compra
            (flatten (d/q '[:find (pull ?compra [*])
                            :where [?compra :compra/valor]]
                          snapshot)))))

(defn carrega-compras-no-banco!
  [conn]
  (mapv
    (partial salva-compra! conn)
    (y.csv/processa-arquivo-de-compras!)))

(defn carrega-cartoes-no-banco!
  [conn]
  (mapv
    (partial salva-cartao! conn)
    (y.csv/processa-arquivo-de-cartoes!)))

(defn lista-compras-por-cartao!
  [snapshot cartao]
  (->> (d/q '[:find (pull ?id-compra [*])
              :in $ ?cartao
              :where [?id-compra :compra/cartao ?cartao]]
            snapshot cartao)
       flatten
       (map registro-datomic->compra)
       vec))

(defn filtro-mes
  [data mes]
  (-> (y.util/mes-da-data data)
      (= mes)))

(defn lista-compras-por-cartao-e-mes
  [snapshot cartao mes]
  (-> '[:find (pull ?id-compra [*])
        :in $ ?cartao ?mes
        :where [?id-compra :compra/cartao ?cartao]
        [?id-compra :compra/data ?data]
        [(yes-she-codes.persistencia.banco-de-dados-datomic/filtro-mes ?data ?mes)]]
      (d/q snapshot [:cartao/numero cartao] mes)
      flatten
      (#(mapv registro-datomic->compra %))))

(def lista-gastos-por-categoria!
  (comp (partial group-by :categoria) lista-compras!))

(defn carrega-banco-de-dados!
  []
  (apaga-banco!)
  (let [conexao (cria-conexao)]
    (cria-schema! conexao)
    (carrega-cartoes-no-banco! conexao)
    (carrega-compras-no-banco! conexao)))