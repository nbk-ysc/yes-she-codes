(ns yes_she_codes.semana3.cliente
  (:use clojure.pprint)
  (:require [schema.core :as s]
            [yes_she_codes.semana3.logica :as y.logica]))

; Força para q sempre valide os dados passados para os esquemas
(s/set-fn-validation! true)

(def ClienteSchema
  "Schema de um cliente"
  { :nome y.logica/ValidaNome, :cpf y.logica/ValidaCpf, :email y.logica/ValidaEmail})

(s/defn novo-clinte :- ClienteSchema
  [nome :- y.logica/ValidaNome, cpf :- y.logica/ValidaCpf, email :- y.logica/ValidaEmail]
  {:nome nome,
   :cpf cpf,
   :email email})

(pprint (novo-clinte "Caroline" "123.123.123-12" "caroline@nubank.com.br"))

