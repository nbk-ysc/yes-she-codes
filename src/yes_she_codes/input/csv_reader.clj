(ns yes-she-codes.input.csv-reader
  (:require [yes-she-codes.adapter.adapter :as a]))

(defn lista-clientes []
  (a/dado-bruto->model
    "data/clientes.csv"
    a/parse-input-cliente
    a/criar-cliente))


(defn lista-cartoes []
  (a/dado-bruto->model
    "data/cartoes.csv"
    a/parse-input-cartao
    a/criar-cartao))


(defn lista-compras []
  (a/dado-bruto->model
    "data/compras.csv"
    a/parse-input-compra
    a/criar-compra))
