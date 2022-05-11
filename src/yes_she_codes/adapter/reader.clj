(ns yes-she-codes.adapter.reader
  (:require [yes-she-codes.adapter.adapter :as a]
            [clojure.java.io :as io]
            [clojure.string :as str])
  (:use [clojure.pprint])
  )

(def project-path "/Users/vitoria.galli/Documents/alura/bootcamp/yes-she-codes")

(defn arquivo->vetor [path-arquivo]
  (try
    (with-open [rdr (io/reader path-arquivo)]
      (into [] (line-seq rdr)))
    (catch Exception e
      (println "Error:" (.getMessage e))))
  )

(defn transformar-dados-arquivo-em-model
  [path-arquivo fn-parse fn-model]
  (let [input-vector (arquivo->vetor path-arquivo)]
    (->> input-vector
         rest
         (map #(str/split % #","))
         (map fn-parse)
         (mapv fn-model)))
  )

(defn lista-clientes []
  (transformar-dados-arquivo-em-model
    (str project-path "/data/clientes.csv")
    a/parse-input-cliente
    a/criar-cliente))


(defn lista-cartoes []
  (transformar-dados-arquivo-em-model
    (str project-path "/data/cartoes.csv")
    a/parse-input-cartao
    a/criar-cartao))


(defn lista-compras []
  (transformar-dados-arquivo-em-model
    (str project-path "/data/compras.csv")
    a/parse-input-compra
    a/criar-compra))


(lista-clientes)
(lista-cartoes)
(lista-compras)

