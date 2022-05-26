(ns yes_she_codes.semana3.compra
  (:use clojure.pprint)
  (:require [schema.core :as s]
            [yes_she_codes.semana3.logica :as y.logica]))

;todo melhorar validaçao do campo Categoria, para receber caracteres especiais

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
   cartao :- y.logica/ValidaCartao ]
  {:data data, :valor valor, :estabelecimento estabelecimento, :categoria categoria, :cartao cartao})

;Exemplo de compra
(pprint (nova-compra "2022-01-02" 345.83 "Outback" "Alimentacao" 1234123412341234))