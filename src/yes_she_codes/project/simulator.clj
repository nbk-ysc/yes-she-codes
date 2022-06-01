(ns yes-she-codes.project.simulator
  (:require [schema.core :as s]
            [yes-she-codes.project.db.config :as db]
            [yes-she-codes.project.db.compra :as db.compra]
            [yes-she-codes.project.db.cartao :as db.cartao]
            [yes-she-codes.project.db.cliente :as db.cliente]
            [datomic.api :as d]
            [yes-she-codes.project.diplomat.compra :as diplomat.compra]
            [yes-she-codes.project.diplomat.cartao :as diplomat.cartao]
            [yes-she-codes.project.diplomat.cliente :as diplomat.cliente]
            [yes-she-codes.project.controllers.compra :as controllers.compra]
            )
  (:use (clojure.pprint)))


(s/set-fn-validation! true)
;(s/set-fn-validation! false)


;;;;;; ÁTOMO

(def compras-dominio (atom []))
(diplomat.compra/insere-compras-csv-no-dominio "data/in/compras.csv" compras-dominio)
(deref compras-dominio)



;;;;;; BASE DE DADOS

(db/apagar-db!)
(def conn (db/criar-conexao!))
(db/criar-schema! conn)
(defn snapshot [] (d/db conn))


(diplomat.cliente/insere-clientes-csv-na-base "data/in/clientes.csv" conn)
(db.cliente/lista-clientes! (snapshot))


(diplomat.cartao/insere-cartoes-csv-na-base "data/in/cartoes.csv" conn)
(db.cartao/lista-cartoes! (snapshot))
(db.cartao/cartao-por-numero! (snapshot) 3939393939393939)



(diplomat.compra/insere-compras-csv-na-base "data/in/compras.csv" conn)
(db.compra/lista-compras! (snapshot))
(db.compra/lista-compras-por-cartao! (snapshot) 3939393939393939)


(db.compra/lista-compras-por-cartao-mes! (snapshot) 3939393939393939 (.getMonth (java-time/local-date "2021-03-01")))
(db.compra/lista-gastos-por-categoria! (snapshot) 3939393939393939)


