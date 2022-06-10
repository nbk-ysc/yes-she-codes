(ns yes-she-codes.domain.client
  (:require [schema.core :as s]
            [yes-she-codes.util :as y.util]))


(def valid-cpf? (partial re-matches #"[0-9]{3}\.[0-9]{3}\.[0-9]{3}-[0-9]{2}"))
(def valid-email? (partial re-matches #"[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?"))

(def ValidName (y.util/min-characters 2))
(def ValidCpf (s/constrained s/Str valid-cpf?))
(def ValidEmail (s/constrained s/Str valid-email?))

(def ClientSchema {(s/optional-key :id) y.util/OptionalId
                    :name               ValidName
                    :cpf                ValidCpf
                    :email              ValidEmail})

(s/defn ->Client :- ClientSchema
        [id :- y.util/OptionalId
   name :- ValidName
   cpf :- ValidCpf
   email :- ValidEmail]
        {:id    id
   :name  name
   :cpf   cpf
   :email email})