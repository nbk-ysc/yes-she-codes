(ns yes-she-codes.semana3.validations
  (:require [java-time :as time]))

(defn nome-valido? [nome]
  (>= (count nome) 2))

(defn cpf-valido? [cpf]
  (re-matches #"\d{3}\.\d{3}\.\d{3}\-\d{2}" cpf))

(defn email-valido? [email]
  (let [pattern #"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"]
    (re-matches pattern email)))

(defn- quantidade-de-digitos [valor]
  (int (inc (Math/floor (Math/log10 valor)))))

(defn numero-cartao-valido? [numero]
  (= 16 (quantidade-de-digitos numero)))

(defn cvv-valido? [cvv]
  (= 3 (quantidade-de-digitos cvv)))

(defn data-da-compra-valida? [data]
  (time/not-after? data (time/local-date)))

(defn categoria-valida? [categoria]
  (let [categorias-validas #{"Alimentação" "Automóvel" "Casa" "Educação" "Lazer" "Saúde"}]
    (contains? categorias-validas categoria)))