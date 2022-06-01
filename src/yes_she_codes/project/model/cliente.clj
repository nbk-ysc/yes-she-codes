(ns yes-she-codes.project.model.cliente
  (:require [schema.core :as s]
            [yes-she-codes.project.model.constraints.constraints :as constraints]))

(s/defschema Id
  (s/constrained
    s/Num
    constraints/maior-igual-zero?))

(s/defschema Nome
  (s/constrained
    s/Str
    constraints/pelo-menos-dois-chars?))

(s/defschema Cpf
  (s/constrained
    s/Str
    constraints/formato-cpf?))

(s/defschema Email
  (s/constrained
    s/Str
    constraints/formato-email?))

(s/defschema Cliente
  {:cliente/id    s/Uuid
   :cliente/nome  Nome
   :cliente/cpf   Cpf
   :cliente/email Email})
