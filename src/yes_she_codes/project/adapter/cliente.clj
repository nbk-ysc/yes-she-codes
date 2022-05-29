(ns yes-she-codes.project.adapter.cliente
  (:require [schema.core :as s]
            [yes-she-codes.project.model.cliente :as model.cliente]
            [yes-she-codes.project.adapter.csv.common :as csv.common]
            [yes-she-codes.project.wire.in.csv :as in.csv]))

(s/defn ^:private csv-map->model :- model.cliente/Cliente
  [{:keys [nome cpf email]} :- in.csv/CsvMapa]
  (when (not-any? nil? [nome cpf email])
    #:cliente{:nome  nome
              :cpf   cpf
              :email email}))

(s/defn csv->clientes :- model.cliente/Clientes
  [csv-data :- in.csv/RawCsv]
  (csv.common/csv->model
    csv-data
    csv-map->model))