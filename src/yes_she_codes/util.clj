(ns yes-she-codes.util
  (:require [java-time :as time]
            [schema.core :as s]))

(defn str->long [value]
  (Long/parseLong (clojure.string/replace value #" " "")))

(defn next-id [entities]
  (if-not (empty? entities)
    (+ 1 (apply max (map :id entities)))
    1))


(defn between-values [min max value]
  (and (>= value min) (<= value max)))

(defn min-characters [n]
  (s/constrained s/Str #(>= (count %) n)))


(defn optional [schema]
  (s/maybe schema))

(def PositiveInteger (s/pred pos-int?))
(def OptionalId (optional PositiveInteger))
(def PositiveValue (s/constrained BigDecimal (comp not neg?)))


(defn local-date-time->date [value]
  (if (time/local-date-time? value)
    (->> (.atZone value (time/zone-id))
         .toInstant
         java.util.Date/from)
    value))


(defn local-date->date [value]
  (if (time/local-date? value)
    (->> (time/zone-id)
         (.atStartOfDay value)
         (.toLocalDateTime)
         (local-date-time->date))
    value))


(defn date->local-date-time [value]
  (if (= java.util.Date (class value))
    (-> (.toInstant value)
        (java.time.LocalDateTime/ofInstant (time/zone-id)))
    value))


(defn- convert-map-values [fn mapa]
  (clojure.walk/postwalk fn mapa))

(def convert-java-time-to-date
  (partial convert-map-values (comp local-date->date local-date-time->date)))

(def convert-date-to-java-time
  (partial convert-map-values date->local-date-time))


(defprotocol MonthExtractor
  (date-month [date]
    "Dada uma date, extrai o value do mês como um inteiro de 1 a 12"))

(extend-type java.lang.String MonthExtractor
  (date-month [date]
    (->> date
         (re-matches #"\d{4}-(\d{2})-\d{2}")
         second
         Long/valueOf)))

(extend-type java.time.LocalDate MonthExtractor
  (mes-da-date [date]
    (.getValue (time/month date))))

(extend-type java.util.Date MonthExtractor
  (mes-da-date [date]
    (-> date
        date->local-date-time
        .toLocalDate
        date-month)))