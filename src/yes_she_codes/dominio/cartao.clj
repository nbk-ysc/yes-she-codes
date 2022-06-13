(ns yes-she-codes.dominio.cartao
  (:require [schema.core :as s]
            [yes-she-codes.logic :as logic]))

(def CartaoSchema
  {(s/optional-key :id) logic/IdOpcional
   :numero (s/constrained s/Num logic/validate-numero) ,
   :cvv (s/constrained s/Num logic/validate-cvv),
   :validate (s/constrained s/Str logic/validate-data-cartao),
   :limite (s/constrained s/Num pos?),
   :cliente s/Str})


(s/defn ->Cartao :- CartaoSchema
  [id :- (s/maybe s/Num)
   numero :- s/Num
   cvv :- s/Num
   validade :- s/Str
   limite :- s/Num
   cliente :- s/Str]

  {:id       id
   :numero   numero
   :cvv      cvv
   :validate validade
   :limite   limite
   :cliente  cliente})
