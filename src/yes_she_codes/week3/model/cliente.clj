(ns yes-she-codes.week3.model.cliente
  (:require [schema.core :as s]))

(defrecord Cliente [^Long   id
                    ^String nome
                    ^String cpf
                    ^String email])


(def ClienteSchema
  {:id    java.lang.Long
   :nome  (s/conditional #(>= (count %) 2) s/Str)
   :cpf   (s/conditional #(re-matches #"\d{3}.\d{3}.\d{3}-\d{2}" %) s/Str)
   :email (s/conditional #(re-matches #"^^\w+[\.-]?\w+@\w+[\.-]?\w+\.\w{2,3}+$" %) s/Str)})



;;; validação de regras de negócio feito a partir de schema é bastante perigoso
;;; como, por exemplo, validação de cpf
;;; schema pode ser desativado em produção