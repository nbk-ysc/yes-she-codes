(ns yes-she-codes.dominio.compra
  (:require [schema.core :as s]
            [yes-she-codes.logic :as v]))


(def CompraSchema
  {(s/optional-key :id) v/IdOpcional
   :data (s/constrained s/Str v/validate-data) ,
   :valor (s/constrained s/Num pos?) ,
   :estabelecimento (s/constrained s/Str v/validate-string),
   :categoria (s/constrained s/Str v/validate-categoria),
   :cartao (s/constrained s/Num v/validate-numero)})


(s/defn ->Compra :- CompraSchema
  [id :- (s/maybe s/Num)
   data :- s/Str
   valor :- s/Num
   estabelecimento :- s/Str
   categoria :- s/Str
   cartao :- s/Num]

  {:id              id
   :data            data
   :valor           valor
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          cartao})
