(ns yes-she-codes.week2.adapter.compra
  (:require [yes-she-codes.week2.adapter.common.common :as adaptor.common]
            [yes-she-codes.week2.model.compra :as model.compra]
            [java-time :as time]))

(defn ^:private criar-record-compra
  [[data valor estabelecimento categoria cartao]]
  (model.compra/map->Compra {:data            (time/local-date data)
                             :valor           (bigdec valor)
                             :estabelecimento estabelecimento
                             :categoria       categoria
                             :cartao          (adaptor.common/string->long cartao)}))

(defn csv->compra
  [caminho-arquivo]
  (->> (adaptor.common/csv-data->vector caminho-arquivo)
       (mapv criar-record-compra)))