(ns yes-she-codes.project.model.cartao
  (:require [schema.core :as s]
            [yes-she-codes.project.model.constraints.constraints :as constraints]
            [yes-she-codes.project.model.cliente :as model.cliente])
  (:import (java.time YearMonth)))

(s/defschema NumeroCartao
  (s/constrained
    Long
    constraints/intervalo-cartao?))

(s/defschema Cvv
  (s/constrained
    Long
    constraints/intervalo-cvv?))

(s/defschema ValorFinanceiro
  (s/constrained
    BigDecimal
    constraints/maior-igual-zero?))

;; todo
;; defschema gera erro!!!
(s/def Validade
  YearMonth)

(s/defschema Cartao
  {(s/optional-key :id) (s/pred pos-int?)
   :numero              NumeroCartao
   :cvv                 Cvv
   :validade            Validade
   :limite              ValorFinanceiro
   :cliente             model.cliente/Cpf})

(s/defschema Cartoes
  [Cartao])