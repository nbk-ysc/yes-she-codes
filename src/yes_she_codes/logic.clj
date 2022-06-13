(ns yes-she-codes.logic
  (:require [schema.core :as s]
            [java-time :as t]
            [clojure.string :as str]))

(s/set-fn-validation! true)

(defn format-numero [numero]
  (Long/parseLong (str/replace numero #" " "")))

(defn format-data-compra [data]
  (t/format "dd/MM/yyyy" (t/local-date data)))

(defn format-data-cartao [validate]
  (t/format "MM/yyyy" (t/year-month validate)))

(defn validate-numero [numero]
  (and (pos? numero) (< numero 1e+16)))

(defn validate-cvv [cvv]
  (and (pos? cvv) (< cvv 999)))

(defn validate-data-cartao [validate]
  (re-matches #"\d{2}[/]?\d{4}" validate))


(defn validate-email [email]
  (re-matches #".+\@.+\..+" email))

(defn validate-cpf [cpf]
  (re-matches #"\d{3}[\.]?\d{3}[\.]?\d{3}[-]?\d{2}" cpf))

(defn validate-string [string]
  (>= (count string) 2))

(def IdOpcional (s/maybe s/Num))

(defn validate-data [data]
  (re-matches #"[0-9]{2}[/]?[0-9]{2}[/]?[0-9]{4}" data))


(defn validate-categoria [categoria]
  (let [lista-categoria ["Alimentação", "Automóvel", "Casa", "Educação", "Lazer", "Saúde"]]
    (str/includes? lista-categoria categoria)))

(defn filtra-data
  [field value data]
  (filter #(= value (field %)) data))
