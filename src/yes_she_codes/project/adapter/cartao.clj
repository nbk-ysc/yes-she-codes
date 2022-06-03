(ns yes-she-codes.project.adapter.cartao
  (:require [clojure.string :as string]
            [schema.core :as s]
            [yes-she-codes.project.model.cartao :as model.cartao]
            [yes-she-codes.project.wire.in.csv :as in.csv]
            [yes-she-codes.project.adapter.common.csv :as common.csv]
            [yes-she-codes.project.adapter.common.util :as common.util]
            [yes-she-codes.project.adapter.cliente :as adapter.cliente]
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
  [year-month :- (s/pred #(re-matches #"\d{4}-\d{2}" %))]
  (time/year-month year-month))

(s/defn str->cvv :- model.cartao/Cvv
  [cvv :- s/Str]
  (Long/parseLong cvv))

(s/defn ^:private csv-map->model :- model.cartao/Cartao
  [{:keys [numero cvv validade limite cliente]} :- in.csv/CsvMapa]
  (when (not-any? nil? [numero cvv validade limite cliente])
    #:cartao{:id       (common.util/uuid)
             :numero   (str->num-cartao numero)
             :cvv      (str->cvv cvv)
             :validade (str->validade validade)
             :limite   (str->valor-financeiro limite)
             :cliente  cliente}))

(s/defn csv->cartoes :- [model.cartao/Cartao]
  [csv-data :- in.csv/RawCsv]
  (common.csv/csv->model
    csv-data
    csv-map->model))

(s/defn model->datomic
  [{:cartao/keys [validade] :as cartao} :- model.cartao/Cartao]
  (-> cartao
      (assoc :cartao/validade (common.util/year-month->inst validade))))

(s/defn datomic->model-with-components :- model.cartao/CartaoComComponents
  [{:cartao/keys [validade cliente] :as cartao}]
  (-> cartao
      (assoc :cartao/validade (common.util/inst->year-month validade))
      (assoc :cartao/cliente (adapter.cliente/datomic->model cliente))
      (dissoc :db/id)))

(s/defn datomic->model :- model.cartao/Cartao
  [{:cartao/keys [validade cliente] :as cartao}]
  (-> cartao
      (assoc :cartao/validade (common.util/inst->year-month validade))
      (assoc :cartao/cliente (:cliente/cpf cliente))
      (dissoc :db/id)))
