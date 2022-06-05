(ns yes-she-codes.study.week2.logic.cliente
  (:require [yes-she-codes.study.week2.logic.common.common :as logic.common]))

(defn insere-cliente!
  [clientes record]
  (swap! clientes logic.common/insere-record record))

(defn lista-clientes!
  [clientes]
  (logic.common/lista-entidade @clientes))

(defn pesquisa-cliente-por-id!
  [clientes id]
  (logic.common/pesquisa-record-por-id @clientes id))

(defn exclui-cliente!
  [clientes id]
  (swap! clientes logic.common/exclui-record id))