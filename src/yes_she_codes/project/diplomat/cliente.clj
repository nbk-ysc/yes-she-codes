(ns yes-she-codes.project.diplomat.cliente
  (:require [schema.core :as s]
            [yes-she-codes.project.diplomat.csv.csv :as diplomat.csv]
            [yes-she-codes.project.adapter.cliente :as adapters.cliente]
            [yes-she-codes.project.db.cliente :as db.cliente]))

(s/defn insere-clientes-csv-na-base
        [filepath-dados
         conn]
        (if-let [dados (adapters.cliente/csv->clientes
                         (diplomat.csv/read-csv filepath-dados))]
          (db.cliente/carrega-clientes-no-banco! conn dados)))
