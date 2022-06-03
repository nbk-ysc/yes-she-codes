(ns yes-she-codes.estudos.week4.adapter.compra
  (:require [yes-she-codes.estudos.week4.adapter.common.common :as adaptor.common]
            [yes-she-codes.estudos.week4.model.compra :as model.compra]
            [schema.core :as s]
            [java-time :as time]))

(defn uuid [] (java.util.UUID/randomUUID))

(defn ^:private criar-record-compra
  [[data valor estabelecimento categoria cartao]]
  #:compra{:id              (uuid)
           :data            (time/local-date data)
           :valor           (bigdec valor)
           :estabelecimento estabelecimento
           :categoria       categoria
           :cartao          (adaptor.common/string->long cartao)})

(s/defn csv->compra :- [model.compra/Compra]
        [caminho-arquivo :- s/Str]
  (->> (adaptor.common/csv-data->vector caminho-arquivo)
       (mapv criar-record-compra)))

