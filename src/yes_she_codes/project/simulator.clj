(ns yes-she-codes.project.simulator
  (:require [schema.core :as s]
            [yes-she-codes.project.db.config :as db]
            [datomic.api :as d]
            [yes-she-codes.project.controller.compra :as controller.compra]
            [yes-she-codes.project.controller.cartao :as controller.cartao]
            [yes-she-codes.project.controller.cliente :as controller.cliente]))

(s/set-fn-validation! true)

(db/apagar-db!)
(def conn (db/criar-conexao!))
(db/criar-schema! conn)
(defn snapshot [] (d/db conn))

(controller.cliente/carrega-clientes-no-banco! "data/in/clientes.csv" conn)
(controller.cliente/lista-clientes! (snapshot))
(def id-cliente (:cliente/id (first (controller.cliente/lista-clientes! (snapshot)))))
(controller.cliente/cartoes-por-cliente! (snapshot) id-cliente)

(controller.cartao/carrega-cartoes-no-banco! "data/in/cartoes.csv" conn)
(controller.cartao/lista-cartoes! (snapshot))

(controller.compra/carrega-compras-no-banco! "data/in/compras.csv" conn)
(controller.compra/lista-compras! (snapshot))
(controller.compra/lista-compras-por-cartao! (snapshot) 3939393939393939)
(controller.compra/lista-gastos-por-categoria! (snapshot) 3939393939393939)

(time (controller.compra/lista-compras-por-cartao-mes! (snapshot) 3939393939393939 3))







