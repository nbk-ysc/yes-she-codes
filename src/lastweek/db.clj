(ns lastweek.db
  (:use clojure.pprint)
  (:require [datomic.api :as d]))

(def she-codes "datomic:dev://localhost:4334/yes-she-codes")

(defn cria-conexao []
  (d/create-database she-codes)
  (d/connect she-codes))

(defn apaga-banco []
  (d/delete-database she-codes))

(def schema-datomic [{:db/ident       :compra/data
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "A data de uma compra"}
                     {:db/ident       :compra/valor
                      :db/valueType   :db.type/bigdec
                      :db/cardinality :db.cardinality/one
                      :db/doc         "O valor de uma compra"}
                     {:db/ident       :compra/estabelecimento
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "O estabelecimento de uma compra"}
                     {:db/ident       :compra/categoria
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "A categoria de uma compra"}
                     {:db/ident       :compra/cartao
                      :db/valueType   :db.type/long
                      :db/cardinality :db.cardinality/one
                      :db/doc         "O cartao de uma compra"}])

(defn cria-schema [conn]
  (d/transact conn schema-datomic))
