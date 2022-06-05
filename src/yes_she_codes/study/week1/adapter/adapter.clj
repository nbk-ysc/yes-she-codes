(ns yes-she-codes.study.week1.adapter.adapter
  (:require [yes-she-codes.study.week1.model.model :as m]
            [yes-she-codes.study.week1.logic.util :as u]
            [java-time :as jt]))


(defn criar-cliente
  "cria um cliente a partir dos parametros brutos"
  [[nome cpf email]]
  (m/novo-cliente nome cpf email))


(defn criar-cartao
  "cria um cartão a partir dos parametros brutos"
  [[numero cvv validade limite cliente]]
  (m/novo-cartao (u/string->long numero)
                 (u/string->long cvv)
                 (jt/year-month validade)
                 (bigdec limite)
                 cliente))


(defn criar-compra
  "cria uma compra a partir dos parametros brutos"
  [[data valor estabelecimento categoria cartao]]
  (m/nova-compra (jt/local-date data)
                 (bigdec valor)
                 estabelecimento
                 categoria
                 (u/string->long cartao)))


(defn dado-bruto->model
  "pipeline que retorna os dados modelados a parir da leitura de um arquivo"
  [caminho-arquivo fn-model]
  (->> (u/csv-data->vector caminho-arquivo)
       (mapv fn-model)))

