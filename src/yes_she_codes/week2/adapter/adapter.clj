(ns yes-she-codes.week2.adapter.adapter
  (:require [yes-she-codes.week2.model.model :as m]
            [yes-she-codes.week2.logic.util :as u]
            [java-time :as jt]))


(defn criar-record-cliente
  "cria um record de cliente a partir dos parametros brutos"
  [[nome cpf email]]
  (m/novo-record-cliente nome cpf email))


(defn criar-record-cartao
  "cria um record de cartão a partir dos parametros brutos"
  [[numero cvv validade limite cliente]]
  (m/novo-record-cartao  (u/string->long numero)
                         (u/string->long cvv)
                         (jt/year-month validade)
                         (bigdec limite)
                         cliente))


(defn criar-record-compra
  "cria um record de compra partir dos parametros brutos"
  [[data valor estabelecimento categoria cartao]]
  (m/novo-record-compra  (jt/local-date data)
                         (bigdec valor)
                         estabelecimento
                         categoria
                         (u/string->long cartao)))


(defn dado-bruto->model
  "pipeline que retorna os dados modelados a parir da leitura de um arquivo"
  [caminho-arquivo fn-model]
  (->> (u/csv-data->vector caminho-arquivo)
       (mapv fn-model)))


(defn lista-record-cliente []
  (dado-bruto->model
    "data/clientes.csv"
    criar-record-cliente))


(defn lista-record-cartao []
  (dado-bruto->model
    "data/cartoes.csv"
    criar-record-cartao))


(defn lista-record-compra []
  (dado-bruto->model
    "data/compras.csv"
    criar-record-compra))