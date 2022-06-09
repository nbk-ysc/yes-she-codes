(ns semana4.csv
  (:require [clojure.java.io :as io]
            [clojure.data.csv :as csv]
            [java-time :as t]))

(defn csv-data->maps [csv-data]
  (map zipmap
       (->> (first csv-data)                                ;; First row is the header
            (map keyword)                                   ;; Drop if you want string keys instead
            repeat)
       (rest csv-data)))

(defn lista-csv-compras [reader]
  (->> (io/reader reader)
       (csv/read-csv)
       csv-data->maps
       (map #(update % :VALOR (fn [valor] (bigdec valor))))
       (map #(update % :DATA (fn [data] (t/local-date data))))
       (map #(update % :CARTÃO (fn [cartao] (Long/parseLong (clojure.string/replace cartao #"\s" "")))))))

(defn lista-csv-cartoes [reader]
  (->> (io/reader reader)
       (csv/read-csv)
       csv-data->maps
       (map #(update % :VALIDADE (fn [validade] (t/format "MM/yyyy" (t/year-month validade)))))))

(def lista-clientes
  (csv-data->maps (csv/read-csv (io/reader "/Users/marilia.marques/Downloads/clientes.csv"))))

(def lista-cartoes
  (lista-csv-cartoes "/Users/marilia.marques/Downloads/cartoes.csv"))

(def lista-compras
  (lista-csv-compras "/Users/marilia.marques/Downloads/compras.csv"))