(ns yes_she_codes.semana3.cliente
  (:use clojure.pprint)
  (:require [schema.core :as s]
            [yes_she_codes.semana3.logica :as y.logica]))

;todo criar validacao para o campo nome (tem q ter no minimo 2 caracteres
;todo criar validacao para o campo cpf (deve seguir o formato 000.000.000-00)
;todo criar validacao para o campo email (seguindo o formato blabla@blabal.com)

; Força para q sempre valide os dados passados para os esquemas
(s/set-fn-validation! true)

(def ClienteSchema
  "Schema de um cliente"
  { :nome s/Str, :cpf s/Num, :email s/Str})

(s/defn novo-clinte :- ClienteSchema
  [nome :- s/Str, cpf :- s/Num, email :- s/Str ]
  {:nome nome,
   :cpf cpf,
   :email email})

(pprint (novo-clinte "Caroline" 185451284 "caroline@nubank.com.br"))


#_(defn lista-clientes [lista-dados-clientes]
  (mapv novo-cliente lista-dados-clientes))