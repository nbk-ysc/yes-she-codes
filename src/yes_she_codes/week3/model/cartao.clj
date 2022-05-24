(ns yes-she-codes.week3.model.cartao
  (:require [schema.core :as s])
  (:import (java.time YearMonth)))

(defrecord Cartao [^Long       id
                   ^Long       numero
                   ^Long       cvv
                   ^YearMonth  validade
                   ^BigDecimal limite
                   ^String     cliente])


(def CartaoSchema
  {:id       java.lang.Long
   :numero   (s/conditional #(<= 0 % 10000000000000000) java.lang.Long)
   :cvv      (s/conditional #(<= 0 % 999) java.lang.Long)
   :validade java.time.YearMonth
   :limite   (s/conditional #(<= 0 %) java.lang.Double)
   :cliente  (s/conditional #(re-matches #"\d{3}.\d{3}.\d{3}-\d{2}" %) s/Str)})

;Tarefa
;
;Definir o símbolo CartaoSchema que estabelece o schema do que é considerado um cartão válido.
;
;Regras de integridade:

;Número:
;inteiro entre 0 e 1 0000 0000 0000 0000.

;CVV:
;inteiro entre 0 e 999.

;Validade:
;se estiver usando String, deve ter o formato "yyyy-mm".
;se estiver usando Java Time, deve ser uma instância de java.time.Month.

;Limite:
;BigDecimal maior ou igual a zero.

;Cliente:
;String com o formato 000.000.000-00.