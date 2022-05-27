(ns yes_she_codes.cartoes
  (:require [yes_she_codes.db :as y.db]))

(defn novo-cartao [numero cvv validade limite cliente]
  {:numero   numero,
   :cvv      cvv,
   :validade validade,
   :limite   limite,
   :cliente  cliente})

(defn transforma-cartao [[numero cvv validade limite cliente]]
  (novo-cartao numero cvv validade limite cliente))

(defn transforma-cartoes [cartoes]
  (map transforma-cartao cartoes))

(defn lista-cartoes []
  (transforma-cartoes (y.db/registros-de-cartoes)))
