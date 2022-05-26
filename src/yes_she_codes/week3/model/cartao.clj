(ns yes-she-codes.week3.model.cartao
  (:require [schema.core :as s]
            [yes-she-codes.week3.model.constrains.constrains :as constrains]
            [yes-she-codes.week3.model.cliente :as model.cliente])
  (:import (java.time YearMonth)))

(def NumeroCartao
  (s/constrained Long constrains/intervalo-cartao?))

(def Cvv
  (s/constrained Long constrains/intervalo-cvv?))

(def ValorFinanceiro
  (s/constrained BigDecimal constrains/maior-igual-zero?))

(def CartaoSchema
  {:numero   NumeroCartao
   :cvv      Cvv
   :validade YearMonth
   :limite   ValorFinanceiro
   :cliente  model.cliente/Cpf})
