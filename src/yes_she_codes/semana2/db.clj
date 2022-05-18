(ns yes_she_codes.semana2.db
  (:require [yes_she_codes.semana1.logic :as y.logic]
            [yes_she_codes.semana2.model :as y.model]))

(def repositorio-de-compras (atom []))
(def repositorio-de-clientes (atom []))

(defn lista-compras
  "Funcao que retorna um vetor de hashmap com dados tratados de compras
  relacionadas a uma lista extraida de um arquivo csv"
  []
  (let [lista-de-compras-nao-tratados (y.logic/csv-to-map "resources/compras.csv")
        lista-de-compras-tratados (map y.logic/extrai-dados-compras lista-de-compras-nao-tratados)]
    (->> lista-de-compras-tratados
         (mapv y.model/nova-compra))))

(defn lista-clientes
  "Funcao que retorna um vetor de hashmap com dados tratados de clientes
  relacionados a uma lista extraida de um arquivo csv"
  []
  (let [lista-de-clientes-nao-tratados (y.logic/csv-to-map "resources/clientes.csv")
        lista-de-clientes-tratados (map y.logic/extrai-dados-clientes lista-de-clientes-nao-tratados)]
    (->> lista-de-clientes-tratados
         (mapv y.model/novo-cliente))))