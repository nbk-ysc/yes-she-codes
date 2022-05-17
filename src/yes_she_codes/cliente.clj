(ns yes-she-codes.cliente
  (:require [yes-she-codes.db :as db]))

(defn novo-cliente [nome cpf email]
  {:nome  nome
   :cpf   cpf
   :email email})

(defn lista-clientes []
  (db/processa-csv "dados/clientes.csv" (fn [[nome cpf email]]
                                          (novo-cliente nome cpf email))))