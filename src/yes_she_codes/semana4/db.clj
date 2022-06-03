(ns yes_she_codes.semana4.db
  (:use clojure.pprint)
  (:require [datomic.api :as d]))

(def db-uri "datomic:dev://localhost:4334/she-codes")

(defn cria-conexão []
  (d/create-database db-uri)
  (d/connect db-uri))

(def CompraSchema [{:db/ident       :compra/slug
                    :db/valueType   :db.type/string
                    :db/cardinality :db.cardinality/one
                    :db/doc         "O caminho para acessar essa compra via http"}
                   {:db/ident       :compra/data
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
                    :db/doc         "O estabelecimento de uma compra"}])

(defn cria-esquema [conn]
  (d/transact conn CompraSchema))