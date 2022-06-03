(ns yes_she_codes.semana3.validators
  (:require [java-time :as time]))

(defn str->-2?
  [nome]
  (and
    (string? nome)
    (> (count nome) 2)))

(defn formato-cpf?
  [cpf]
  (re-matches #"^[0-9]{3}\.[0-9]{3}\.[0-9]{3}-[0-9]{2}$" cpf))

(defn formato-email?
  [email]
  (re-matches #".+\@.+\..+" email))

(defn numero-cartao-valido?
  [numero]
  (and
    (int? numero)
    (>= numero 0)
    (<= numero 10000000000000000)))

(defn numero-0-999?
  [cvv]
  (and
    (int? cvv)
    (>= cvv 0)
    (<= cvv 999)))

(defn pos-bigdec?
  [limite]
  (and (decimal? limite)
       (>= limite 0)))

(defn data-valida?
  [data]
  (let [agora (time/local-date)]
    (not (time/after? data agora))))

(defn categoria-pertence?
  [categoria]
  (let [categorias ["Alimentação", "Automóvel", "Casa", "Educação", "Lazer", "Saúde"]]
    (some #(= categoria %) categorias)))