(ns yes-she-codes.project.adapter.cartao
  (:require [clojure.string :as string]
            [schema.core :as s]
            [yes-she-codes.project.model.cartao :as model.cartao]
            [yes-she-codes.project.wire.in.csv :as in.csv]
            [yes-she-codes.project.adapter.csv.common :as csv.common]
            [java-time :as time]))

(s/defn ^:private string-sem-espacos :- s/Str
  [string :- s/Str]
  (string/replace string #"\s" ""))

(s/defn str->valor-financeiro :- model.cartao/ValorFinanceiro
  [valor] :- s/Str
  (bigdec valor))

(s/defn str->num-cartao :- model.cartao/NumeroCartao
  [cartao] :- s/Str
  (Long/parseLong (string-sem-espacos cartao)))

(s/defn str->validade :- model.cartao/Validade
  [year-month :- s/Str]
  (time/year-month year-month))

(s/defn str->cvv :- model.cartao/Cvv
  [cvv :- s/Str]
  (Long/parseLong cvv))

(s/defn ^:private csv-map->model :- model.cartao/Cartao
  [{:keys [numero cvv validade limite cliente]} :- in.csv/CsvMapa]
  {:numero   (str->num-cartao numero)
   :cvv      (str->cvv cvv)
   :validade (str->validade validade)
   :limite   (str->valor-financeiro limite)
   :cliente  cliente})

(s/defn csv->cartoes :- model.cartao/Cartoes
  [csv-data :- in.csv/RawCsv]
  (csv.common/csv->model
    csv-data
    csv-map->model))