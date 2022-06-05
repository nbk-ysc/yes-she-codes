(ns yes-she-codes.study.week4.db.db
  [:require [datomic.api :as d]
            [schema.core :as s]
            [yes-she-codes.study.week4.model.compra :as model.compra]]
  (:import [java.time LocalDate Instant ZoneOffset]
           [java.util Date]))

(def db-uri "datomic:dev://localhost:4334/she-codes")

(defn criar-conexao!
  []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn apagar-db!
  []
  (d/delete-database db-uri))


(s/defschema schema-datomic [;;; COMPRA
                             {:db/ident       :compra/id
                              :db/valueType   :db.type/uuid
                              :db/cardinality :db.cardinality/one
                              :db/unique      :db.unique/identity
                              :db/doc         "id unico que identifica a compra"}
                             {:db/ident       :compra/data
                              :db/valueType   :db.type/instant
                              :db/cardinality :db.cardinality/one
                              :db/doc         "instant inicial da data em que foi realizada a compra"}
                             {:db/ident       :compra/valor
                              :db/valueType   :db.type/bigdec
                              :db/cardinality :db.cardinality/one
                              :db/doc         "o preço da compra com precisão monetaria"}
                             {:db/ident       :compra/estabelecimento
                              :db/valueType   :db.type/string
                              :db/cardinality :db.cardinality/one
                              :db/doc         "nome do estabelecimento onde foi feito a compra"}
                             {:db/ident       :compra/categoria
                              :db/valueType   :db.type/string
                              :db/cardinality :db.cardinality/one
                              :db/doc         "categoria a qual a compra pertence"}
                             {:db/ident       :compra/cartao
                              :db/valueType   :db.type/long
                              :db/cardinality :db.cardinality/one
                              :db/doc         "numero do cartão em que foi realizada a compra"}])


(defn local-date->inst
  [local-date]
  (-> local-date
      .atStartOfDay
      (.toInstant ZoneOffset/UTC)
      .toEpochMilli
      Date.))


(defn inst->local-date
  [inst]
  (-> (.getTime inst)
      Instant/ofEpochMilli
      (LocalDate/ofInstant ZoneOffset/UTC)))


(defn compra->datomic
  [{:compra/keys [data] :as compra}]
  (assoc compra :compra/data (local-date->inst data)))

(defn datomic->compra
  [{:compra/keys [data] :as compra}]
  (-> (assoc compra :compra/data (inst->local-date data))
      (dissoc compra :db/id)))


(defn criar-schema!
  [conn]
  (d/transact conn schema-datomic))


(s/defn lista-compras! :- [model.compra/Compra]
  [db]
  (let [datomic-obj (d/q '[:find [(pull ?db_id [*]) ...]
                           :where [?db_id :compra/id]]
                         db)]
    (map datomic->compra datomic-obj)))


(s/defn salva-compra!
  [conn
   compra :- model.compra/Compra]
  (d/transact conn [(compra->datomic compra)]))


(s/defn carrega-compras-no-banco!
  [conn
   compras :- [model.compra/Compra]]
  (mapv (partial salva-compra! conn) compras))


(s/defn lista-compras-por-cartao!
  [db
   numero-cartao :- Long]
  (let [datomic-obj (d/q '[:find [(pull ?db_id [*]) ...]
                           :in $ ?cartao
                           :where [?db_id :compra/cartao ?cartao]]
                         db numero-cartao)]
    (map datomic->compra datomic-obj)))











