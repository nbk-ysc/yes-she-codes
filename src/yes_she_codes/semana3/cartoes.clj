(ns yes-she-codes.semana3.cartoes
  (:require [schema.core :as s])
  (:use [clojure pprint])
  )



(s/set-fn-validation! true)



(def CartaoSchema
  "Schema de um cartao"
  {:numero s/Int,                                           ; inteiro entre 0 e 1 0000 0000 0000 0000.
   :cvv s/Int,                                              ; inteiro entre 0 e 999.
   :validade s/Str,                                         ; se estiver usando String, deve ter o formato "yyyy-mm".
   :limite s/Int,                                           ; BigDecimal maior ou igual a zero.
   :cliente s/Str                                           ; String com o formato 000.000.000-00.
   }
  )