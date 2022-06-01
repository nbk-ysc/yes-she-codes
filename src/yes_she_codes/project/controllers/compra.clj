(ns yes-she-codes.project.controllers.compra
  (:require [schema.core :as s]
            [yes-she-codes.project.model.compra :as model.compra]
            [yes-she-codes.project.logic.common.common :as logic.common])
  (:import (clojure.lang Atom)))

;;; controller --> o dado já entrou (diplomat, adapter (wire->model)
;;; tentar não importar os adapters


;;;;;;; ATOM

(s/defn insere-compra!
  [compras :- Atom
   registro :- model.compra/Compra]
  (swap! compras logic.common/insere-registro registro))

(s/defn insere-compras!
  [compras :- Atom
   registros :- [model.compra/Compra]]
  (swap! compras logic.common/insere-registros registros))

(s/defn lista-compras!
  [compras :- Atom]
  (logic.common/lista-entidade @compras))

(s/defn pesquisa-compra-por-id! :- model.compra/Compra
  [compras :- Atom
   id :- model.compra/Id]
  (logic.common/pesquisa-registro-por-id @compras id))

(s/defn exclui-compra!
  [compras :- Atom
   id :- model.compra/Id]
  (swap! compras logic.common/exclui-registro id))


;;;;;; BASE DE DADOS

