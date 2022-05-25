(ns semana3.compra
  (:use clojure.pprint)
  (:require [schema.core :as s
             :include-macros true                           ;; cljs only
             ]
            [java-time :as t]
            [semana3.logic :as s.logic]))

(def Valor (s/constrained BigDecimal pos?))
(def Estabelecimento (s/constrained s/Str s.logic/pelo-menos-2-caracteres?))
(def Categoria (s/pred s.logic/categoria?))
(def Cartao (s/constrained s/Int s.logic/maior-ou-igual-a-zero-e-menor-ou-igual-a-numero-grande?))
(def Data (s/pred s.logic/menor-ou-igual-a-data-atual))

(def CompraSchema
  {:data            Data
   :valor           Valor
   :estabelecimento Estabelecimento
   :categoria       Categoria
   :cartao          Cartao})

(pprint (s/validate CompraSchema {:data            (t/local-date "2022-05-09")
                                  :valor           100M
                                  :estabelecimento "Amazon"
                                  :categoria       "Casa"
                                  :cartao          4321432143214321}))
