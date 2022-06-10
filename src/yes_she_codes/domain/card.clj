(ns yes-she-codes.domain.card
  (:require [schema.core :as s]
            [yes-she-codes.util :as y.util]
            [yes-she-codes.domain.client :as y.client])
  (:import [java.time YearMonth]
           [java.math BigDecimal]))

(def ValidCardNumber (s/constrained s/Int (partial y.util/between-values 1 9999999999999999)))
(def ValidCvv (s/constrained s/Int (partial y.util/between-values 0 999)))

(def CardSchema {(s/optional-key :id) y.util/OptionalId
                   :number            ValidCardNumber
                   :cvv               ValidCvv
                   :expiration-date   YearMonth
                   :limit             y.util/PositiveValue
                   :client            y.client/ValidCpf})

(s/defn ->Card :- CardSchema
        [id :- y.util/OptionalId
   number :- ValidCardNumber
   cvv :- ValidCvv
   expiration-date :- YearMonth
   limit :- y.util/PositiveValue
   client :- y.client/ValidCpf
   ]

        {:id       id
   :number   number
   :cvv      cvv
   :expiration-date expiration-date
   :limit   limit
   :client  client})