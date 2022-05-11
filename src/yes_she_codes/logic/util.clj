(ns yes-she-codes.logic.util
  (:import (java.time.format DateTimeFormatter)
           (java.time LocalDate)
           (java.time YearMonth)))

(defn str->local-date
  [str]
  (LocalDate/parse str (DateTimeFormatter/ofPattern "yyyy-MM-dd")))


(defn str->year-month
  [str]
  (YearMonth/parse str (DateTimeFormatter/ofPattern "yyyy-MM")))


(defn qual-mes?
  [obj-data]
  (.getMonthValue obj-data))

