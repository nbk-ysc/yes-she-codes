(ns yes_she_codes.semana4.utils
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [clojure.string :as str]
            [java-time :as time]
            [schema.core :as s]))

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

(def StrGTE2
  (s/pred str->-2?))

(def BigDecPositivo
  (s/pred pos-bigdec?))

(defn limpa-whitespace
  [string]
  (str/replace string " " ""))

(defn processa-csv [caminho-arquivo funcao-mapeamento]
  (->> (slurp caminho-arquivo)
       clojure.string/split-lines
       rest
       (map #(clojure.string/split % #","))
       (map funcao-mapeamento)))

(defn local-date-time->date
  [valor]
  (if (time/local-date-time? valor)
    (->> (.atZone valor (time/zone-id))
         .toInstant
         java.util.Date/from)
    valor))

(defn local-date->date
  [valor]
  (if (time/local-date? valor)
    (->> (time/zone-id)
         (.atStartOfDay valor)
         (.toLocalDateTime)
         (local-date-time->date))
    valor))

(defn date->local-date-time
  [valor]
  (if (= java.util.Date (class valor))
    (-> (.toInstant valor)
        (java.time.LocalDateTime/ofInstant (time/zone-id)))
    valor))

(defn date->local-date
  [valor]
  (if (= java.util.Date (class valor))
    (-> (.toInstant valor)
        (java.time.LocalDateTime/ofInstant (time/zone-id))
        time/local-date)
    valor))

(defn ajusta-compra-local-para-java-date
  [compra]
  (assoc compra :compra/data (local-date->date (:compra/data compra))))

(defn ajusta-compra-java-date-para-local
  [compra]
  (assoc compra :data (date->local-date (:data compra))))