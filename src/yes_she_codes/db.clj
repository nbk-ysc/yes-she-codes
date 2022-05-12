(ns yes_she_codes.db
  (:require [yes_she_codes.logic :as y.logic]
            [yes_she_codes.model :as y.model])
  (:use clojure.pprint))

(defn lista-clientes
  []
  (let [lista-de-clientes-nao-tratados (y.logic/csv-to-map "resources/clientes.csv")
        lista-de-clientes-tratados (map y.logic/extrai-dados-clientes lista-de-clientes-nao-tratados)]

    (->> lista-de-clientes-tratados
         (map y.model/novo-cliente))))

(defn lista-cartoes
  []
  (let [lista-de-cartoes-nao-tratados (y.logic/csv-to-map "resources/cartoes.csv")
        lista-de-cartoes-tratados (map y.logic/extrai-dados-cartoes lista-de-cartoes-nao-tratados)]

    (->> lista-de-cartoes-tratados
         (map y.model/novo-cartao))))

(defn lista-compras
  []
  (let [lista-de-compras-nao-tratados (y.logic/csv-to-map "resources/compras.csv")
        lista-de-compras-tratados (map y.logic/extrai-dados-compras lista-de-compras-nao-tratados)]
    (->> lista-de-compras-tratados
         (map y.model/nova-compra))))