(ns yes-she-codes.week2.adapter.cartao
  (:require [yes-she-codes.week2.adapter.common.common :as adaptor.common]
            [yes-she-codes.week2.model.cartao :as model.cartao]
            [java-time :as time]))

(defn ^:private criar-record-cartao
  [[numero cvv validade limite cliente]]
  (model.cartao/novo-record-cartao (adaptor.common/string->long numero)
                                   (adaptor.common/string->long cvv)
                                   (time/year-month validade)
                                   (bigdec limite)
                                   cliente))


; validacao será feita uma vez implementado o schema
; abrir o arquivo deve ser feito no controller?

(defn csv->cartao
  [caminho-arquivo]
  (->> (adaptor.common/csv-data->vector caminho-arquivo)
       (mapv criar-record-cartao)))