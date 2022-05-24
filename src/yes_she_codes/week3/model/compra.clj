(ns yes-she-codes.week3.model.compra
  (:require [schema.core :as s]
            [java-time :as time])
  (:import (java.time LocalDate)))

(defrecord Compra [^Long       id
                   ^LocalDate  data
                   ^BigDecimal valor
                   ^String     estabelecimento
                   ^String     categoria
                   ^Long       cartao])



(def CompraSchema
  {:id              java.lang.Long
   :data            (s/conditional #(not (.isAfter % (time/local-date))) LocalDate)
   :valor           (s/conditional #(> % 0) java.lang.Double)
   :estabelecimento (s/conditional #(>= (count %) 2) s/Str)
   :categoria       (s/conditional #(contains? #{"Alimentação", "Automóvel", "Casa", "Educação", "Lazer", "Saúde"} %) s/Str)
   :cartao          (s/conditional #(<= 0 % 10000000000000000) java.lang.Long)})


