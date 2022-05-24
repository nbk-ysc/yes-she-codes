(ns yes-she-codes.week3.model.constrains.constrains
  (:require [java-time :as time]))

(defn data-menor-igual-a-hoje?
  [data]
  (not (.isAfter data (time/local-date))))

(defn pelo-menos-dois-chars?
  [estabelecimento]
  (>= (count estabelecimento) 2))

(defn pertence-as-opcoes-de-categoria?
  [categoria]
  (let [categorias-permitidas #{"Alimentação", "Automóvel", "Casa", "Educação", "Lazer", "Saúde"}]
    (contains? categorias-permitidas categoria)))

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
