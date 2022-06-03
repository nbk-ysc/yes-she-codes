(ns yes-she-codes.project.db.cartao
  [:require [datomic.api :as d]
            [schema.core :as s]
            [yes-she-codes.project.model.cartao :as model.cartao]])

(s/defn lista-cartoes
  [db]
  (d/q
    '[:find [(pull ?db_id [* {:cartao/cliente [*]}]) ...]
      :where [?db_id :cartao/id]]
    db))

(s/defn salva-cartao!
  [conn cartao-com-cliente-vinculado]
  (d/transact conn [cartao-com-cliente-vinculado]))

(s/defn cartao-por-numero
  [db
   numero-cartao :- model.cartao/NumeroCartao]
  (d/q
    '[:find (pull ?db_id [*]) .
      :in $ ?numero-cartao
      :where [?db_id :cartao/numero ?numero-cartao]] db numero-cartao))
