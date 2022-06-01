(ns yes-she-codes.project.db.compra
  [:require [datomic.api :as d]
            [schema.core :as s]
            [yes-she-codes.project.model.compra :as model.compra]
            [yes-she-codes.project.adapter.compra :as adapter.compra]
            [yes-she-codes.project.db.cartao :as db.cartao]
            [yes-she-codes.project.model.cartao :as model.cartao]
            [yes-she-codes.project.logic.compra :as logic.compra]]
  (:import (java.time Month)))


(s/defn lista-compras! :- [model.compra/Compra]
        [db]
        (let [datomic-obj (d/q '[:find [(pull ?db_id [* {:compra/cartao [*]}]) ...]
                                 :where [?db_id :compra/id]]
                               db)]
          (mapv adapter.compra/datomic->compra datomic-obj)))

(s/defn salva-compra!
  [conn
   compra :- model.compra/Compra]
  (if-let [cartao-vinculado
           (db.cartao/cartao-por-numero!
             (d/db conn) (:compra/cartao compra))]
    (d/transact conn
                [(assoc
                   (adapter.compra/compra->datomic compra)
                   :compra/cartao
                   cartao-vinculado)])))

(s/defn carrega-compras-no-banco!
  [conn
   compras :- [model.compra/Compra]]
  (mapv (partial salva-compra! conn) compras))

(s/defn lista-compras-por-cartao!
  [db
   numero-cartao :- model.cartao/NumeroCartao]
  (let [datomic-obj (d/q '[:find [(pull ?db_id [* {:compra/cartao [*]}]) ...]
                           :in $ ?num-cartao
                           :where
                           [?db_id :compra/cartao ?cartao]
                           [?cartao :cartao/numero ?num-cartao]]
                         db numero-cartao)]
    (mapv adapter.compra/datomic->compra datomic-obj)))

(s/defn lista-compras-por-cartao-mes!
  [db
   numero-cartao :- model.cartao/NumeroCartao
   mes :- Month]
  (->> numero-cartao
       (lista-compras-por-cartao! db)
       (logic.compra/lista-de-compras-do-mes mes)))


(s/defn lista-gastos-por-categoria!
  [db
   numero-cartao :- model.cartao/NumeroCartao]
  (->> numero-cartao
       (lista-compras-por-cartao! db)
       (group-by :compra/categoria)))
