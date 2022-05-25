(ns yes_she_codes.semana3.logica
  (:use clojure.pprint)
  (:require [schema.core :as s]))

; Funcao que valida se um numero é positivo
(defn valor-positivo? [x]
  (> x 0))
; Schema que valida se um num é positvo
(def Positivo (s/pred valor-positivo?))

