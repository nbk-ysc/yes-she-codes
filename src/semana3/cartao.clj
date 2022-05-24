(ns semana3.cartao
  (:use clojure.pprint)
  (:require [schema.core :as s
             :include-macros true                           ;; cljs only
             ]
            [java-time :as t]
            [semana3.logic :as s.logic]))

(def NumeroCartao (s/constrained s/Int
                                 s.logic/maior-ou-igual-a-zero?
                                 s.logic/menor-ou-igual-a-numero-grande?))
(def CVV (s/constrained s/Int
                        s.logic/maior-ou-igual-a-zero?
                        s.logic/menor-ou-igual-a-999?))
(def Limite (s/constrained BigDecimal s.logic/maior-ou-igual-a-zero?))
(def Cliente (s/constrained s/Str s.logic/cpf?))

(def CartaoSchema
  {:numero   NumeroCartao
   :cvv      CVV
   :validade java.time.YearMonth
   :limite   Limite
   :cliente  Cliente})

(pprint (s/validate CartaoSchema {:numero   4321432143214321
                                  :cvv      222
                                  :validade (t/year-month "2024-02")
                                  :limite   2000M
                                  :cliente  "333.444.555-66"}))


