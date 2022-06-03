(ns yes-she-codes.semana4.compra
  (:require [schema.core :as s])
  (:use [clojure.pprint]))

; Força para q sempre valide os dados passados para os esquemas
(s/set-fn-validation! true)

(s/defn nova-compra
  [slug, data, valor, estabelecimento, categoria, cartao]
  {:compra/slug            slug
   :compra/data            data,
   :compra/valor           valor,
   :compra/estabelecimento estabelecimento,
   :compra/categoria       categoria,
   :compra/cartao          cartao})
