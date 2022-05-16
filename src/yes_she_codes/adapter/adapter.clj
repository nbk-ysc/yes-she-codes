(ns yes-she-codes.adapter.adapter
  (:require [yes-she-codes.model.model :as m]
            [yes-she-codes.logic.util :as u]
            [java-time :as jt]))


(defn criar-cliente
  "cria um cliente a partir dos parametros parseados"
  [[nome cpf email]]
  (m/novo-cliente nome cpf email))


(defn criar-cartao
  "cria um cartão a partir dos parametros parseados"
  [[numero cvv validade limite cliente]]
  (m/novo-cartao numero cvv validade limite cliente))


(defn criar-compra
  "cria uma compra a partir dos parametros parseados"
  [[data valor estabelecimento categoria cartao]]
  (m/nova-compra data valor estabelecimento categoria cartao))


(defn parse-input-cliente
  "retorna um vetor com os tipos primitivos adequados ao model a partir dos elementos em string"
  [[nome cpf email]]
  [nome cpf email])


(defn parse-input-cartao
  "retorna um vetor com os tipos primitivos adequados ao model a partir dos elementos em string"
  [[numero cvv validade limite cliente]]
  [(u/string->long numero)
   (u/string->long cvv)
   (jt/year-month validade)
   (bigdec limite)
   cliente])


(defn parse-input-compra
  "retorna um vetor com os tipos primitivos adequados ao model a partir dos elementos em string"
  [[data valor estabelecimento categoria cartao]]
  [(jt/local-date data)
   (bigdec valor)
   estabelecimento
   categoria
   (u/string->long cartao)])


(defn dado-bruto->model
  "pipeline que retorna os dados modelados a parir da leitura de um arquivo"
  [caminho-arquivo fn-parse fn-model]
  (->> (u/csv-data->vector caminho-arquivo)
       (map fn-parse)
       (mapv fn-model)))

