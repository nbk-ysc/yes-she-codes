(ns yes-she-codes.semana3
  (:use clojure.pprint)
  (:require [schema.core :as s]
            [clojure.string :as str]))

(s/set-fn-validation! true)

(defn validate-email [email]
  (re-matches #".+\@.+\..+" email))

(defn validate-cpf [cpf]
  (re-matches #"\d{3}[\.]?\d{3}[\.]?\d{3}[-]?\d{2}" cpf))

(defn validate-string [string]
  (>= (count string) 2))

(def ClienteSchema
  "Schema de um paciente"
  {:nome (s/constrained s/Str validate-string) ,
   :cpf (s/constrained s/Str validate-cpf),
   :email (s/constrained s/Str validate-email)})

(defn validate-numero [numero]
  (and (pos? numero) (< numero 1e+16)))

(defn validate-cvv [cvv]
  (and (pos? cvv) (< cvv 999)))

(defn validate-data-cartao [validate]
  (re-matches #"\d{2}[/]?\d{4}" validate))

(def CartaoSchema
  {:numero (s/constrained s/Num validate-numero) ,
   :cvv (s/constrained s/Num validate-cvv) ,
   :validate (s/constrained s/Str validate-data-cartao),
   :limite (s/constrained s/Num pos?),
   :cliente (s/constrained s/Str validate-cpf)})

(defn validate-data [data]
  (re-matches #"[0-9]{2}[/]?[0-9]{2}[/]?[0-9]{4}" data))

(defn validate-categoria [categoria]
  (let [lista-categoria ["Alimentação", "Automovel", "Casa", "Educação", "Lazer", "Saúde"]]
    (str/includes? lista-categoria categoria)))

(def CompraSchema
  {:data (s/constrained s/Str validate-data) ,
   :valor (s/constrained s/Num pos?) ,
   :estabelecimento (s/constrained s/Str validate-string),
   :categoria (s/constrained s/Str validate-categoria),
   :cartao (s/constrained s/Num validate-numero)})


(s/defn novo-cliente :- ClienteSchema
  [nome :- s/Str, cpf :- s/Str, email :- s/Str]
  {:nome nome, :cpf cpf, :email email})

(s/defn novo-cartao :- CartaoSchema
  [numero :- s/Num, cvv :- s/Num, validate :- s/Str, limite :- s/Num, cliente :- s/Str]
  {:numero numero, :cvv cvv, :validate validate, :limite limite, :cliente cliente})

(s/defn nova-compra :- CompraSchema
  [data :- s/Str, valor :- s/Num, estabelecimento :- s/Str, categoria :- s/Str, cartao :- s/Num]
  {:data data, :valor valor, :estabelecimento estabelecimento, :categoria categoria :cartao cartao})



