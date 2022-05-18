(ns yes-she-codes.week2.adapter.cliente
  (:require [yes-she-codes.week2.adapter.common.common :as addaptor.common]
            [yes-she-codes.week2.model.cliente :as model.cliente]))

(defn ^:private criar-record-cliente
  [[nome cpf email]]
  (model.cliente/novo-record-cliente nome cpf email))

(defn csv->cliente
  [caminho-arquivo]
  (->> (addaptor.common/csv-data->vector caminho-arquivo)
       (mapv criar-record-cliente)))
