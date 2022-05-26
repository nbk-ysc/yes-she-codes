(ns yes_she_codes.semana3.cartao
  (:use clojure.pprint)
  (:require [schema.core :as s]
            [yes_she_codes.semana3.logica :as y.logica]))

; Força para q sempre valide os dados passados para os esquemas
(s/set-fn-validation! true)

(def CartaoSchema
  "Schema de um cartao"
  {:num-cartao y.logica/ValidaCartao,
   :cvv        y.logica/ValidaCVV,
   :validade   y.logica/ValidaValidade,
   :limite     y.logica/ValidaLimite,
   :cliente    y.logica/ValidaCpf})

(s/defn novo-cartao :- CartaoSchema
  [num-cartao :- y.logica/ValidaCartao,
   cvv :- y.logica/ValidaCVV,
   validade :- y.logica/ValidaValidade,
   limite :- y.logica/ValidaLimite,
   cliente :- y.logica/ValidaCpf ]
  {:num-cartao num-cartao, :cvv cvv, :validade validade, :limite limite, :cliente cliente})

;exemplo de novo cartao
(pprint (novo-cartao 1234123412341234 206 "2022-03" 100 "123.123.123-12"))