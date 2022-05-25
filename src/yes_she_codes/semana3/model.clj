(ns yes_she_codes.semana3.model
  (:require [schema.core :as s]))

(s/set-fn-validation! true)

(def regex-cpf #"^[0-9]{3}\.[0-9]{3}\.[0-9]{3}-[0-9]{2}$")
(def regex-email #"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?")
(def regex-data #"\d{4}-\d{2}")

(def categorias ["Alimentação" "Saúde" "Lazer" "Automóvel" "Lazer" "Educação" "Casa"])

(defn nome-valido? [nome] (> (count nome) 1))
(defn cpf-valido? [cpf] (re-find regex-cpf cpf))
(defn email-valido? [email] (re-find regex-email email))
(defn numero-cartao-valido? [numero] (and (>= numero 0) (<= numero 10000000000000000)))
(defn cvv-valido? [cvv] (and (>= cvv 0) (<= cvv 999)))
(defn data-validade-valida? [validade] (re-find regex-data validade))
(defn limite-valido? [limite] (and (decimal? limite) (>= limite 0)))
(defn categoria-valida? [categoria] (some #(= categoria %) categorias))
(defn data-compra-valida? [data-compra] (.before data-compra (java.util.Date.)))

(def Nome (s/constrained s/Str nome-valido?))
(def Cpf (s/pred cpf-valido?))
(def Email (s/pred email-valido?))
(def NumeroCartao (s/constrained s/Int numero-cartao-valido?))
(def Cvv (s/constrained s/Int cvv-valido?))
(def DataValidade (s/pred data-validade-valida?))
(def BigdecPositivo (s/pred limite-valido?))
(def Categoria (s/constrained s/Str categoria-valida?))
(def DataCompra (s/pred data-compra-valida?))

(def ClienteSchema
  {:nome  Nome
   :cpf   Cpf
   :email Email})

(def CartaoSchema
  {:numero   NumeroCartao
   :cvv      Cvv
   :validade DataValidade
   :limite   BigdecPositivo
   :cliente  Cpf})

(def CompraSchema
  {:data            DataCompra
   :valor           BigdecPositivo
   :estabelecimento Nome
   :categoria       Categoria
   :cartao          NumeroCartao})