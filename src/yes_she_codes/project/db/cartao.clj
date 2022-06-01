(ns yes-she-codes.project.db.cartao
  [:require [datomic.api :as d]
            [schema.core :as s]
            [yes-she-codes.project.adapter.cartao :as adapter.cartao]
            [yes-she-codes.project.model.cartao :as model.cartao]])

(s/defn lista-cartoes! :- [model.cartao/Cartao]
  [db]
  (let [datomic-obj (d/q '[:find [(pull ?db_id [*]) ...]
                           :where [?db_id :cartao/id]]
                         db)]
    (mapv adapter.cartao/datomic->cartao datomic-obj)))

(s/defn salva-cartao!
  [conn
   cartao :- model.cartao/Cartao]
  (d/transact conn [(adapter.cartao/cartao->datomic cartao)]))

(s/defn carrega-cartoes-no-banco!
  [conn
   cartoes :- [model.cartao/Cartao]]
  (mapv (partial salva-cartao! conn) cartoes))

(s/defn cartao-por-numero!
  [db
   numero-cartao :- model.cartao/NumeroCartao]
  (d/q '[:find (pull ?db_id [*]) .
         :in $ ?numero-cartao
         :where [?db_id :cartao/numero ?numero-cartao]] db numero-cartao)
  )
