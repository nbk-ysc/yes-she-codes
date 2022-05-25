(ns yes_she_codes.semana3.compra
  (:use clojure.pprint)
  (:require [schema.core :as s]
            [yes_she_codes.semana3.logica :as y.logica]))

;todo criar validaçao para o campo data (yyyy-mm-dd)
;todo criar validaçao para o campo valor (BigDecinal positivo)
;todo criar validaçao para o campo Estabelecimeto (deve ter no minimo 2 caracteres)
;todo criar validaçao para o campo Categoria (entre as opçoes Alimentaçao, Automovel, Educaçao, Lazer ou Saude
;todo criar validaçao para o campo cartao ( inteiro entre 0 e 1 0000 0000 0000 0000)

; Força para q sempre valide os dados passados para os esquemas
(s/set-fn-validation! true)

(def CompraSchema
  "Schema de uma compra"
  {:data s/Str,
   :valor (s/constrained s/Num pos?),
   :estabelecimento s/Str,
   :categoria s/Str,
   :cartao s/Num})

(s/defn nova-compra :- CompraSchema
  [data :- s/Str, valor :- s/Num, estabelecimento :- s/Str, categoria :- s/Str, cartao :- s/Num ]
  {:data data,
   :valor valor,
   :estabelecimento estabelecimento,
   :categoria categoria,
   :cartao cartao})