(ns yes-she-codes.models.clientes
  (:require [schema.core :as s]
            [yes-she-codes.models.common :as y.models.common]))

(defn nome-valido? [nome]
  (>= (count nome) 2))

(defn email-valido? [email]
  (= (re-find
       #"^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$"
       email)
     email))

(def NomeSchema (s/constrained s/Str nome-valido?))
(def EmailSchema (s/constrained s/Str email-valido?))

(def ClienteSchema
  {:nome  NomeSchema,
   :cpf   y.models.common/CPFSchema,
   :email EmailSchema})

(def ClientesSchema [ClienteSchema])
