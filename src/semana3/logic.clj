(ns semana3.logic
  (:require [java-time :as t]))

(defn pelo-menos-2-caracteres? [string]
  (>= (count string) 2))

(defn cpf? [cpf]
  (re-matches #"[0-9]{3}\.[0-9]{3}\.[0-9]{3}\-[0-9]{2}" cpf))

(defn email? [email]
  (re-matches #".+\@.+\..+" email))

(defn maior-ou-igual-a-zero? [numero]
  (>= numero 0))

(defn maior-ou-igual-a-zero-e-menor-ou-igual-a-999? [numero]
  (and (>= numero 0) (<= numero 999)))

(defn maior-ou-igual-a-zero-e-menor-ou-igual-a-numero-grande? [numero]
  (and (>= numero 0) (<= numero 10000000000000000)))

(defn categoria? [categoria]
  (contains? #{"Alimentação", "Automóvel", "Casa", "Educação", "Lazer", "Saúde"} categoria))

(defn menor-ou-igual-a-data-atual [data]
  (t/before? data (t/local-date)))