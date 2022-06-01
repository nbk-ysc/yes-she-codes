(ns yes-she-codes.models.cartao
  (:require [schema.core :as s]
            [yes-she-codes.util :as y.util])
  (:import [java.time YearMonth]))

(defrecord Cartao [id numero cvv validade limite cliente])

(def CartaoSchema {(s/optional-key :id) y.util/InteiroPositivo
                   :numero              y.util/NumeroDeCartaoValido
                   :cvv                 y.util/CvvValido
                   :validade            YearMonth
                   :limite              y.util/ValorPositivo
                   :cliente             y.util/CpfValido})

(s/defn ->Cartao :- CartaoSchema
  [id       :- y.util/IdOpcional
   numero   :- y.util/NumeroDeCartaoValido
   cvv      :- y.util/CvvValido
   validade :- YearMonth
   limite   :- y.util/ValorPositivo
   cliente  :- y.util/CpfValido]
  {:id       id
   :numero   numero
   :cvv      cvv
   :validade validade
   :limite   limite
   :cliente  cliente})