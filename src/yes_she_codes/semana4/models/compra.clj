(ns yes_she_codes.semana4.models.compra
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [clojure.string :as str]
            [schema.core :as s]
            [java-time :as time]
            [yes_she_codes.semana4.utils :as y.utils]
            [yes_she_codes.semana4.models.cartao :as y.cartao]))

(s/set-fn-validation! true)

(def DataCompra
  (s/pred y.utils/data-valida?))

(def Categoria
  (s/pred y.utils/categoria-pertence?))

(def CompraSchema
  {:data              y.utils/StrGTE2                       ;STRING
   :valor             y.utils/BigDecPositivo
   :estabelecimento   y.utils/StrGTE2
   :categoria         Categoria
   :cartao            y.cartao/NumeroCartao})

(s/defn nova-compra :- CompraSchema
  [data :- y.utils/StrGTE2                                  ;STRING
   valor :- y.utils/BigDecPositivo
   estabelecimento :- y.utils/StrGTE2
   categoria :- Categoria
   cartao :- y.cartao/NumeroCartao]

  {:data            data
   :valor           valor
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          cartao})


(defn transforma-compra-date
  [[data-string valor estabelecimento categoria cartao-espaco]]
  (let [data (time/local-date data-string)
        valor (bigdec valor)
        cartao (long (bigdec (y.utils/limpa-whitespace cartao-espaco)))]
    (nova-compra data valor estabelecimento categoria cartao)))

(defn transforma-compra
  [[data-string valor estabelecimento categoria cartao-espaco]]
  (let [valor (bigdec valor)
        cartao (long (bigdec (y.utils/limpa-whitespace cartao-espaco)))]
    (nova-compra data-string valor estabelecimento categoria cartao)))