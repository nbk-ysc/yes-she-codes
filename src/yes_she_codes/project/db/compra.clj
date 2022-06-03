(ns yes-she-codes.project.db.compra
  [:require [datomic.api :as d]
            [schema.core :as s]
            [yes-she-codes.project.model.cartao :as model.cartao]])

(s/defn lista-compras
  [db]
  (d/q
    '[:find [(pull ?db_id [* {:compra/cartao [* {:cartao/cliente [*]}]}]) ...]
      :where [?db_id :compra/id]]
    db))

(s/defn salva-compra!
  [conn compra-com-cartao-vinculado]
  (d/transact conn [compra-com-cartao-vinculado]))

(s/defn lista-compras-por-cartao
  [db numero-cartao :- model.cartao/NumeroCartao]
  (d/q
    '[:find [(pull ?db_id [* {:compra/cartao [* {:cartao/cliente [*]}]}]) ...]
      :in $ ?num-cartao
      :where [?cartao :cartao/numero ?num-cartao]
      [?db_id :compra/cartao ?cartao]]
    db numero-cartao))

(s/defn lista-compras-por-cartao-mes
  [db numero-cartao mes]
  (d/q
    '[:find [(pull ?db_id [* {:compra/cartao [* {:cartao/cliente [*]}]}]) ...]
      :in $ ?num-cartao ?mes-match
      :where [?cartao :cartao/numero ?num-cartao]
             [?db_id :compra/cartao ?cartao]
             [?db_id :compra/data ?data]
             [((fn [dt] (inc (.getMonth dt))) ?data) ?mes]
             [(= ?mes ?mes-match)]]
    db numero-cartao mes))