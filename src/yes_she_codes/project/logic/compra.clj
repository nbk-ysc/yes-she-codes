(ns yes-she-codes.project.logic.compra
  (:require [schema.core :as s]
            [yes-she-codes.project.model.cartao :as model.cartao]
            [yes-she-codes.project.model.compra :as model.compra])
  (:import (java.time Month)))

(s/defn total-gasto :- model.cartao/ValorFinanceiro
  [lista-compras :- model.compra/Compras]
  (transduce (map :valor) + 0M lista-compras))

(s/defn lista-de-compras-do-mes :- model.compra/Compras
  [mes :- Month
   lista-compras :- model.compra/Compras]
  (->> lista-compras
       (filter #(= mes (.getMonth (:data %))))))

(s/defn lista-de-compras-do-estabelecimento :- model.compra/Compras
  [estabelecimento :- model.compra/Estabelecimento
   lista-compras :- model.compra/Compras]
  (->> lista-compras
       (filter #(= estabelecimento (:estabelecimento %)))))

(s/defn total-gasto-no-mes :- model.cartao/ValorFinanceiro
  [mes :- Month
   lista-compras :- model.compra/Compras]
  (->> lista-compras
       (lista-de-compras-do-mes mes)
       (total-gasto)))

(s/defn lista-de-compras-por-intervalo-de-valores :- model.compra/Compras
  [valor-max :- model.cartao/ValorFinanceiro
   valor-min :- model.cartao/ValorFinanceiro
   lista-compras :- model.compra/Compras]
  (->> lista-compras
       (filter #(<= valor-min (:valor %) valor-max))))

(s/defn gasto-por-categoria :- model.compra/GastoCategoria
  [lista-compras :- model.compra/Compras]
  (->> lista-compras
       (group-by :categoria)
       (map (fn [[key vals]] [key (total-gasto vals)]))
       (reduce conj {})))