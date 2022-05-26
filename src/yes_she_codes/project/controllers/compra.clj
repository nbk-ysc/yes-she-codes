(ns yes-she-codes.project.controllers.compra
  (:require [schema.core :as s]
            [yes-she-codes.project.model.compra :as model.compra]
            [yes-she-codes.project.logic.common.common :as logic.common]))

(s/defn insere-compra! :- model.compra/Compras
  [compras :- model.compra/Compras
   record :- model.compra/Compra]
  (swap! compras logic.common/insere-record record))

(s/defn lista-compras!
  [compras :- model.compra/Compras]
  (logic.common/lista-entidade @compras))

(s/defn pesquisa-compra-por-id! :- model.compra/Compra
  [compras :- model.compra/Compras
   id :- s/Num]
  (logic.common/pesquisa-record-por-id @compras id))

(s/defn exclui-compra! :- model.compra/Compras
  [compras :- model.compra/Compras
   id :- s/Num]
  (swap! compras logic.common/exclui-record id))
