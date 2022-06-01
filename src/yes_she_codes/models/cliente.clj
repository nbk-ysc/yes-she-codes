(ns yes-she-codes.models.cliente
  (:require [schema.core :as s]
            [yes-she-codes.util :as y.util]))

(defrecord Cliente [id nome pf email])

(def ClienteSchema {(s/optional-key :id) y.util/IdOpcional
                    :nome                y.util/NomeValido
                    :cpf                 y.util/CpfValido
                    :email               y.util/EmailValido})

(s/defn ->Cliente :- ClienteSchema
  [id    :- y.util/IdOpcional
   nome  :- y.util/NomeValido
   cpf   :- y.util/CpfValido
   email :- y.util/EmailValido]
  {:id    id
   :nome  nome
   :cpf   cpf
   :email email})