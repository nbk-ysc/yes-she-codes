(ns yes-she-codes.project.diplomat.cartao
  (:require [schema.core :as s]
            [yes-she-codes.project.diplomat.csv.csv :as diplomat.csv]
            [yes-she-codes.project.adapter.cartao :as adapters.cartao]
            [yes-she-codes.project.controllers.cartao :as controllers.cartao]
            [yes-she-codes.project.db.cartao :as db.cartao]))


;(s/defn insere-cartoes-csv-no-dominio
;  [filepath-dados
;   cartoes-dominio]
;  (if-let [dados (adapters.cartao/csv->cartoes
;                   (diplomat.csv/read-csv filepath-dados))]
;    (controllers.cartao/insere-cartoes! cartoes-dominio dados)))


(s/defn insere-cartoes-csv-na-base
  [filepath-dados
   conn]
  (if-let [dados (adapters.cartao/csv->cartoes
                   (diplomat.csv/read-csv filepath-dados))]
    (db.cartao/carrega-cartoes-no-banco! conn dados)))




