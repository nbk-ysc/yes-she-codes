(ns yes-she-codes.project.adapter.compra
  (:require [schema.core :as s]
            [yes-she-codes.project.model.compra :as model.compra]
            [yes-she-codes.project.adapter.cartao :as adapter.cartao]
            [yes-she-codes.project.wire.in.csv :as in.csv]
            [yes-she-codes.project.adapter.csv.common :as csv.common]
            [java-time :as time]))

(s/defn str->data :- model.compra/Data
  [data :- s/Str]
  (time/local-date data))

(s/defn ^:private csv-map->model :- model.compra/Compra
  [{:keys [data valor estabelecimento categoria cartao]} :- in.csv/CsvMapa]
  {:data            (str->data data)
   :valor           (adapter.cartao/str->valor-financeiro valor)
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          (adapter.cartao/str->cartao cartao)})

(s/defn ^:private csv-maps->model :- model.compra/Compras
  [csv-maps :- in.csv/CsvMapas]
  (mapv csv-map->model csv-maps))

(s/defn csv->model :- model.compra/Compras
  [csv-data :- in.csv/RawCsv]
  (vec (-> csv-data
           csv.common/csv-data->maps
           csv-maps->model)))