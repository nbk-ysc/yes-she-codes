(ns yes-she-codes.models.compra
        (:require [schema.core :as s]
                  [yes-she-codes.util :as y.util]))

(defrecord Compra [id data valor estabelecimento categoria cartao])

(def CompraSchema {(s/optional-key :id) y.util/IdOpcional
                   :data                y.util/DataDeCompraValida
                   :valor               y.util/ValorPositivo
                   :estabelecimento     y.util/EstabelecimentoValido
                   :categoria           y.util/CategoriaValida
                   :cartao              y.util/NumeroDeCartaoValido})


(s/defn ->Compra :- CompraSchema
  [id              :- y.util/IdOpcional
   data            :- y.util/DataDeCompraValida
   valor           :- y.util/ValorPositivo
   estabelecimento :- y.util/EstabelecimentoValido
   categoria       :- y.util/CategoriaValida
   cartao          :- y.util/NumeroDeCartaoValido]
  {:id              id
   :data            data
   :valor           valor
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          cartao})