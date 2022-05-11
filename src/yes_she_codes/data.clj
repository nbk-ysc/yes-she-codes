(ns yes-she-codes.data
  (:use clojure.data)
  (:require [yes-she-codes.db :as y.db]
            [clojure.java.io :as io]
            [java-time :as t]))

(def compra1 {:data 2022 01 01
              :valor 129.90
              :estabelecimento "Outback"
              :categoria "Alimentação"
              :cartao 1234123412341234
              })

(defn get-moth [mes]
  ;(get (str/split mes #"-") 1)
  )

(defn lista-compras-por-mes [mes lista-compras]
  ;(println "Todas as compras no mês" mes)
  ;(filter #(= (get-moth (:data %)) mes) lista-compras)
  )

;(pprint (lista-compras-por-mes "03" lista-compras))

