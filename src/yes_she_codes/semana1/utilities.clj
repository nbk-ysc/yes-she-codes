(ns yes-she-codes.semana1.utilities
  (:require [java-time :as jt]))

(defn str->long [valor]
  (Long/parseLong (clojure.string/replace valor #" " "")))

(defn get-valor
  [compra]
  (get compra :valor 0))

(defn get-data
  [compra]
  (get compra :data 0))

(defn get-mes
  [data]
  (subs data 5 7))

(defn valor-esta-no-intervalo?
  [inicio fim compra]
  (<= inicio  (get compra :valor 0) fim ))

(defn valor-gasto-por-categoria
  [compras]
  (->> compras
       (map get-valor)
       (reduce +)))

(defn gasto-por-categoria
  [[categoria compras]]
  { :categoria categoria
   :total-gasto (valor-gasto-por-categoria compras)})

(defn processa-csv
  [caminho-arquivo ]
  (->> (slurp caminho-arquivo)
       ;clojure.string/split-lines
       ;rest
       println )
  )

;(processa-csv "")

;(use 'java-time)
;
;(println (local-date 2015 10))
;
;(def dia1 (local-date 2000 04 01))
;
;(format "yyyy-MM" (zoned-date-time 2015 9 28))
;(format "yyyy-MM" (zoned-date-time 2026 05))
;
;(defn data-formatada=para-cartao
;  [data])
;(format "yyyy-MM-dd" (zoned-date-time 2022 01 01))
;
;;(def cartao5  "2026-05"
;;(def compra1  "2022-01-01"  ))


