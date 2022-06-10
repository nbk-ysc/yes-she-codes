(ns yes-she-codes.csv.csv-reader

  (:require [java-time :as time]
            [yes-she-codes.util :as y.util]
            [yes-she-codes.domain.purchase :as y.purchase]
            [yes-she-codes.domain.client :as y.client]
            [yes-she-codes.domain.card :as y.card]))

(defn- convert-values-in-line [conversion-functions line]
  (map #(%1 %2) conversion-functions line))

(defn- process-csv [file-path]
  (->> (slurp file-path)
       clojure.string/split-lines
       rest
       (map #(clojure.string/split % #","))))

(def csv->card [y.util/str->long
                  y.util/str->long
                  time/year-month
                  bigdec
                  identity])

(def csv->purchase [time/local-date
                  bigdec
                  identity
                  identity
                  y.util/str->long])


(def create-client-without-id (partial y.client/->Client nil))
(def create-card-without-id (partial y.card/->Card nil))
(def create-purchase-without-id (partial y.purchase/->Purchase nil))


(defn process-clients-file! []
  (->> (process-csv "data/clients.csv")
       (map #(apply create-client-without-id %))))


(defn process-cards-file! []
  (->> (process-csv "data/cards.csv")
       (map #(convert-values-in-line csv->card %))
       (map #(apply create-card-without-id %))
       vec))

(defn process-purchases-file! []
  (->> (process-csv "data/purchases.csv")
       (map #(convert-values-in-line csv->purchase %))
       (map #(apply create-purchase-without-id %))
       vec))

