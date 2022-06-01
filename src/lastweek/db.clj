(ns lastweek.db
  (:use clojure.pprint)
  (:require [datomic.api :as d]))

(def db-uri "datomic:dev://localhost:4334/yes-she-codes")

(defn abre-conexao []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn apaga-banco []
  (d/delete-database db-uri))