(ns yes_she_codes.semana1.db
  (:require [yes_she_codes.semana1.logic :as y.logic]
            [yes_she_codes.semana1.model :as y.model]))

(defn lista-clientes
  "Funcao que retorna um vetor de hashmap com dados tratados de clientes
  relacionados a uma lista extraida de um arquivo csv"
  []
  (let [lista-de-clientes-nao-tratados (y.logic/csv-to-map "resources/clientes.csv")
        lista-de-clientes-tratados (map y.logic/extrai-dados-clientes lista-de-clientes-nao-tratados)]

    (->> lista-de-clientes-tratados
         (map y.model/novo-cliente))))

(defn lista-cartoes
  "Funcao que retorna um vetor de hashmap com dados tratados de cartoes
  relacionados a uma lista extraida de um arquivo csv"
  []
  (let [lista-de-cartoes-nao-tratados (y.logic/csv-to-map "resources/cartoes.csv")
        lista-de-cartoes-tratados (map y.logic/extrai-dados-cartoes lista-de-cartoes-nao-tratados)]

    (->> lista-de-cartoes-tratados
         (map y.model/novo-cartao))))

(defn lista-compras
  "Funcao que retorna um vetor de hashmap com dados tratados de compras
  relacionadas a uma lista extraida de um arquivo csv"
  []
  (let [lista-de-compras-nao-tratados (y.logic/csv-to-map "resources/compras.csv")
        lista-de-compras-tratados (map y.logic/extrai-dados-compras lista-de-compras-nao-tratados)]
    (->> lista-de-compras-tratados
         (map y.model/nova-compra))))