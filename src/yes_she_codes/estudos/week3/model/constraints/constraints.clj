(ns yes-she-codes.estudos.week3.model.constraints.constraints
  (:require [java-time :as time]))

(defn data-menor-igual-a-hoje?
  [data]
  (not (.isAfter data (time/local-date))))

(defn pelo-menos-dois-chars?
  [estabelecimento]
  (>= (count estabelecimento) 2))

(defn maior-igual-zero?
  [valor]
  (>= valor 0))

(defn formato-cpf?
  [cpf]
  (re-matches #"\d{3}.\d{3}.\d{3}-\d{2}" cpf))

(defn formato-email?
  [email]
  (re-matches #"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?" email))

(defn intervalo-cartao?
  [cartao]
  (<= 0 cartao 10000000000000000))

(defn intervalo-cvv?
  [cvv]
  (<= 0 cvv 999))
