(ns yes-she-codes.semana4.main
  (:use [clojure pprint])
  (:require [datomic.api :as d]
            [schema.core :as s]
            [yes-she-codes.semana4.db.config :as db.config]
            [yes-she-codes.semana4.db.cartao :as db.cartao]
            [yes-she-codes.semana4.db.compra :as db.compra]))

(s/set-fn-validation! true)

(db.config/apaga-banco!)
(def conn (db.config/abre-conexao!))
(db.config/cria-schema! conn)

(db.cartao/carrega! conn "/Users/bruna.soares/Documents/projects/yes-she-codes-arquivos/cartoes.csv")
(pprint (db.cartao/lista (d/db conn)))
(println "-------------------------------------------------------------")

(db.compra/carrega! conn "/Users/bruna.soares/Documents/projects/yes-she-codes-arquivos/compras.csv")
(pprint (db.compra/lista (d/db conn)))
(println "-------------------------------------------------------------")

(pprint (db.compra/lista-por-cartao (d/db conn) 1234123412341234))
(println "-------------------------------------------------------------")

(pprint (db.compra/lista-por-cartao (d/db conn) 1234123412341234 "01"))
(println "-------------------------------------------------------------")

(pprint (db.compra/lista-por-categoria (d/db conn) 6655665566556655))

