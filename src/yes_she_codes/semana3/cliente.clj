(ns yes_she_codes.semana3.cliente
  (:require [schema.core :as s]
            [yes_she_codes.semana3.logica :as y.logica])
  (:use [clojure.pprint]))

; Força para q sempre valide os dados passados para os esquemas
(s/set-fn-validation! true)

(def ClienteSchema
  "Schema de um cliente"
  {(s/optional-key :id) (s/pred pos-int?),
   :nome                y.logica/ValidaNome,
   :cpf                 y.logica/ValidaCpf,
   :email               y.logica/ValidaEmail})

(s/defn novo-cliente :- ClienteSchema
  [nome :- y.logica/ValidaNome, cpf :- y.logica/ValidaCpf, email :- y.logica/ValidaEmail]
  {:nome  nome,
   :cpf   cpf,
   :email email})

;Exemplo de cliente
;(pprint (novo-cliente "Caroline" "123.123.123-12" "caroline@nubank.com.br"))

(pprint (s/validate ClienteSchema {:nome "Caroline" :cpf "123.123.123-12" :email "caroline@nubank.com.br"}))