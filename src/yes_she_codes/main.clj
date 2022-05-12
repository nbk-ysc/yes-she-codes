(ns yes-she-codes.main
  (:use clojure.pprint)
  (:require [yes-she-codes.db :as y.db]
            [clojure.string :as str]))

(def lista-compras y.db/compras)

;CALCULAR O TOTAL GASTO EM COMPRAS DE UMA LISTA DE COMPRAS
(defn total-gasto [lista-compras]
  (->> lista-compras
       (map :valor)
       (reduce +)))

;(println "Total Gasto R$" (total-gasto lista-compras))

;BUSCAR COMPRAS POR ESTABELECIMENTO
(defn lista-compras-por-estabelecimento [estabelecimento lista-compras]
  (println "Todas as compras n@" estabelecimento)
  (-> :estabelecimento
      (group-by lista-compras)
      (get estabelecimento)))

;(pprint (lista-compras-por-estabelecimento "Alura" lista-compras))

;BUSCAR COMPRAS POR MES
(defn get-moth [mes]
  (get (str/split mes #"-") 1))

(defn lista-compras-por-mes [mes lista-compras]
  ;(println "Todas as compras no mês" mes)
  (filter #(= (get-moth (:data %)) mes) lista-compras))

;(pprint (lista-compras-por-mes "02" lista-compras))

;CALCULAR O TOTAL DA FATURA DE UM MÊS
(defn get-extrato-mes [cartao mes lista-compras]
  (->> lista-compras
       (lista-compras-por-mes mes)
       (filter #(= (:cartao %) cartao))))

(defn total-gasto-no-mes [cartao mes lista-compras]
  (println "Todas as compras do cartão" cartao "no mês" mes ": R$ ")
  (->> lista-compras
       (get-extrato-mes cartao mes)
       (map :valor)
       (reduce +)))

;(println (total-gasto-no-mes 1234123412341234 "01" lista-compras))

;FILTRAR COMPRAS NUM INTERVALO DE VALORES
(defn filtro-maximo-minimo [lista-compras valormax valormin]
  (println "Compras realizadas entre R$" valormin "e R$" valormax)
  (filter #(< (:valor %) valormax) (filter #(> (:valor %) valormin) lista-compras)))

;(pprint (filtro-maximo-minimo lista-compras 130.0 84.0))

;AGRUPAR GASTOS POR CATEGORIA
(defn total-categoria [[categoria compras]]
  (->> compras
       (map :valor)
       (reduce +)
       (println categoria "R$")))

(defn gastos-por-categoria [lista-compras]
  (->> lista-compras
       (group-by :categoria)
       (map total-categoria)))

;(gastos-por-categoria lista-compras)

;LER ARQUIVO (NOT DONE)
(def arquivo (slurp "src/yes_she_codes/files/clientes.csv"))
;(println (str/trim arquivo))


