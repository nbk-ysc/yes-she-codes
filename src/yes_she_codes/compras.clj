(ns yes-she-codes.compras
  (:use clojure.pprint)
  (:require [yes-she-codes.db :as y.db]
            [yes-she-codes.logic :as y.logic]))


(defn nova-compra
  [data valor estabelecimento categoria cartao]
  {:DATA data :VALOR valor :ESTABELECIMENTO estabelecimento :CATEGORIA categoria :CARTAO cartao})

(defn lista-compras
  [compras]
  (map (fn [[data valor estabelecimento categoria cartao]]
         (nova-compra data valor estabelecimento categoria cartao))
       compras))

(defn listar-compras-csv
  [filepath]
  (->> (y.logic/read-csv filepath)
       y.logic/csv-data->maps
       (map (fn [csv-record]
              (update csv-record :VALOR #(bigdec %))))))

(def minhas-compras (listar-compras-csv "/Users/marta.lima/Desktop/YSC/yes-she-codes/src/yes_she_codes/compras.csv"))
