(ns yes_she_codes.semana3.model
  (:use clojure.pprint)
  (:require [schema.core :as s]
            [yes_she_codes.semana3.validators :as ysc.validators]))

(require '[java-time :as time])

(s/set-fn-validation! true)

(def StrGTE2
  (s/pred ysc.validators/str->-2?))

(def Cpf
  (s/pred ysc.validators/formato-cpf?))

;; https://stackoverflow.com/questions/33736473/how-to-validate-email-in-clojure
(def Email
  (s/pred ysc.validators/formato-email?))

(def NumeroCartao
  (s/pred ysc.validators/numero-cartao-valido?))

(def Cvv
  (s/pred ysc.validators/numero-0-999?))

(def Validade
  (s/pred time/year-month?))

(def BigDecPositivo
  (s/pred ysc.validators/pos-bigdec?))

(def DataCompra
  (s/pred ysc.validators/data-valida?))

(def Categoria
  (s/pred ysc.validators/categoria-pertence?))

        ;;SCHEMAS
(def ClienteSchema
  {:nome  StrGTE2
   :cpf   Cpf
   :email Email})

(def CartaoSchema
  {:numero    NumeroCartao
   :cvv       Cvv
   :validade  Validade
   :limite    BigDecPositivo
   :cliente   Cpf})

(def CompraSchema
  {:data              DataCompra
   :valor             BigDecPositivo
   :estabelecimento   StrGTE2
   :categoria         Categoria
   :cartao            NumeroCartao})
