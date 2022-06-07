(ns yes-she-codes.schema
  (:use [clojure pprint])
  (:require [schema.core :as s]))

; entendimendo: fazer as funções de validação de forma separada e aplicar no schema / sem record ou afins.

(defn nome-maior-que-dois? [x]
  (> (count x) 2))

(def NomeValido (s/pred nome-maior-que-dois?))

(s/defn validaNome [nome :- s/Str]
  (s/validate NomeValido nome))

(validaNome "Carol")

;funciona, mas porque não adapta o valor?
(defn cpf? []
  #(re-matches #"(\d{3}\.){2}\d{3}\-\d{2}" %))

(def CpfValido (s/pred cpf?))

(s/defn validaCpf [cpf? :- s/Str]
  (s/validate  CpfValido cpf?))

(validaCpf "831.428.658-51")

(defn email? []
  #(re-matches #"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?" %)

  (def EmailValido (s/pred email?)))

(s/defn validaEmail [email? :- s/Str]
  (s/validate  EmailValido email?))

(validaEmail "abc@nubank.com")









