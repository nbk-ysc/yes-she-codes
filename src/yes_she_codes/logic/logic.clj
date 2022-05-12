(ns yes-she-codes.logic.logic
  (:require [yes-she-codes.logic.util :as u]))


(defn total-gasto
  "recebe uma lista de compras e retorna a soma dos valores gastos"
  [lista-compras]
  (reduce + (map :valor lista-compras)))


(defn lista-de-compras-do-mes
  "retorna a lista de compras feitas somente naquele mês"
  [mes lista-compras]
  (filter #(= mes (u/qual-mes? (:data %))) lista-compras))


(defn lista-de-compras-do-estabelecimento
  "retorna uma lista de compras feitas somente naquele estabelecimento"
  [estabelecimento lista-compras]
  (filter #(= estabelecimento (:estabelecimento %)) lista-compras))


(defn total-gasto-no-mes
  "calcule a soma dos valores gastos num determinado cartão em um mês
  premissa: todas as compras consideradas são de um mesmo cartão"
  [mes lista-compras]
  (let [lista-compras-mes (lista-de-compras-do-mes mes lista-compras)]
    (total-gasto lista-compras-mes)))


(defn lista-de-compras-por-intervalo
  "retorna as compras que estão dentro de um intervalo dado."
  [tempo-max tempo-min lista-compras]
  (filter #(u/pertence-ao-intervalo? tempo-max tempo-min (:data %)) lista-compras))


(defn gasto-por-categoria
  "retorna os totais gasto agrupados por categoria"
  [lista-compras]
  (->> lista-compras
       (group-by :categoria)
       (map (fn [[key vals]] [key (total-gasto vals)]))
       (reduce conj {})))

