(ns yes-she-codes.adapter.adapter
  (:require [yes-she-codes.model.model :as m]
            [yes-she-codes.logic.util :as u]
            [java-time :as jt]))


(defn criar-cliente
  [[nome cpf email]]
  (m/novo-cliente nome cpf email))


(defn criar-cartao
  [[numero cvv validade limite cliente]]
  (m/novo-cartao numero cvv validade limite cliente))


(defn criar-compra
  [[data valor estabelecimento categoria cartao]]
  (m/nova-compra data valor estabelecimento categoria cartao))


(defn parse-input-cliente
  "retorna um vetor com os tipos primitivos adequados ao model a partir dos elementos em string"
  [[nome cpf email]]
  [nome cpf email])


(defn parse-input-cartao
  "retorna um vetor com os tipos primitivos adequados ao model a partir dos elementos em string"
  [[numero cvv validade limite cliente]]
  [(Long/parseLong (u/string-sem-espacos numero))
   (Long/parseLong cvv)
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
   (Long/parseLong (u/string-sem-espacos cartao))])


(defn dado-bruto->model
  "pipeline que retorna os dados modelados a parir da leitura de um arquivo"
  [file-path fn-parse fn-model]
  (->> (u/arquivo->vetor file-path)
       rest
       (map u/csv-splitter)
       (map fn-parse)
       (mapv fn-model)))

