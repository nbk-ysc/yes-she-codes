(ns yes-she-codes.project.model.cartao
  (:require [schema.core :as s]
            [yes-she-codes.project.model.constraints.constraints :as constraints]
            [yes-she-codes.project.model.cliente :as model.cliente])
  (:import (java.time YearMonth)))

(def required-keys
  #{:cartao/numero :cartao/cvv :cartao/validade :cartao/limite :cartao/cliente})

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
  {(s/optional-key :id) Id
   :cartao/numero       NumeroCartao
   :cartao/cvv          Cvv
   :cartao/validade     Validade
   :cartao/limite       ValorFinanceiro
   :cartao/cliente      model.cliente/Cpf})

(s/defschema Cartoes
  [Cartao])