(ns yes-she-codes.core
  (:require [clojure.pprint]
            [yes-she-codes.db :as y.db]
            [yes-she-codes.clientes :as y.clientes]
            [yes-she-codes.compras :as y.compras]
            [yes-she-codes.cartoes :as y.cartoes]
            [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [java-time :as t]))

(defn total-gasto
  [lista]
  (reduce + (map :VALOR lista)))

(defn buscar-por-estabelecimento
  [estabelecimento lista]
  (filter #(= estabelecimento (:ESTABELECIMENTO %)) lista))

(defn buscar-por-mes
  [mes lista]
  (let [nova-lista (map #(assoc % :mes (subs (% :DATA) 5 7)) lista)]
    (filter #(= mes (:mes  %)) nova-lista)))

(defn total-gasto-no-mes
  [mes lista]
  (let [nova-lista (buscar-por-mes mes lista)]
    (reduce + (map :VALOR nova-lista))))

(defn filtro-intervalo
  [valor-minimo valor-maximo lista]
  (filter #(and (<= valor-minimo (:VALOR %)) (>= valor-maximo (:VALOR %))) lista))

(defn gastos-por-categoria
  [lista]
  (map (fn [[key values]]
      {:CATEGORIA key
       :total (reduce + (map :VALOR values))})
    (group-by :CATEGORIA lista)))





