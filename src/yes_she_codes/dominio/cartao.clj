(ns yes-she-codes.dominio.cartao
  (:use clojure.pprint)
  (:require [schema.core :as s]
            [yes-she-codes.logic.logic :as y.logic]
            [java-time :as time]))

(defrecord Cartao [id numero cvv validade limite cliente])

(s/set-fn-validation! true)

(s/def CartaoSchema
  {:numero   y.logic/NumeroCartaoValido,
   :cvv      y.logic/CvvValido,
   :validade y.logic/ValidadeValido
   :limite   y.logic/LimiteValido
   :cliente  y.logic/ClienteValido})

(s/defn novo-cartao :- CartaoSchema
  [numero :- y.logic/NumeroCartaoValido,
   cvv :- y.logic/CvvValido,
   validade :- y.logic/ValidadeValido
   limite :- y.logic/LimiteValido
   cliente :- y.logic/ClienteValido]
  {:numero   numero,
   :cvv      cvv,
   :validade validade,
   :limite   limite,
   :cliente  cliente})

;(pprint (novo-cartao 1598159815981598,333, (time/local-date 2021 03),3000M ,"666.777.888-99"))