(ns yes_she_codes.semana3.logica
  (:use clojure.pprint)
  (:require [schema.core :as s]))


; Funçao que valida nome do cliente
(defn nome-valido? [nome]
  (>= (count nome) 2))

(def ValidaNome
  "Schema do nome-valido?"
  (s/constrained s/Str nome-valido? 'NameFormatInvalid))

; Funçao que valida o formato da data
(defn cpf-valido? [cpf]
  (re-matches #"[0-9]{3}\.[0-9]{3}\.[0-9]{3}\-[0-9]{2}"  cpf))

(def ValidaCpf
  "Schema do cpf-valido?"
  (s/constrained s/Str cpf-valido? 'CpfFormatInvalid))

; Funcao que valida o formato do email
(defn email-valido?  [email]
  (let [pattern #"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"]
    (and (string? email) (re-matches pattern email))))

(def ValidaEmail
  "Schema do email-valido?"
  (s/constrained s/Str email-valido? 'EmailFormatInvalid))

; Funcao que valida se um numero é >= a 0
(defn limite-valido? [x]
  (>= x 0))

(def Limite
  "Schema do limite-valido?"
  (s/constrained s/Num limite-valido?))

; Funcao que valida se um numero esta entre 0 e 999
(defn cvv-valido? [x]
  (and (> x 0) (<= x 999)))

(def CVV
  "Schema do cvv-valido?"
  (s/constrained s/Int cvv-valido?))



