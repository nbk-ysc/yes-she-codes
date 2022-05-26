(ns semana3.compra
  (:use clojure.pprint)
  (:require [schema.core :as s
             :include-macros true                           ;; cljs only
             ]
            [java-time :as t]
            [semana3.logica :as s.logica]))

(def Valor (s/constrained BigDecimal pos?))
(def Estabelecimento (s/constrained s/Str s.logica/pelo-menos-2-caracteres?))
(def Categoria (s/pred s.logica/categoria?))
(def Cartao (s/constrained s/Int s.logica/maior-ou-igual-a-zero-e-menor-ou-igual-a-numero-grande?))
(def Data (s/pred s.logica/menor-ou-igual-a-data-atual))

(def CompraSchema
  {:data            Data
   :valor           Valor
   :estabelecimento Estabelecimento
   :categoria       Categoria
   :cartao          Cartao})

(s/defn nova-compra :- CompraSchema
  [data :- Data, valor :- Valor, estabelecimento :- Estabelecimento, categoria :- Categoria, cartao :- Cartao]
  {:data            data
   :valor           valor
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          cartao})

(pprint (nova-compra (t/local-date "2022-05-09")
                     100M
                     "Amazon"
                     "Casa"
                     4321432143214321))