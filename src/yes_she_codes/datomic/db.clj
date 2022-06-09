(ns yes-she-codes.datomic.db
  (:require [datomic.api :as d]))

(def db-uri "datomic:dev://localhost:4334/she-codes")

(defn cria-conexao []
  (d/create-database db-uri)
  (d/connect db-uri))

(def schema-datomic [{:db/ident       :compra/data
                      :db/valueType   :db.type/instant
                      :db/cardinality :db.cardinality/one
                      }
                     {:db/ident       :compra/valor
                      :db/valueType   :db.type/bigdec
                      :db/cardinality :db.cardinality/one
                      }
                     {:db/ident       :compra/estabelecimento
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      }
                     {:db/ident       :compra/categoria
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      }
                     {:db/ident       :compra/cartao
                      :db/valueType   :db.type/long
                      :db/cardinality :db.cardinality/one
                      }])

(defn cria-schema [conn]
  (d/transact conn schema-datomic))

(defn apaga-banco []
  (d/delete-database db-uri))

(apaga-banco)


