(ns yes-she-codes.week2.adapter.compra
  (:require [yes-she-codes.week2.adapter.common.common :as adaptor.common]
            [yes-she-codes.week2.model.compra :as model.compra]
            [java-time :as time]))

(defn ^:private criar-record-compra
  [[data valor estabelecimento categoria cartao]]
  (model.compra/novo-record-compra (time/local-date data)
                                   (bigdec valor)
                                   estabelecimento
                                   categoria
                                   (adaptor.common/string->long cartao)))

(defn csv->compra
  [caminho-arquivo]
  (->> (adaptor.common/csv-data->vector caminho-arquivo)
       (mapv criar-record-compra)))