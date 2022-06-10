(ns yes-she-codes.persistence.database-atoms
  (:require [yes-she-codes.domain.purchase :as y.purchase]
            [yes-she-codes.domain.client :as y.client]
            [yes-she-codes.domain.card :as y.card]
            [yes-she-codes.util :as y.util]
            [yes-she-codes.csv.csv-reader :as y.csv]
            [java-time :as time]))

(def clients-repository (atom []))
(def cards-repository (atom []))
(def purchases-repository (atom []))



(defn- insert-entity [entities entity]
  (let [entity-id (y.util/next-id entities)
        entity-with-id(assoc entity :id entity-id)]
    (conj entities entity-with-id)))


(defn- search-by-id [entities id]
  (first (filter #(= id (:id %)) entities)))


(defn- should-remove? [id entity]
  (->> entity
       :id
       (= id)))


(defn- delete-entity [entities id]
  (remove #(should-remove? id %) entities))


(defn- load-records! [insert records]
  (doseq [record records]
        (insert record)))



(defn insert-purchase! [purchase]
  (if (y.purchase/valid-purchase? purchase)
    (swap! purchases-repository insert-entity purchase)
    (throw (ex-info "invalid purchase!" {:purchase purchase}))))


(defn- load-purchases-in-atom []
  (load-records! insert-purchase! (y.csv/process-purchases-file!)))


(defn delete-purchase! [id]
  (swap! purchases-repository delete-entity id))


(defn search-purchase-by-id! [id]
  (search-by-id @purchases-repository id))


(defn purchases-list! []
  @purchases-repository)



(defn insert-client! [client]
  (swap! clients-repository insert-entity client))


(defn- load-clients-in-atom! []
  (load-records! insert-client! (y.csv/process-clients-file!)))


(defn search-client-by-id! [id]
  (search-by-id @clients-repository id))


(defn list-clients! []
  @clients-repository)


(defn delete-client! [id]
  (swap! clients-repository delete-entity id))



(defn insert-card! [card]
  (swap! cards-repository insert-entity card))


(defn- load-cards-in-atom! []
  (load-records! insert-card! (y.csv/process-cards-file!)))


(defn search-card-by-id! [id]
  (search-by-id @cards-repository id))


(defn list-cards! []
  @cards-repository)


(defn delete-card! [id]
  (swap! cards-repository delete-entity id))



(defn clear-database []
  (doseq [atom [clients-repository cards-repository purchases-repository]]
    (swap! atom (fn [entities]
                   []))))

(defn load-database! []
  (clear-database!)
  (load-clients-in-atom!)
  (load-cards-in-atom!)
  (load-purchases-in-atom))