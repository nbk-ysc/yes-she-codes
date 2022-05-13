(ns yes-she-codes.cartoes
  (:require [clojure.pprint]
            [yes-she-codes.db :as y.db]
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

(def cartoes (lista-cartoes y.db/cartoes))
(def meus-cartoes (listar-cartoes-csv "/Users/marta.lima/Desktop/YSC/yes-she-codes/src/yes_she_codes/cartoes.csv"))

(pprint "Função listar cartões a partir do db")
(pprint cartoes)

(pprint "Função listar cartões a partir do csv")
(pprint meus-cartoes)
