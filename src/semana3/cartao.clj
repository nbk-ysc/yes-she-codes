(ns semana3.cartao
  (:use clojure.pprint)
  (:require [schema.core :as s
             :include-macros true                           ;; cljs only
             ]
            [java-time :as t]
            [semana3.logica :as s.logica]))

(def NumeroCartao (s/constrained s/Int s.logica/maior-ou-igual-a-zero-e-menor-ou-igual-a-numero-grande?))
(def CVV (s/constrained s/Int s.logica/maior-ou-igual-a-zero-e-menor-ou-igual-a-999?))
(def Limite (s/constrained BigDecimal s.logica/maior-ou-igual-a-zero?))
(def Cliente (s/constrained s/Str s.logica/cpf?))

(def CartaoSchema
  {:numero   NumeroCartao
   :cvv      CVV
   :validade java.time.YearMonth
   :limite   Limite
   :cliente  Cliente})

(s/defn novo-cartao :- CartaoSchema
  [numero :- NumeroCartao, cvv :- CVV, validade :- java.time.YearMonth, limite :- Limite, cliente :- Cliente]
  {:numero   numero
   :cvv      cvv
   :validade validade
   :limite   limite
   :cliente  cliente})

(pprint (novo-cartao 4321432143214321
                     222
                     (t/year-month "2024-02")
                     2000M
                     "333.444.555-66"))