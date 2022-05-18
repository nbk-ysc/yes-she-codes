(ns yes-she-codes.compra
  (:require [java-time :as t]
            [yes-she-codes.arquivo :as ysc.arquivo]))

(defn novo-compra
  "criar uma estrutura de compra"
  [data valor estabelecimento categoria cartao]
  {:Data (t/local-date data)
   :Valor (bigdec valor)
   :Estabelecimento estabelecimento
   :Categoria categoria
   :Cartao (Long/valueOf (clojure.string/replace cartao #" " ""))})

(defn adiciona-compra
  "Adicionar um novo cliente"
  [item]
  (novo-compra (get item 0 "") (get item 1 "") (get item 2 "") (get item 3 "") (get item 4 "")))

(defn lista-compras
  "Lista os clientes"
  []
  (map adiciona-compra (ysc.arquivo/carrega-csv "compras.csv")))

