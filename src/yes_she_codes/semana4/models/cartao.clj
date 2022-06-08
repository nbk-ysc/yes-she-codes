(ns yes_she_codes.semana4.models.cartao
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [clojure.string :as str]
            [schema.core :as s]
            [java-time :as time]
            [yes_she_codes.semana4.utils :as y.utils])
  (:import (java.time YearMonth)))

(def NumeroCartao
  (s/pred y.utils/numero-cartao-valido?))

(def Cvv
  (s/pred y.utils/numero-0-999?))

(def Validade
  (s/pred time/year-month?))

(def Cpf
  (s/pred y.utils/formato-cpf?))

(def CartaoSchema
  {:numero   NumeroCartao
   :cvv      Cvv
   :validade y.utils/StrGTE2                                ;STRING
   :limite   y.utils/BigDecPositivo
   :cliente  Cpf})

(s/defn novo-cartao :- CartaoSchema
  [numero :- NumeroCartao
   cvv :- Cvv
   validade :- y.utils/StrGTE2                              ;STRING
   limite :- y.utils/BigDecPositivo
   cliente :- Cpf]

  {:numero   numero
   :cvv      cvv
   :validade validade
   :limite   limite
   :cliente  cliente})

(defn transforma-cartao-date
  [[numero-espaco cvv-string validade-string limite-string cliente]]
  (let [numero (long (bigdec (y.utils/limpa-whitespace numero-espaco)))
        cvv (long (bigdec cvv-string))
        limite (bigdec limite-string)
        validade (time/year-month validade-string)]
    (novo-cartao numero cvv validade limite cliente)))

(defn transforma-cartao
  [[numero-espaco cvv-string validade limite-string cliente]]
  (let [numero (long (bigdec (y.utils/limpa-whitespace numero-espaco)))
        cvv (long (bigdec cvv-string))
        limite (bigdec limite-string)]
    (novo-cartao numero cvv validade limite cliente)))