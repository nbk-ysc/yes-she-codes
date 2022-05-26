(ns yes-she-codes.project.adapter.cliente
  (:require [schema.core :as s]
            [yes-she-codes.project.model.cliente :as model.cliente]
            [yes-she-codes.project.wire.in.csv :as in.csv]))


;(s/defn csv-maps->cliente :- model.cliente/Cliente
;  [{:keys [nome cpf email]} :- in.csv/CsvMapa]
;  {:nome nome
;   :cpf cpf
;   :email email})