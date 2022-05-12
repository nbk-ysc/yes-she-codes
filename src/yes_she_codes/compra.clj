(ns yes-she-codes.compra
  (:require [java-time :as t]
            [yes-she-codes.arquivo :as ysc.arquivo]))

(def dados_compras (ysc.arquivo/carrega-csv "compras.csv"))

(defn novo-compra
  "criar uma estrutura de compra"
  [data valor estabelecimento categoria cartao]
  {:Data (t/local-date data)
   :Valor (read-string valor)
   :Estabelecimento estabelecimento
   :Categoria categoria
   :Cartao (Long/valueOf (clojure.string/replace cartao #" " ""))})

(defn adiciona-compra
  "Adicionar um novo cliente"
  [item]
  (novo-compra (get item 0 "") (get item 1 "") (get item 2 "") (get item 3 "") (get item 4 "")))

(defn lista-compras
  "Lista os clientes"
  [dados]
  (map adiciona-compra dados))

(println (lista-compras dados_compras))