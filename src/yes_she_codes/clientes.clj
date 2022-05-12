(ns yes-she-codes.clientes
  (:use clojure.pprint)
  (:require [yes-she-codes.db :as y.db]
            [yes-she-codes.logic :as y.logic]))

(defn novo-cliente
  [nome cpf email]
  {:NOME nome :CPF cpf :EMAIL email})

(defn lista-clientes
  [clientes]
  (map (fn [[nome cpf email]]
         (novo-cliente nome cpf email))
       clientes))

(defn listar-clientes-csv
  [filepath]
  (y.logic/csv-data->maps (y.logic/read-csv filepath)))


(def meus-clientes (listar-clientes-csv "/Users/marta.lima/Desktop/YSC/yes-she-codes/src/yes_she_codes/clientes.csv"))
