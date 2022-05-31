(ns semana3.cliente
  (:use clojure.pprint)
  (:require [schema.core :as s
             :include-macros true                           ;; cljs only
             ]
            [semana3.logica :as s.logica]))

(s/set-fn-validation! true)

(def Nome (s/constrained s/Str s.logica/pelo-menos-2-caracteres?))
(def CPF (s/constrained s/Str s.logica/cpf?))
(def Email (s/constrained s/Str s.logica/email?))

(s/defschema ClienteSchema
  {(s/optional-key :id) (s/pred pos-int?)
   :nome                Nome
   :cpf                 CPF
   :email               Email})

(s/defn novo-cliente :- ClienteSchema
  [nome :- Nome, cpf :- CPF, email :- Email]
  {:nome  nome
   :cpf   cpf
   :email email})

(pprint (novo-cliente "Viúva Negra"
                      "333.444.555-66"
                      "viuva.casca.grossa@vingadoras.com.br"))