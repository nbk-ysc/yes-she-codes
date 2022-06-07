(ns yes-she-codes.semana4.logica
  (:use clojure.pprint)
  (:require [schema.core :as s]
            [clojure.string :as str]))


; Funçao que valida nome do cliente
(defn nome-valido? [nome]
  (>= (count nome) 2))

(def ValidaNome
  "Schema do nome-valido?"
  (s/constrained s/Str nome-valido? 'NameFormatInvalid))

; Funçao que valida o formato da data
(defn cpf-valido? [cpf]
  (re-matches #"[0-9]{3}\.[0-9]{3}\.[0-9]{3}\-[0-9]{2}" cpf))

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
(defn limite-valido? [x] (>= x 0))

(def ValidaLimite
  "Schema do limite-valido?"
  (s/constrained s/Num limite-valido?))

; Funcao que valida se um numero esta entre 0 e 999
(defn cvv-valido?  [x]
  (let [pattern #"[0-9]{3}"]
    (and (<= x 999) (re-matches pattern (str x)))))

(def ValidaCVV
  "Schema do cvv-valido?"
  (s/constrained s/Int cvv-valido? 'CVVFormatInvalid))

; Valida numero do cartão
(defn cartao-valido? [x]
  (let [pattern #"[0-9]{4}[0-9]{4}[0-9]{4}[0-9]{4}"]
    (and (< x 10000000000000000) (re-matches pattern (str x))))
  )

(def ValidaCartao
  "Schema do cartao-valido?"
  (s/constrained s/Int cartao-valido? 'CardNumberInvalid))

; Funcao que valida data da validade do cartao
(defn validade-valida? [x]
  (re-matches #"\d{4}-\d{2}" x))

(def ValidaValidade
  "Schema do validade-valida?"
  (s/constrained s/Str validade-valida?))

; Funcao que valida a data da compra
(defn data-valida? [x]
  (re-matches #"\d{4}-\d{2}-\d{2}" x))

(def ValidaData
  "Schema do data-valida?"
  (s/constrained s/Str data-valida?))

; Funcao que valida se estabelecimento é um valido
(defn categoria-valida? [x]
  (let [lowerx (clojure.string/lower-case x)]
    (some #{lowerx} '("alimentação" "automóvel" "casa" "educação" "lazer" "saúde"))))

(def ValidaCateg
  "Schema do estabelecimento-valido?"
  (s/constrained s/Str categoria-valida?))
