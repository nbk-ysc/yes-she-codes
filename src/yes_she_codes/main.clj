(ns yes-she-codes.main
  (:use clojure.pprint)
  (:require [yes-she-codes.db :as y.db]
            [clojure.string :as str]))

(def lista-compras (y.db/todas-compras))

;CALCULAR O TOTAL GASTO EM COMPRAS DE UMA LISTA DE COMPRAS
(defn total-gasto [lista-compras]
  (reduce + (map :valor lista-compras)))

;(pprint (total-gasto lista-compras))

;BUSCAR COMPRAS POR ESTABELECIMENTO
(defn lista-compras-por-estabelecimento [estabelecimento lista-compras]
  (println "Todas as compras n@" estabelecimento)
  (get (group-by  :estabelecimento lista-compras) estabelecimento))

;(pprint (lista-compras-por-estabelecimento "Alura" lista-compras))

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

;(pprint (total-gasto-no-mes 1234123412341234 "01" lista-compras))

;FILTRAR COMPRAS NUM INTERVALO DE VALORES
(defn filtro-maximo-minimo [lista-compras valormax valormin]
  (println "Compras realizadas entre R$" valormin "e R$" valormax)
  (filter #(< (:valor %) valormax) (filter #(> (:valor %) valormin) lista-compras)))

;(pprint (filtro-maximo-minimo lista-compras 130.0 84.0))

;AGRUPAR GASTOS POR CATEGORIA (NOT DONE YET)
(defn total-categoria [compra]
  )

(defn gastos-por-categoria [lista-compras]
  ((group-by :categoria lista-compras) 0)
  )

(pprint (gastos-por-categoria lista-compras))

