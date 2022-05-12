(ns yes-she-codes.logic.logic
  (:require [yes-she-codes.logic.util :as u]))


(defn total-gasto
  "recebe uma lista de compras e retorna a soma dos valores gastos"
  [lista-compras]
  (->> lista-compras
      (map :valor)
      (reduce +)))


(defn lista-de-compras-do-mes
  "retorna a lista de compras feitas somente naquele mês"
  [mes lista-compras]
  (->> lista-compras
       (filter #(u/mesmo-mes? (:data %) mes))))


(defn lista-de-compras-do-estabelecimento
  "retorna uma lista de compras feitas somente naquele estabelecimento"
  [estabelecimento lista-compras]
  (->> lista-compras
       (filter #(= estabelecimento (:estabelecimento %)))))


(defn total-gasto-no-mes
  "calcule a soma dos valores gastos num determinado cartão em um mês
  premissa: todas as compras consideradas são de um mesmo cartão"
  [mes lista-compras]
  (->> lista-compras
       (lista-de-compras-do-mes mes)
       (total-gasto)))


(defn lista-de-compras-por-intervalo
  "retorna as compras que estão dentro de um intervalo dado."
  [data-max data-min lista-compras]
  (->> lista-compras
       (filter #(u/pertence-ao-intervalo? data-max data-min (:data %)))))


(defn gasto-por-categoria
  "retorna os totais gasto agrupados por categoria"
  [lista-compras]
  (->> lista-compras
       (group-by :categoria)
       (map (fn [[key vals]] [key (total-gasto vals)]))
       (reduce conj {})))

