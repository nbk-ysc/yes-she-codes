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


(defn mesmo-mes?
  [obj-data mes]
  (= mes (.getMonthValue obj-data)))


(defn pertence-ao-intervalo?
  "datas máxima e mínima inclusas"
  [data-max data-min data-cmp]
  (and
    (or (.equals data-max data-cmp) (.isAfter data-max data-cmp))
    (or (.equals data-cmp data-min) (.isAfter data-cmp data-min))))

