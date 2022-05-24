(ns yes-she-codes.week3.adapter.cliente
  (:require [yes-she-codes.week3.adapter.common.common :as addaptor.common]
            [yes-she-codes.week3.model.cliente :as model.cliente]))

(defn ^:private criar-record-cliente
  [[nome cpf email]]
  (model.cliente/map->Cliente {:nome  nome
                               :cpf   cpf
                               :email email}))

(defn csv->cliente
  [caminho-arquivo]
  (->> (addaptor.common/csv-data->vector caminho-arquivo)
       (mapv criar-record-cliente)))
