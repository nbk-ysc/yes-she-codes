(ns semana4.model
  (:use clojure.pprint)
  (:require [schema.core :as s
             :include-macros true                           ;; cljs only
             ]
            [semana3.logica :as s.logica]))

(defn str->long [valor]
  (Long/parseLong (clojure.string/replace valor #" " "")))

(s/set-fn-validation! true)

(def Valor (s/constrained BigDecimal pos?))
(def Estabelecimento (s/constrained s/Str s.logica/pelo-menos-2-caracteres?))
(def Categoria (s/pred s.logica/categoria?))
(def Cartao (s/constrained s/Int s.logica/maior-ou-igual-a-zero-e-menor-ou-igual-a-numero-grande?))

(def CompraSchema
  {:data            s/Str
   :valor           Valor
   :estabelecimento Estabelecimento
   :categoria       Categoria
   :cartao          Cartao})

(s/defn nova-compra :- CompraSchema
  [data :- s/Str, valor :- s/Str, estabelecimento :- Estabelecimento, categoria :- Categoria, cartao :- s/Str]
  {:data            data
   :valor           (bigdec valor)
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          (str->long cartao)})