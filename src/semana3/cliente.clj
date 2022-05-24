(ns semana3.cliente
  (:use clojure.pprint)
  (:require [schema.core :as s
             :include-macros true                           ;; cljs only
             ]
            [semana3.logic :as s.logic]))

(s/set-fn-validation! true)

(def Nome (s/constrained s/Str s.logic/pelo-menos-2-caracteres?))
(def CPF (s/constrained s/Str s.logic/cpf?))
(def Email (s/constrained s/Str s.logic/email?))

(def ClienteSchema
  {:nome  Nome
   :cpf   CPF
   :email Email})

(pprint (s/validate ClienteSchema {:nome  "Viúva Negra"
                                   :cpf   "333.444.555-66"
                                   :email "viuva.casca.grossa@vingadoras.com.br"}))

