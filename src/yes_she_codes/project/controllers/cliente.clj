(ns yes-she-codes.project.controllers.cliente
  (:require [schema.core :as s]
            [yes-she-codes.project.model.cliente :as model.cliente]
            [yes-she-codes.project.logic.common.common :as logic.common]))

(s/defn insere-cliente! :- model.cliente/Clientes
  [clientes :- model.cliente/Clientes
   record :- model.cliente/Cliente]
  (swap! clientes logic.common/insere-record record))

(s/defn lista-clientes!
  [clientes :- model.cliente/Clientes]
  (logic.common/lista-entidade @clientes))

(s/defn pesquisa-cliente-por-id! :- model.cliente/Cliente
  [clientes :- model.cliente/Clientes
   id :- s/Num]
  (logic.common/pesquisa-record-por-id @clientes id))

(s/defn exclui-cliente! :- model.cliente/Clientes
  [clientes :- model.cliente/Clientes
   id :- s/NUm]
  (swap! clientes logic.common/exclui-record id))
