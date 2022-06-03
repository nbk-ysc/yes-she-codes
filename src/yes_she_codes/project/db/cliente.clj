(ns yes-she-codes.project.db.cliente
  [:require [datomic.api :as d]
            [schema.core :as s]
            [yes-she-codes.project.model.cliente :as model.cliente]])

(s/defn lista-clientes
  [db]
  (d/q
    '[:find [(pull ?db_id [*]) ...]
      :where [?db_id :cliente/id]]
    db))

(s/defn salva-cliente!
  [conn cliente-datomic]
  (d/transact conn [cliente-datomic]))

(s/defn cliente-por-cpf!
  [db cpf :- model.cliente/Cpf]
  (d/q
    '[:find (pull ?db_id [*]) .
      :in $ ?cpf
      :where [?db_id :cliente/cpf ?cpf]] db cpf))

(s/defn cliente-por-id!
  [db id-cliente]
  (d/pull db '[*] [:cliente/id id-cliente]))

(s/defn cartoes-por-cliente!
  [db id-cliente]
  (d/q
    '[:find [(pull ?cartao [*]) ...]
      :in $ ?id-cliente
      :where [?cartao :cartao/cliente ?cliente]
             [?cliente :cliente/id ?id-cliente]
             [?cartao :cartao/numero ?num-cartao]] db id-cliente))
