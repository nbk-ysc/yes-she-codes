(ns yes-she-codes.cartao
  (:require [java-time :as t]
            [yes-she-codes.arquivo :as ysc.arquivo]))

(defn novo-cartao
  "criar uma estrutura de cartao"
  [numero cvv validade limite cliente]
  {:Numero (Long/valueOf (clojure.string/replace numero #" " ""))
   :CVV (Long/valueOf cvv)
   :Validade (t/local-date (str validade "-01"))
   :Limite (read-string limite)
   :Cliente cliente})

(defn adiciona-cartao
  "Adicionar um novo cliente"
  [item]
  (novo-cartao (get item 0 "") (get item 1 "") (get item 2 "") (get item 3 "") (get item 4 ""))
  )

(defn lista-cartoes
  "Lista os clientes"
  []
  (map adiciona-cartao (ysc.arquivo/carrega-csv "cartoes.csv")))

