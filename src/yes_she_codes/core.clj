(ns yes-she-codes.core
  (:use [clojure pprint])
  (:require [yes-she-codes.compras :as y.compras]))


(defn buscar-por-estabelecimento
  [estabelecimento lista]
  (filter #(= estabelecimento (:ESTABELECIMENTO %)) lista))

(defn buscar-por-mes
  [mes lista]
  (filter #(= mes (subs (% :DATA) 5 7)) lista))

(defn buscar-por-cartao
  [cartao lista]
  (filter #(= cartao (:CARTAO %)) lista))

(defn total-gasto
  [cartao lista]
  (let [nova-lista (buscar-por-cartao cartao lista)]
    (reduce + (map :VALOR nova-lista))))

(defn total-gasto-no-mes
  [mes cartao lista]
  (let [nova-lista (buscar-por-mes mes lista)]
    (reduce + (map :VALOR (buscar-por-cartao cartao nova-lista)))))

(defn filtro-intervalo
  [valor-minimo valor-maximo lista]
  (filter #(and (<= valor-minimo (:VALOR %)) (>= valor-maximo (:VALOR %))) lista))

(defn gastos-por-categoria
  [lista]
  (map (fn [[key values]]
      {:CATEGORIA key
       :total (reduce + (map :VALOR values))})
    (group-by :CATEGORIA lista)))

(pprint "Total gasto em um cartao")
(pprint (total-gasto 1234123412341234 y.compras/compras))

(pprint "Busca por estabelecimento")
(pprint (buscar-por-estabelecimento "Alura" y.compras/minhas-compras))

(pprint "Busca por mes")
(pprint (buscar-por-mes "04" y.compras/minhas-compras))

(pprint "Busca por cartão")
(pprint (buscar-por-cartao 1234123412341234 y.compras/compras))

(pprint "Total gasto em um mes em um cartao")
(pprint (total-gasto-no-mes "01" 1234123412341234 y.compras/compras))

(pprint "Filtro por intervalo")
(pprint (filtro-intervalo 200 300 y.compras/minhas-compras))

(pprint "Gastos por categoria")
(pprint (gastos-por-categoria  y.compras/minhas-compras))





