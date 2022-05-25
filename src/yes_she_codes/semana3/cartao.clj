(ns yes_she_codes.semana3.cartao
  (:use clojure.pprint)
  (:require [schema.core :as s]
            [yes_she_codes.semana3.logica :as y.logica]))

;todo criar validaçao para o campo numero do cartao (inteiro entre 0 e 1 0000 0000 0000 0000)
;todo criar validaçao para o campo cvv (inteiro entre 0 e 999)
;todo criar validaçao para o campo validade (formato yyyy-mm)
;todo criar validaçao para o campo limite (BigDecinal maior ou igual a 0)
;todo criar validaçao para o campo cliente (String no formato 000.000.000-00)

; Força para q sempre valide os dados passados para os esquemas
(s/set-fn-validation! true)

(def CartaoSchema
  "Schema de um cartao"
  { :num-cartao s/Num, :cvv s/Num, :validade s/Str, :limite s/Num, :cliente s/Str})

(s/defn novo-cartao :- CartaoSchema
  [num-cartao :- s/Num, cvv :- s/Num, validade :- s/Str, limite :- s/Num, cliente :- s/Str ]
  {:num-cartao num-cartao,
   :cvv cvv,
   :validade validade,
   :limite limite,
   :cliente cliente})