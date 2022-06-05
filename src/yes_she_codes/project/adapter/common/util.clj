(ns yes-she-codes.project.adapter.common.util
  (:require [schema.core :as s])
  (:import [java.time LocalDate Instant ZoneOffset YearMonth]
           [java.util Date UUID]
           [java.time.format DateTimeFormatter]))

(defn uuid [] (UUID/randomUUID))

(s/defn str->local-date
  [str-data :- (s/pred #(re-matches #"\d{4}-\d{2}-\d{2}" %))]
  (LocalDate/parse str-data))

(defn local-date->string
  [local-date]
  (let [formatter DateTimeFormatter/ISO_DATE]
    (.format local-date formatter)))

(defn local-date->inst
  [local-date]
  (-> local-date
      .atStartOfDay
      (.toInstant ZoneOffset/UTC)
      .toEpochMilli
      Date.))

(defn inst->local-date
  [date]
  (-> (.getTime date)
      Instant/ofEpochMilli
      (LocalDate/ofInstant ZoneOffset/UTC)))

(defn local-date->year-month [local-date]
  (YearMonth/from local-date))

(defn year-month->inst
  [year-month]
  (local-date->inst (.atDay year-month 1)))

(defn inst->year-month
  [date]
  (-> date
      inst->local-date
      local-date->year-month))

(defn num->string
  [valor]
  (str valor))