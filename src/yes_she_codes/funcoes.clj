(ns yes-she-codes.funcoes
  (:require [java-time :as t]))

(defn total-gasto
  "Total de gastos de uma lista de compras"
  [compras]
  (reduce + (map :Valor compras)))

(defn mes-certo?
  "É o mes certo"
  [mes compra]
  (let [intMes (t/as (get compra :Data) :month-of-year)]
    (= intMes mes)))

(defn busca-mes
  "Buscar uma lista de comprar por um mes especifico"
  [mes compras]
  (filter #(mes-certo? mes %1) compras))

(defn busca-estabelecimento
  "Buscar uma lista de comprar por um mes especifico"
  [estabelecimento compras]
  (filter #(= estabelecimento (get %1 :Estabelecimento)) compras))

(defn busca-cartao
  "Buscar uma lista de comprar por um mes especifico"
  [cartao compras]
  (filter #(= cartao (get %1 :Cartao)) compras))

(defn total-gasto-no-mes
  "Total de gastos em um mes"
  [mes compras]
  (total-gasto (busca-mes mes compras)))

(defn busca-intervalo-valor
  "Buscar uma lista de comprar por um mes especifico"
  [valor-minimo valor-maximo compras]
  (filter #(<= valor-minimo (get %1 :Valor) valor-maximo) compras))

(defn agrupar-categoria
  "Agrupar as comprar por gategoria"
  [Compras]
  (->> Compras
       (group-by :Categoria)
       (map (fn [[chave compras]] {:Categoria chave :Valor (reduce + (map :Valor compras))}))))