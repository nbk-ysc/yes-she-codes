(ns yes-she-codes.logic
  (:require [yes-she-codes.db :as y.db]
            [clojure.string :as s]
            [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [java-time :as time])
  (:import (java.text Normalizer Normalizer$Form)))


(let [clientes (y.db/todos-clientes)
      cartoes (y.db/todos-cartoes)
      compras (y.db/todas-compras)]
  (println "clientes ---" (sort-by :nome clientes))
  (println "cartoes ---" (sort-by :cliente cartoes))
  (println "compras ---" (reverse (sort-by :cartao compras)))
)





(println "\n\n ---------------- str-to-float ---------------- ")
(defn str-to-float
  [string]
  (if (string? string)
           (->> string
                (remove #{\ \.\-})
                (s/join)
                (Long/valueOf))
           string))





(println "\n\n ---------------- validar cliente ---------------- ")
(defn validar-cliente
  [cliente]
  (let [clientes (y.db/todos-clientes)]
    (some #{cliente} (map :cpf clientes)))
  )





(println "\n\n ---------------- csv reader ---------------- ")

(defn remover-acentos
  [str]
  (let [normalized (Normalizer/normalize str Normalizer$Form/NFD)]
    (s/replace normalized #"\p{InCombiningDiacriticalMarks}+" "")))


(defn csv-data-to-maps
  [csv-data]
  (mapv zipmap
        (->> (first csv-data)
             (map s/lower-case)
             (map remover-acentos)
             (map keyword)
             repeat)
        (rest csv-data)))


(defn csv-reader
  [arquivo]
  (with-open [reader (io/reader arquivo)]
    (let [read-file (csv/read-csv reader)
          map (csv-data-to-maps read-file)]
      map)))


(let [file "/Users/maria.carneiro/Documents/yes-she-codes-alura/yes-she-codes/src/yes_she_codes/csv/cartoes.csv"]
  (println "CARTOES" (csv-reader file)))


(let [file "/Users/maria.carneiro/Documents/yes-she-codes-alura/yes-she-codes/src/yes_she_codes/csv/clientes.csv"]
  (println "CLIENTES" (csv-reader file)))

(let [file "/Users/maria.carneiro/Documents/yes-she-codes-alura/yes-she-codes/src/yes_she_codes/csv/compras.csv"]
  (println "COMPRAS" (csv-reader file)))





















