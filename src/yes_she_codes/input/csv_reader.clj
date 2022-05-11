(ns yes-she-codes.input.csv-reader
  (:require [yes-she-codes.adapter.adapter :as a]))


(let [project-path "/Users/vitoria.galli/Documents/alura/bootcamp/yes-she-codes"]

  (defn lista-clientes []
    (a/transformar-dados-arquivo-em-model
      (str project-path "/data/clientes.csv")
      a/parse-input-cliente
      a/criar-cliente))


  (defn lista-cartoes []
    (a/transformar-dados-arquivo-em-model
      (str project-path "/data/cartoes.csv")
      a/parse-input-cartao
      a/criar-cartao))


  (defn lista-compras []
    (a/transformar-dados-arquivo-em-model
      (str project-path "/data/compras.csv")
      a/parse-input-compra
      a/criar-compra))

  )