(ns yes-she-codes.cartoes
  (:use clojure.pprint)
  (:require [yes-she-codes.db :as y.db]
            [yes-she-codes.logic :as y.logic]))

(defn novo-cartao
  [numero cvv validade limite cliente ]
  {:NÚMERO numero :CVV cvv :VALIDADE validade :LIMITE limite :CLIENTE cliente})

(defn lista-cartoes
  [cartoes]
  (map (fn [[numero cvv validade limite cliente]]
         (novo-cartao numero cvv validade limite cliente))
       cartoes))

(defn listar-cartoes-csv
  [filepath]
  (->> (y.logic/read-csv filepath)
       y.logic/csv-data->maps
       (map (fn [csv-record]
              (update csv-record :CVV #(Long/parseLong %))))))
