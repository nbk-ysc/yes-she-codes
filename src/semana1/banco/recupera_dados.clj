(ns semana1.banco.recupera-dados
  (:require [clojure.java.io :as io]
            [clojure.data.csv :as csv]
            [java-time :as t]))

;region importação de csv sem utilização de lib e sem conversão de valores
;(defn csv-seq [rdr]
;  (let [entries (map #(clojure.string/split % #",")
;                     (line-seq rdr))
;        header (map keyword (first entries))]
;    (map #(apply hash-map (interleave header %))
;         (rest entries))
;    ))
;
;(defn recupera-clientes []
;  (csv-seq (io/reader "/Users/marilia.marques/Downloads/clientes.csv")))
;
;(defn recupera-cartoes []
;  (csv-seq (io/reader "/Users/marilia.marques/Downloads/cartoes.csv")))
;
;(defn recupera-compras []
;  (csv-seq (io/reader "/Users/marilia.marques/Downloads/compras.csv")))
;
;
;(def lista-de-compras (recupera-compras))

;(println lista-de-compras)

;(doseq [clientes (csv-seq (clojure.java.io/reader "/Users/marilia.marques/Downloads/clientes.csv"))] (println clientes))
;endregion

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
       (map #(update % :DATA (fn [data] (t/format "dd/MM/yyyy" (t/local-date data)))))))

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
