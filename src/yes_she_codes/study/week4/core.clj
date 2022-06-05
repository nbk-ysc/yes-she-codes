(ns yes-she-codes.study.week4.core
  (:require [datomic.api :as d]
            [yes-she-codes.study.week4.adapter.compra :as adapter.compra]
            [yes-she-codes.study.week4.db.db :as db]
            [schema.core :as s])
  (:use clojure.pprint))

(s/set-fn-validation! false)

(db/apagar-db!)
(def conn (db/criar-conexao!))
(db/criar-schema! conn)
(defn snapshot [] (d/db conn))

(def compras (adapter.compra/csv->compra "data/in/compras.csv"))

(db/carrega-compras-no-banco! conn compras)
(db/lista-compras! (snapshot))
(db/lista-compras-por-cartao! (snapshot) 3939393939393939 )
(db/lista-compras-por-cartao-mes! (snapshot) 3939393939393939 (.getMonth (java-time/local-date "2022-04-10")))