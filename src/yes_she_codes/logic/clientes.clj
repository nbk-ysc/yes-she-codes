(ns yes-she-codes.logic.clientes
  (:require [schema.core :as s]
            [yes-she-codes.db.db :as y.db]
            [yes-she-codes.models.clientes :as y.models.clientes]
            [yes-she-codes.models.common :as y.models.common]))

(s/set-fn-validation! true)

(s/defn novo-cliente :- y.models.clientes/ClienteSchema
  [nome :- y.models.clientes/NomeSchema,
   cpf :- y.models.common/CPFSchema,
   email :- y.models.clientes/EmailSchema]
  {:nome  nome,
   :cpf   cpf,
   :email email})

; To do: Colocar schema no parâmetro
(s/defn transforma-cliente :- y.models.clientes/ClienteSchema [[nome cpf email]]
  (novo-cliente nome cpf email))

; To do: Colocar schema no parâmetro
(s/defn transforma-clientes :- y.models.clientes/ClientesSchema [clientes]
  (map transforma-cliente clientes))

(s/defn lista-clientes :- y.models.clientes/ClientesSchema []
  (transforma-clientes (y.db/registros-de-clientes)))
