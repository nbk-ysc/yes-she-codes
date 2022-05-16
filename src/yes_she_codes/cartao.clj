(ns yes-she-codes.cartao
  (:use clojure.pprint)
  (:require [yes-she-codes.db :as y.db]))

;DEFINE UM NOVO CARTAO
(defn novo-cartao [numero cvv validade limite cliente]
  {:numero numero
   :cvv cvv
   :validade validade
   :limite limite
   :cliente cliente})

;(pprint (novo-cartao 4321432143214325 021 "2026-08" 8000 "111222333014"))

;ADICIONA UM NOVO CARTAO NA LISTA
(defn add-cartao [numero cvv validade limite cliente]
  (conj y.db/cartoes (novo-cartao numero cvv validade limite cliente)))

;(pprint (add-cartao 4321432143214325 021 "2026-08" 8000 "111222333014"))

;LISTA OS CARTOES
(defn lista-cartoes []
  (pprint y.db/cartoes))

;(lista-cartoes)