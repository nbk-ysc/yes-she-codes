(ns yes-she-codes.persistencia.db
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [yes-she-codes.logic.util :as y.util]
            [yes-she-codes.csv.leitor-csv :as y.csv]
            [yes-she-codes.dominio.compra :as y.compra]
            [java-time :as time]))

(def db-url "datomic:dev://localhost:4334/she-codes")

(defn cria-conexao []
  (d/create-database db-url)
  (d/connect db-url))

(defn apaga-banco []
  (d/delete-database db-url))

;date, bigdec,string,string,numerico
;(data, valor, estabelecimento, categoria e cartao)
(def schema-datomic [{:db/ident       :compra/data
                      :db/valueType   :db.type/instant
                      :db/cardinality :db.cardinality/one
                      :db/doc         "data de compra de um cartão"}
                     {:db/ident       :compra/slug
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "O caminho para acessar esse produto via http"}
                     {:db/ident       :compra/valor
                      :db/valueType   :db.type/bigdec
                      :db/cardinality :db.cardinality/one
                      :db/doc         "O valor de uma compra com precisão monetária"}
                     {:db/ident       :compra/estabelecimento
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "O nome do estabelecimento em que foi feita a compra"}
                     {:db/ident       :compra/categoria
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "A categoria em que foi feita a compra"}
                     {:db/ident       :compra/cartao
                      :db/valueType   :db.type/ref          ;era long mas está referenciando ao esquema :/cartao
                      :db/cardinality :db.cardinality/one
                      :db/doc         "O numero de cartão com que foi feita a compra"}

                     {:db/ident       :cartao/numero
                      :db/unique      :db.unique/identity
                      :db/valueType   :db.type/long
                      :db/cardinality :db.cardinality/one
                      :db/doc         "O numero de cartão"}
                     {:db/ident       :cartao/cvv
                      :db/valueType   :db.type/long
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Numero cvv do cartao"}
                     {:db/ident       :cartao/validade
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Mes e ano de validade do cartao"}
                     {:db/ident       :cartao/limite
                      :db/valueType   :db.type/bigdec
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Valor limite do cartao"}
                     {:db/ident       :cartao/cliente
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "O numero de cpf do cliente"}

                     ])

(defn cria-schema [conn]
  (d/transact conn schema-datomic))

(defn- compra-a-registro-datomic [compra]
  (-> compra
      (clojure.set/rename-keys {:id              :db/id
                                :data            :compra/data
                                :valor           :compra/valor
                                :estabelecimento :compra/estabelecimento
                                :categoria       :compra/categoria
                                :cartao          :compra/cartao})
      (dissoc :id)
      y.util/converte-java-time-a-date))

(defn- cartao-a-registro-datomic [cartao]
  (-> cartao
      (assoc :validade (.toString (:validade cartao)))
      (clojure.set/rename-keys {:numero   :cartao/numero
                                :cvv      :cartao/cvv
                                :validade :cartao/validade
                                :limite   :cartao/limite
                                :cliente  :cartao/cliente})
      (dissoc :id)))

(defn- registro-datomic-a-compra [registro]
  (-> registro
      (clojure.set/rename-keys {:db/id                  :id
                                :compra/data            :data
                                :compra/valor           :valor
                                :compra/estabelecimento :estabelecimento
                                :compra/categoria       :categoria
                                :compra/cartao          :cartao})
      y.util/converte-date-a-java-time))

(defn- registro-datomic-a-cartao [registro]
  (-> registro
      (clojure.set/rename-keys {:db/id           :id
                                :cartao/numero   :numero
                                :cartao/cvv      :cvv
                                :cartao/validade :validade
                                :cartao/limite   :limite
                                :cartao/cliente  :cliente})))


(defn salva-compra [conn compra]
  (let [registro (compra-a-registro-datomic compra)]
    (d/transact conn [registro])))

(defn pesquisa-numero-cartao [conn cartao]
  (->> (d/q '[:find (pull ?id-cartao [*])
              :in $ ?cartao-query
              :where [?id-cartao :cartao/numero ?cartao-query]
              ] conn cartao)
        ffirst
       (registro-datomic-a-cartao))
  )
(defn salva-compra [conn compra]
  (let [numero-cartao (pesquisa-numero-cartao (d/db conn) (:cartao compra))
        registro (compra-a-registro-datomic compra)
        registro (assoc registro :compra/cartao (:id numero-cartao))]
    (d/transact conn [registro])))


(defn salva-cartao [conn cartao]
  (let [registro (cartao-a-registro-datomic cartao)]
    (d/transact conn [registro])))

(defn lista-compras [conn]
  (->> (d/q '[:find (pull ?entidade [*])
              :where [?entidade :compra/valor]] conn)
       flatten
       (map registro-datomic-a-compra)
       vec))

(defn carrega-compras-no-banco [conn]
  (map (partial salva-compra conn)
       (y.csv/processa-arquivo-de-compras!)))
(defn carrega-cartoes-no-banco [conn]
  (map (partial salva-cartao conn)
       (y.csv/processa-arquivo-de-cartoes!)))

(defn lista-cartoes [conn]
  (->> (d/q '[:find (pull ?entidade [*])
              :where [?entidade :cartao/numero]] conn)
       flatten
       (map registro-datomic-a-cartao)
       vec))

(defn lista-compras-por-cartao [conn cartao]
  (->> (d/q '[:find (pull ?id-compra [*])
              :in $ ?cartao-query
              :where [?id-compra :compra/cartao ?cartao-query]]
            conn cartao)
       flatten
       (map registro-datomic-a-compra)
       vec))

(defn converte-data-a-mes [data mes]
  (= mes (inc (.getMonth data)))
  ;(->> data
  ;     (y.util/converte-date-a-java-time)
  ;     (time/month)
  ;     (.getValue)
  ;     (= mes))
  )

(defn lista-compras-por-cartao-e-mes [conn cartao mes]
  (->> (d/q '[:find [(pull ?id-compra [*])]
              :in $ ?cartao-query ?mes-query
              :where [?id-compra :compra/cartao ?cartao-query]
              [?id-compra :compra/data ?data-query]
              ;não sei o porque não reconhecia minha função e coloquei o nome do arquivo tdo :(
              [(yes-she-codes.persistencia.db/converte-data-a-mes ?data-query ?mes-query)]]
            conn cartao mes)
       flatten
       (map registro-datomic-a-compra)
       vec)
  )


(defn lista-compras-por-categoria [conn]
  ((comp y.compra/agrupa-gastos-por-categoria lista-compras) conn)
  )



