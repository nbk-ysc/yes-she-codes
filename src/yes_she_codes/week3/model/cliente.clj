(ns yes-she-codes.week3.model.cliente
  (:require [schema.core :as s]
            [yes-she-codes.week3.model.constrains.constrains :as constrains]))

(def Nome  (s/constrained s/Str constrains/pelo-menos-dois-chars?))
(def Cpf   (s/constrained s/Str constrains/formato-cpf?))
(def Email (s/constrained s/Str constrains/formato-email?))

(def ClienteSchema
  {:nome  Nome
   :cpf   Cpf
   :email Email})
