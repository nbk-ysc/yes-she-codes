(ns yes-she-codes.filtros
  (:use clojure.pprint)
  (:require [yes-she-codes.db :as y.db]
            [clojure.string :as str]))

(def lista-compras (y.db/todas-compras))

;BUSCAR COMPRAS POR MES
(defn get-moth [mes]
  (get (str/split mes #"-") 1))

(defn lista-compras-por-mes [mes lista-compras]
  ;(println "Todas as compras no mês" mes)
  (filter #(= (get-moth (:data %)) mes) lista-compras))

;(pprint (lista-compras-por-mes "03" lista-compras))

;CALCULAR O TOTAL DA FATURA DE UM MÊS
(defn get-extrato-mes [cartao mes lista-compras]
  (filter #(= (:cartao %) cartao) (lista-compras-por-mes mes lista-compras)))

(defn total-gasto-no-mes [cartao mes lista-compras]
  (print "Todas as compras do cartão" cartao "no mês" mes ": R$ ")
  (reduce + (map :valor (get-extrato-mes cartao mes lista-compras))))

(pprint (total-gasto-no-mes 1234123412341234 "02" lista-compras))