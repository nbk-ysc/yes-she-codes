(ns yes-she-codes.domain.purchase
  (:require [yes-she-codes.util :as y.util]
            [java-time :as time]
            [schema.core :as s]
            [yes-she-codes.domain.card :as y.card]))


(def ValidPurchaseDate (s/constrained java.time.LocalDate
                                      (fn [date]
                                         (let [current-date (time/local-date)]
                                           (or (= date current-date) (time/before? date current-date))))))

(def ValidStore (y.util/min-characters 2))
(def ValidCategory (s/enum "Alimentação" "Automóvel" "Casa" "Educação" "Lazer" "Saúde"))

(def PurchaseSchema {(s/optional-key :id) y.util/OptionalId
                   :date                  ValidPurchaseDate
                   :value                 y.util/PositiveValue
                   :store      ValidStore
                   :category             ValidCategory
                   :card                  y.card/ValidCardNumber})


(s/defn ->Purchase :- PurchaseSchema
        [id :- y.util/OptionalId
   date :- ValidPurchaseDate
   value :- y.util/PositiveValue
   store :- ValidStore
   category :- ValidCategory
   card :- y.card/ValidCardNumber]

        {:id              id
   :date            date
   :value           value
   :store           store
   :category       category
   :card          card})

(defn valid-purchase? [purchase]
  (let [valid-date (time/after? (time/local-date) (get purchase :date))
        valid-value (and (number? (get purchase :value)) (>= (get purchase :value) 0))
        valid-store(>= (count (get purchase :store)) 2)
        category (get purchase :category)
        valid-category (or (= "Alimentação" category)
                             (= "Automóvel" category)
                             (= "Casa" category)
                             (= "Educação" category)
                             (= "Lazer" category)
                             (= "Saúde" category))]

    (and valid-date valid-value valid-store valid-category)))


(defn purchases-filter [predicate purchases]
  (vec (filter predicate purchases)))


(defn month-purchases-filter [mes purchases]
  (purchases-filter #(= mes (y.util/date-month (:date %))) purchases))


(defn total-spent [purchases]
  (reduce + (map :value purchases)))


(def total-spent-month (comp total-spent month-purchases-filter))


(defn group-spends-by-category [purchases]
  (vec (map (fn [[category purchases-da-category]]
              {:category   category
               :total-spent (total-spent purchases-da-category)})
            (group-by :category purchases))))