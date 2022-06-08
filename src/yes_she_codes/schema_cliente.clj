(ns yes-she-codes.schema-cliente
  (:use [clojure pprint])
  (:require [schema.core :as s]))

; schema de clientes.

(defn nome-maior-que-dois? [x]
  (> (count x) 2))

(def NomeValido (s/pred nome-maior-que-dois?))

(s/defn validaNome [nome :- s/Str]
  (s/validate NomeValido nome))

(validaNome "Carol")

;funciona, mas porque não adapta o valor?

(def cpf-valido? (partial re-matches #"[0-9]{3}\.[0-9]{3}\.[0-9]{3}-[0-9]{2}"))

(def CpfValido (s/constrained s/Str cpf-valido?))

(s/defn validaCpf [cpf :- s/Str]
  (s/validate  CpfValido cpf))

(validaCpf "831.428.658-51")

(def email-valido? (partial re-matches #"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"))

(def EmailValido (s/constrained s/Str email-valido?))

(s/defn validaEmail [email :- s/Str]
  (s/validate EmailValido email))

(validaEmail "abc@nubank.com")









