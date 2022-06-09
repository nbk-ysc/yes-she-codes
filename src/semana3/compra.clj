(ns semana3.compra
  (:require [schema.core :as s
             :include-macros true]
            [semana3.logic :as s3.logic])
  (:use [clojure pprint]))


(s/set-fn-validation! true)

(def CompraSchema
  "Schema de uma compra"
  {:data s3.logic/Verifica-data-completa?,
   :valor s3.logic/Valor-BigDecimal-positivo-ou-zero?,
   :estabelecimento s3.logic/Possui-2-caracteres
   :categoria s3.logic/Categoria-valida?
   :cartao s3.logic/Cartao-valido?
   })


(s/defn nova-compra :- CompraSchema
  [data :- s3.logic/Verifica-data-completa?,
   valor :- s3.logic/Valor-BigDecimal-positivo-ou-zero?,
   estabelecimento :- s3.logic/Possui-2-caracteres
   categoria :- s3.logic/Categoria-valida?
   cartao :- s3.logic/Cartao-valido?]
  {:data data, :valor valor, :estabelecimento estabelecimento, :categoria categoria, :cartao cartao})
