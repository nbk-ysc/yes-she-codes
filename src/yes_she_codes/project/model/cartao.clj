(ns yes-she-codes.project.model.cartao
  (:require [schema.core :as s]
            [yes-she-codes.project.model.constrains.constrains :as constrains]
            [yes-she-codes.project.model.cliente :as model.cliente])
  (:import (java.time YearMonth)))

(s/defschema NumeroCartao
  (s/constrained
    Long
    constrains/intervalo-cartao?))

(s/defschema Cvv
  (s/constrained
    Long
    constrains/intervalo-cvv?))

(s/defschema ValorFinanceiro
  (s/constrained
    BigDecimal
    constrains/maior-igual-zero?))

;; todo
;; defschema gera erro!!!
(s/def Validade
  YearMonth)

(s/defschema Cartao
  {:numero   NumeroCartao
   :cvv      Cvv
   :validade Validade
   :limite   ValorFinanceiro
   :cliente  model.cliente/Cpf})

(s/defschema Cartoes
  [Cartao])