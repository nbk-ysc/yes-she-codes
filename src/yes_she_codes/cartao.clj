(ns yes-she-codes.cartao
  (:use clojure.pprint)
  (:require [yes-she-codes.db :as y.db]))

(defn novo-cartao [numero cvv validade limite cliente]
  (let [cartoes (y.db/todos-cartoes)]
    (conj cartoes {:numero numero
                   :cvv cvv
                   :validade validade
                   :limite limite
                   :cliente cliente})))

(pprint (novo-cartao 4321432143214325 021 "2026-08" 8000 "111222333014"))

(defn lista-cartoes []
  (pprint (y.db/todos-cartoes)))

(lista-cartoes)