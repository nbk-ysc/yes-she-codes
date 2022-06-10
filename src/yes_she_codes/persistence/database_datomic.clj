(ns yes-she-codes.persistence.database-datomic
  (:require [datomic.api :as d]
            [yes-she-codes.util :as y.util]
            [yes-she-codes.csv.csv-reader :as y.csv]))

(def schema-datomic
  [; ESQUEMA DE COMPRA
   {:db/ident       :purchase/data
    :db/valueType   :db.type/instant
    :db/cardinality :db.cardinality/one
    :db/doc         "Purchase date."}
   {:db/ident       :purchase/value
    :db/valueType   :db.type/bigdec
    :db/cardinality :db.cardinality/one
    :db/doc         "Purchase total value."}
   {:db/ident       :purchase/store
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "Store where the purchase was made."}
   {:db/ident       :purchase/category
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "Expense group to which purchase belongs."}
   {:db/ident       :purchase/card
    :db/valueType   :db.type/ref
    :db/cardinality :db.cardinality/one
    :db/doc         "Card used in the purchase."}

   {:db/ident       :card/number
    :db/valueType   :db.type/long
    :db/cardinality :db.cardinality/one
    :db/unique      :db.unique/identity
    :db/doc         "Card number."}
   {:db/ident       :card/cvv
    :db/valueType   :db.type/long
    :db/cardinality :db.cardinality/one
    :db/doc         "Card CVV"}
   {:db/ident       :card/expiration-date
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "Card CVV number"}
   {:db/ident       :card/limit
    :db/valueType   :db.type/bigdec
    :db/cardinality :db.cardinality/one
    :db/doc         "Maximum amount of purchases for a card."}
   {:db/ident       :card/client
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "Card owner."}])



(defn- db-url []
  "datomic:dev://localhost:4334/she-codes")

(defn create-connection []
  (d/create-database (db-url))
  (d/connect (db-url)))

(defn clear-database! []
  (d/delete-database (db-url)))

(defn create-schema! [conn]
  @(d/transact conn schema-datomic))


(defn card->datomic-record [card]
  (let [expiration-date-str (.toString (:expiration-date card))]
    (-> card
        (assoc :expiration-date expiration-date-str)
        (clojure.set/rename-keys {:id       :db/id
                                  :number   :card/number
                                  :cvv      :card/cvv
                                  :expiration-date :card/expiration-date
                                  :limit   :card/limit
                                  :client  :card/client}))))

(defn datomic-record->card [record]
  (-> record
      (clojure.set/rename-keys {:db/id           :id
                                :card/number   :number
                                :card/cvv      :cvv
                                :card/expiration-date :expiration-date
                                :card/limit   :limit
                                :card/client  :client})))


(defn search-card [snapshot number]
  (-> (d/q '[:find (pull ?id-card [*])
             :in $ ?number
             :where [?id-card :card/number ?number]]
           snapshot number)
      ffirst
      datomic-record->card))


(defn save-card! [conn card]
  (let [record (card->datomic-record card)]
    @(d/transact conn [record])))


(defn list-cards! [snapshot]
  (mapv datomic-record->card
        (flatten (d/q '[:find (pull ?id-card [*])
                        :where [?id-card :card/number]]
                      snapshot))))


(defn purchase->record-datomic [purchase]
  (-> purchase
      (clojure.set/rename-keys {:id              :db/id
                                :date            :purchase/date
                                :value           :purchase/value
                                :card          :purchase/card
                                :category       :purchase/category
                                :store :purchase/store})
      y.util/convert-java-time-to-date))


(defn record-datomic->purchase [record]
  (-> record
      (clojure.set/rename-keys {:db/id                  :id
                                :purchase/date            :date
                                :purchase/value           :value
                                :purchase/card          :card
                                :purchase/category       :category
                                :purchase/store :store})
      y.util/convert-date-to-java-time))


(defn save-purchase! [conn purchase]
  (some->> (:card purchase)
       (search-card (d/db conn))
       :id
       (assoc purchase :card)                               
       (purchase->record-datomic)
       (conj [])
       (d/transact conn)
       deref))


(defn list-purchases! [snapshot]
  (vec (map record-datomic->purchase
            (flatten (d/q '[:find (pull ?purchase [*])
                            :where [?purchase :purchase/value]]
                          snapshot)))))



(defn load-purchases-in-database! [conn]
  (mapv
    (partial save-purchase! conn)
    (y.csv/load-purchases-file!)))

(defn load-cards-in-database! [conn]
  (mapv
    (partial save-card! conn)
    (y.csv/process-cards-file!)))


(defn list-purchases-by-card [snapshot card]
  (->> (d/q '[:find (pull ?id-purchase [*])
              :in $ ?card
              :where [?id-purchase :purchase/card ?card]]
            snapshot card)
       flatten
       (map record-datomic->purchase)
       vec))


(defn filter-month [date month]
  (-> (y.util/date-month date)
      (= month)))


(defn list-expenses-by-card-and-month [snapshot card month]
  (-> '[:find (pull ?id-purchase [*])
        :in $ ?card ?month
        :where [?id-purchase :purchase/card ?card]
               [?id-purchase :purchase/data ?data]
               [(yes-she-codes.persistence.database-datomic/filter-month ?date ?month)]]
      (d/q snapshot [:card/number card] month) 
      flatten
      (#(mapv record-datomic->purchase %))))


(def list-expenses-by-category!
  (comp (partial group-by :category) list-purchases!))


(defn load-database! []
  (clear-database!)
  (let [connection (create-connection)]
    (create-schema! connection)
    (load-cards-in-database! connection)
    (load-purchases-in-database! connection)))