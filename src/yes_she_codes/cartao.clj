(ns yes-she-codes.cartao
  (:require [yes-she-codes.db :as db]
            [yes-she-codes.core :as core]))

(defn novo-cartao [numero cvv validade limite cpf-cliente]
  {:numero   (core/str-to-long numero)
   :cvv      (core/str-to-long cvv)
   :validade validade
   :limite   (bigdec limite)
   :cliente  cpf-cliente})

(defn lista-cartoes []
  (db/processa-csv "dados/cartoes.csv" (fn [[numero cvv validade limite cliente]]
                                         (novo-cartao numero cvv validade limite cliente))))