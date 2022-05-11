(ns yes-she-codes.funcoes
  (:require [clojure.string :as str]))

(defn total-gasto
  "Total de gastos de uma lista de compras"
  [compras]
  (reduce + (map :Valor compras)))

(defn mes-certo?
  "É o mes certo"
  [mes compra]
  (let [intMes (Integer/parseInt (get (str/split (get compra :Data "0000-00-00") #"-") 1 "00"))]
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