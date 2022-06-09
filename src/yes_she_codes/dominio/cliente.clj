(ns yes-she-codes.dominio.cliente
  (:require [schema.core :as s]
            [yes-she-codes.util :as y.util]))

(defn nome-valido?
  [nome]
  (>= (count nome) 2))

(def cpf-valido?
  (partial re-matches #"[0-9]{3}\.[0-9]{3}\.[0-9]{3}-[0-9]{2}"))

(def email-valido?
  (partial re-matches #"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"))

(def NomeValido
  (y.util/min-caracteres 2))

(def CpfValido
  (s/constrained s/Str cpf-valido?))

(def EmailValido
  (s/constrained s/Str email-valido?))

(def ClienteSchema
  {(s/optional-key :id) y.util/IdOpcional
   :nome                NomeValido
   :cpf                 CpfValido
   :email               EmailValido})

(s/defn ->Cliente :- ClienteSchema
        [id :- y.util/IdOpcional
         nome :- NomeValido
         cpf :- CpfValido
         email :- EmailValido]
        {:id    id
         :nome  nome
         :cpf   cpf
         :email email})