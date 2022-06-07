(ns yes-she-codes.semana4.compra
  (:require [schema.core :as s]
            [yes-she-codes.semana4.logica])
  (:use [clojure.pprint]))

; Força para q sempre valide os dados passados para os esquemas
(s/set-fn-validation! true)

(def CompraSchema
  "Schema de uma compra"
  {:data            y.logica/ValidaData,
   :valor           (s/constrained s/Num pos?),
   :estabelecimento y.logica/ValidaNome,
   :categoria       y.logica/ValidaCateg,
   :cartao          y.logica/ValidaCartao})

(s/defn nova-compra :- CompraSchema
  [data :- y.logica/ValidaData,
   valor :- (s/constrained s/Num pos?),
   estabelecimento :- y.logica/ValidaNome,
   categoria :- y.logica/ValidaCateg,
   cartao :- y.logica/ValidaCartao]
  {:data data, :valor valor, :estabelecimento estabelecimento, :categoria categoria, :cartao cartao})
