(ns yes-she-codes.estudos.week3.model.cliente
  (:require [schema.core :as s]
            [yes-she-codes.estudos.week3.model.constraints.constraints :as constraints]))

(def Nome  (s/constrained s/Str constraints/pelo-menos-dois-chars?))
(def Cpf   (s/constrained s/Str constraints/formato-cpf?))
(def Email (s/constrained s/Str constraints/formato-email?))

(def ClienteSchema
  {:nome  Nome
   :cpf   Cpf
   :email Email})
