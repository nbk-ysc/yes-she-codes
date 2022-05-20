(ns yes-she-codes.semana1.refactor-utilizar-java-api-datas
  (:require [java-time :as jt]))

(use 'java-time)

(println (local-date 2015 10))

(def dia1 (local-date 2000 04 01))

(format "yyyy-MM" (zoned-date-time 2015 9 28))
(format "yyyy-MM" (zoned-date-time 2026 05))

(defn data-formatada=para-cartao
  [data])
(format "yyyy-MM-dd" (zoned-date-time 2022 01 01))

;(def cartao5  "2026-05"
;(def compra1  "2022-01-01"  ))
