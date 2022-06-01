(ns yes-she-codes.project.diplomat.compra
  (:require [schema.core :as s]
            [yes-she-codes.project.diplomat.csv.csv :as diplomat.csv]
            [yes-she-codes.project.adapter.compra :as adapters.compra]
            [yes-she-codes.project.controllers.compra :as controllers.compra]
            [yes-she-codes.project.db.compra :as db.compra]))


;; layer responsible for defining how to interact with external resources
;; like adapt of data, error handling...

;; only adapters (ou model)
;; don't use logic layer here!!!!

;;; verificar no consumo de csv
;;; se um dado estiver errado, vai recusar todos??? resp: VAI


;;; onde usar o atomo???? ( -_- )
;;; schema para atom????


(s/defn insere-compras-csv-no-dominio
  [filepath-dados
   compras-dominio]
  (if-let [dados (adapters.compra/csv->compras
                (diplomat.csv/read-csv filepath-dados))]
    (controllers.compra/insere-compras! compras-dominio dados)))


;;; cada vez que ele lê o csv ele atribui novos id
;;; se chamar varias vezes, adiciona o mesmo dado varias vezes
(s/defn insere-compras-csv-na-base
  [filepath-dados
   conn]
  (if-let [dados (adapters.compra/csv->compras
                   (diplomat.csv/read-csv filepath-dados))]
    (db.compra/carrega-compras-no-banco! conn dados)))



