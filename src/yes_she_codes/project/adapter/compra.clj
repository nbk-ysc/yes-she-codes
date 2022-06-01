(ns yes-she-codes.project.adapter.compra
  (:require [schema.core :as s]
            [yes-she-codes.project.model.compra :as model.compra]
            [yes-she-codes.project.adapter.cartao :as adapter.cartao]
            [yes-she-codes.project.wire.in.csv :as in.csv]
            [yes-she-codes.project.adapter.common.csv :as common.csv]
            [yes-she-codes.project.adapter.common.util :as common.util]
            [java-time :as time]
            )
  )

(s/defn str->data :- model.compra/Data
  [data :- (s/pred #(re-matches #"\d{4}-\d{2}-\d{2}" %))]
  (time/local-date data))

(s/defn ^:private csv-map->model :- model.compra/Compra
  [{:keys [data valor estabelecimento categoria cartao]} :- in.csv/CsvMapa]
  (when (not-any? nil? [data valor estabelecimento categoria cartao])
    #:compra{:id              (common.util/uuid)
             :data            (str->data data)
             :valor           (adapter.cartao/str->valor-financeiro valor)
             :estabelecimento estabelecimento
             :categoria       categoria
             :cartao          (adapter.cartao/str->num-cartao cartao)}))

(s/defn csv->compras :- [model.compra/Compra]
  [csv-data :- in.csv/RawCsv]
  (common.csv/csv->model
    csv-data
    csv-map->model))

(s/defn compra->datomic
  [{:compra/keys [data] :as compra} :- model.compra/Compra]
  (-> compra
      (assoc :compra/data (common.util/local-date->inst data))))

(s/defn datomic->compra :- model.compra/Compra
  [{:compra/keys [data cartao] :as compra}]
  (-> compra
      (assoc :compra/data (common.util/inst->local-date data))
      (assoc :compra/cartao (adapter.cartao/datomic->cartao cartao))
      (dissoc :db/id)))