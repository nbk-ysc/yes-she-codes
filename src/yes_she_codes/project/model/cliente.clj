(ns yes-she-codes.project.model.cliente
  (:require [schema.core :as s]
            [yes-she-codes.project.model.constrains.constrains :as constrains]))

(s/defschema Nome
  (s/constrained
    s/Str
    constrains/pelo-menos-dois-chars?))

(s/defschema Cpf
  (s/constrained
    s/Str
    constrains/formato-cpf?))

(s/defschema Email
  (s/constrained
    s/Str
    constrains/formato-email?))

(s/defschema Cliente
  {:nome  Nome
   :cpf   Cpf
   :email Email})

(s/defschema Clientes
  [Cliente])