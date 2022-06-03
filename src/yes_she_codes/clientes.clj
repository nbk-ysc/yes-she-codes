(ns yes_she_codes.clientes
  (:require [yes_she_codes.db :as y.db]
            [schema.core :as s]))

(s/set-fn-validation! true)

(defn nome-valido? [nome]
  (>= (count nome) 2))

(defn cpf-valido? [cpf]
  (= (re-find #"^\d{3}\.\d{3}\.\d{3}\-\d{2}$" cpf)
     cpf))

(defn email-valido? [email]
  (= (re-find
       #"^[a-zA-Z0-9.!#$%&'*+\/=?^_`{|}~-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?(?:\.[a-zA-Z0-9](?:[a-zA-Z0-9-]{0,61}[a-zA-Z0-9])?)*$"
       email)
     email))

(def NomeSchema (s/constrained s/Str nome-valido?))
(def CPFSchema (s/constrained s/Str cpf-valido?))
(def EmailSchema (s/constrained s/Str email-valido?))

(def ClienteSchema
  {:nome  NomeSchema,
   :cpf   CPFSchema,
   :email EmailSchema})

(def ClientesSchema [ClienteSchema])

(s/defn novo-cliente :- ClienteSchema [nome :- NomeSchema, cpf :- CPFSchema, email :- EmailSchema]
  {:nome  nome,
   :cpf   cpf,
   :email email})

; To do: Colocar schema no parâmetro
(s/defn transforma-cliente :- ClienteSchema [[nome cpf email]]
  (novo-cliente nome cpf email))

; To do: Colocar schema no parâmetro
(s/defn transforma-clientes :- ClientesSchema [clientes]
  (map transforma-cliente clientes))

(s/defn lista-clientes :- ClientesSchema []
  (transforma-clientes (y.db/registros-de-clientes)))
