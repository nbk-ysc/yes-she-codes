(ns yes-she-codes.week2.logic.cartao
  (:require [yes-she-codes.week2.logic.common.common :as logic.common]))

(defn insere-cartao!
  [cartoes record]
  (swap! cartoes logic.common/insere-record record))

(defn lista-cartoes!
  [cartoes]
  (logic.common/lista-entidade @cartoes))

(defn pesquisa-cartao-por-id!
  [cartoes id]
  (logic.common/pesquisa-record-por-id @cartoes id))

(defn exclui-cartao!
  [cartoes id]
  (swap! cartoes logic.common/exclui-record id))