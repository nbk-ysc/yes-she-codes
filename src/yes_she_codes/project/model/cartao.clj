(ns yes-she-codes.project.model.cartao
  (:require [schema.core :as s]
            [yes-she-codes.project.model.constraints.constraints :as constraints]
            [yes-she-codes.project.model.cliente :as model.cliente])
  (:import (java.time YearMonth)))

(s/defschema Id
  (s/constrained
    s/Num
    constraints/maior-igual-zero?))

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

(s/defschema Validade
  (s/pred (partial instance? YearMonth)))

(s/defschema Cartao
  {:cartao/id       s/Uuid
   :cartao/numero   NumeroCartao
   :cartao/cvv      Cvv
   :cartao/validade Validade
   :cartao/limite   ValorFinanceiro
   :cartao/cliente  model.cliente/Cpf})
