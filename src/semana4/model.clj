(ns semana4.model
  (:use clojure.pprint)
  (:require [schema.core :as s
             :include-macros true                           ;; cljs only
             ]
            [semana3.logica :as s.logica]))

(defn uuid [] (java.util.UUID/randomUUID))

(defn str->long [valor]
  (Long/parseLong (clojure.string/replace valor #" " "")))

(s/set-fn-validation! true)

(def Valor (s/constrained BigDecimal pos?))
(def Estabelecimento (s/constrained s/Str s.logica/pelo-menos-2-caracteres?))
(def Categoria (s/pred s.logica/categoria?))
(def Cartao (s/constrained s/Int s.logica/maior-ou-igual-a-zero-e-menor-ou-igual-a-numero-grande?))

(def CompraSchema
  {:compra/id              java.util.UUID
   :compra/data            s/Str
   :compra/valor           Valor
   :compra/estabelecimento Estabelecimento
   :compra/categoria       Categoria
   :compra/cartao          Cartao})

(s/defn nova-compra :- CompraSchema
  ([data :- s/Str, valor :- s/Str, estabelecimento :- Estabelecimento, categoria :- Categoria, cartao :- s/Str]
   (nova-compra (uuid) data valor estabelecimento categoria cartao))
  ([id :- java.util.UUID, data :- s/Str, valor :- s/Str, estabelecimento :- Estabelecimento, categoria :- Categoria, cartao :- s/Str]
   {:compra/id              id
    :compra/data            data
    :compra/valor           (bigdec valor)
    :compra/estabelecimento estabelecimento
    :compra/categoria       categoria
    :compra/cartao          (str->long cartao)}))


(def NumeroCartao (s/constrained Long s.logica/maior-ou-igual-a-zero-e-menor-ou-igual-a-numero-grande?))
(def CVV (s/constrained Long s.logica/maior-ou-igual-a-zero-e-menor-ou-igual-a-999?))
(def Limite (s/constrained BigDecimal s.logica/maior-ou-igual-a-zero?))
(def Cliente (s/constrained s/Str s.logica/cpf?))

(def CartaoSchema
  {:cartao/id       java.util.UUID
   :cartao/numero   NumeroCartao
   :cartao/cvv      CVV
   :cartao/validade s/Str
   :cartao/limite   Limite
   :cartao/cliente  Cliente})

(s/defn novo-cartao :- CartaoSchema
  ([numero :- s/Str, cvv :- s/Str, validade :- s/Str, limite :- s/Str, cliente :- Cliente]
   (novo-cartao (uuid) numero cvv validade limite cliente))
  ([id :- java.util.UUID, numero :- s/Str, cvv :- s/Str, validade :- s/Str, limite :- s/Str, cliente :- Cliente]
   {:cartao/id       id
    :cartao/numero   (str->long numero)
    :cartao/cvv      (str->long cvv)
    :cartao/validade validade
    :cartao/limite   (bigdec limite)
    :cartao/cliente  cliente}))
