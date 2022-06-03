(ns yes-she-codes.estudos.week3.model.cartao
  (:require [schema.core :as s]
            [yes-she-codes.estudos.week3.model.constraints.constraints :as constraints]
            [yes-she-codes.estudos.week3.model.cliente :as model.cliente])
  (:import (java.time YearMonth)))

(def NumeroCartao
  (s/constrained Long constraints/intervalo-cartao?))

(def Cvv
  (s/constrained Long constraints/intervalo-cvv?))

(def ValorFinanceiro
  (s/constrained BigDecimal constraints/maior-igual-zero?))

(def CartaoSchema
  {:numero   NumeroCartao
   :cvv      Cvv
   :validade YearMonth
   :limite   ValorFinanceiro
   :cliente  model.cliente/Cpf})
