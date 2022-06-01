(ns yes-she-codes.semana3.validations
  (:require [java-time :as time]))

(defn nome-valido? [nome]
  (>= (count nome) 2))

(defn cpf-valido? [cpf]
  (re-matches #"\d{3}\.\d{3}\.\d{3}\-\d{2}" cpf))

(defn email-valido? [email]
  (let [pattern #"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"]
    (re-matches pattern email)))

(defn numero-cartao-valido? [numero]
  (and (>= numero 1) (<= numero 9999999999999999)))

(defn cvv-valido? [cvv]
  (and (>= cvv 1) (<= cvv 999)))

(defn data-da-compra-valida? [data]
  (time/not-after? data (time/local-date)))

(defn categoria-valida? [categoria]
  (let [categorias-validas #{"Alimentação" "Automóvel" "Casa" "Educação" "Lazer" "Saúde"}]
    (contains? categorias-validas categoria)))