(ns yes-she-codes.project.db.cliente
  [:require [datomic.api :as d]
            [schema.core :as s]
            [yes-she-codes.project.model.cliente :as model.cliente]
            [yes-she-codes.project.adapter.cliente :as adapter.cliente]])

(s/defn lista-clientes! :- [model.cliente/Cliente]
  [db]
  (let [datomic-obj (d/q '[:find [(pull ?db_id [*]) ...]
                           :where [?db_id :cliente/id]]
                         db)]
    (mapv adapter.cliente/datomic->cliente datomic-obj)))

(s/defn salva-cliente!
        [conn
         cliente :- model.cliente/Cliente]
  (d/transact conn [(adapter.cliente/cliente->datomic cliente)]))

(s/defn carrega-clientes-no-banco!
        [conn
         clientes :- [model.cliente/Cliente]]
        (mapv (partial salva-cliente! conn) clientes))