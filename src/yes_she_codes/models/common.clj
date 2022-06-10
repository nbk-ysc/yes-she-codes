(ns yes-she-codes.models.common
  (:require [schema.core :as s]))

(defn numero-do-cartao-valido? [numero-do-cartao]
  (and (> numero-do-cartao 0) (< numero-do-cartao 10000000000000000)))

(def NumeroDoCartaoSchema (s/constrained s/Int numero-do-cartao-valido?))

(defn cpf-valido? [cpf]
  (= (re-find #"^\d{3}\.\d{3}\.\d{3}\-\d{2}$" cpf)
     cpf))

(def CPFSchema (s/constrained s/Str cpf-valido?))

(def Nil (s/pred nil?))
