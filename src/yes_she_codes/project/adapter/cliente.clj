(ns yes-she-codes.project.adapter.cliente
  (:require [schema.core :as s]
            [yes-she-codes.project.model.cliente :as model.cliente]
            [yes-she-codes.project.adapter.common.csv :as common.csv]
            [yes-she-codes.project.adapter.common.util :as common.util]
            [yes-she-codes.project.wire.in.csv :as in.csv]))

(s/defn ^:private csv-map->model :- model.cliente/Cliente
  [{:keys [nome cpf email]} :- in.csv/CsvMapa]
  (when (not-any? nil? [nome cpf email])
    #:cliente{:id    (common.util/uuid)
              :nome  nome
              :cpf   cpf
              :email email}))

(s/defn csv->model :- [model.cliente/Cliente]
  [csv-data :- in.csv/RawCsv]
  (common.csv/csv->maps
    csv-data
    csv-map->model))

(s/defn model->datomic
  [cliente :- model.cliente/Cliente]
  cliente)

(s/defn datomic->model
  [cliente]
  (-> cliente
      (dissoc :db/id)))

