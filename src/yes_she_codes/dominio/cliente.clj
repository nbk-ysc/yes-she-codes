(ns yes-she-codes.dominio.cliente
  (:require [schema.core :as s]
            [yes-she-codes.logic :as v]))

(def ClienteSchema
  {(s/optional-key :id) v/IdOpcional
   :nome (s/constrained s/Str v/validate-string) ,
   :cpf (s/constrained s/Str v/validate-cpf),
   :email (s/constrained s/Str v/validate-email)})


(s/defn ->Cliente :- ClienteSchema
  [id :- (s/maybe s/Num)
   nome :- s/Str
   cpf :- s/Str
   email :- s/Str]
  {:id    id
   :nome  nome
   :cpf   cpf
   :email email})
