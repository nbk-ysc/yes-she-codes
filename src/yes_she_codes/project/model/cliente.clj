(ns yes-she-codes.project.model.cliente
  (:require [schema.core :as s]
            [yes-she-codes.project.model.constraints.constraints :as constraints]))

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
  {(s/optional-key :id) (s/pred pos-int?)
   :nome                Nome
   :cpf                 Cpf
   :email               Email})

(s/defschema Clientes
  [Cliente])