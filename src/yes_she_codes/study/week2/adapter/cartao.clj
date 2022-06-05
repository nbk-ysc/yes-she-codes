(ns yes-she-codes.study.week2.adapter.cartao
  (:require [yes-she-codes.study.week2.adapter.common.common :as adaptor.common]
            [yes-she-codes.study.week2.model.cartao :as model.cartao]
            [java-time :as time]))

(defn ^:private criar-record-cartao
  [[numero cvv validade limite cliente]]
  (model.cartao/map->Cartao {:numero   (adaptor.common/string->long numero)
                             :cvv      (adaptor.common/string->long cvv)
                             :validade (time/year-month validade)
                             :limite   (bigdec limite)
                             :cliente   cliente}))

(defn csv->cartao
  [caminho-arquivo]
  (->> (adaptor.common/csv-data->vector caminho-arquivo)
       (mapv criar-record-cartao)))