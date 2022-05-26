(ns yes-she-codes.dominio.compra-schema
  (:use clojure.pprint)
  (:require [schema.core :as s]
            [yes-she-codes.logic.logic :as y.logic]
            [java-time :as time])
  )

(s/set-fn-validation! true)

(s/def CompraSchema
  {:data            y.logic/DataCompraValido
   :valor           y.logic/ValorCompraValido
   :estabelecimento y.logic/EstabelecimentoValido
   :categoria       y.logic/CategoriaValido
   :cartao          y.logic/NumeroCartaoValido})

(s/defn nova-compra :- CompraSchema
  [data :- y.logic/DataCompraValido
   valor :- y.logic/ValorCompraValido
   estabelecimento :- y.logic/EstabelecimentoValido
   categoria :- y.logic/CategoriaValido
   cartao :- y.logic/NumeroCartaoValido]
  {:data            data
   :valor           valor
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          cartao})

;(pprint (nova-compra (time/local-date 2022 06 01), 129.90M, "Outback", "Alimentação", 1234123412341234))