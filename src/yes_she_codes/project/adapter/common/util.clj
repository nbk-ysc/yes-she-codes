(ns yes-she-codes.project.adapter.common.util
  (:import [java.time LocalDate Instant ZoneOffset YearMonth DayOfWeek ZoneId Month MonthDay]
           [java.util Date UUID]))

(defn uuid [] (UUID/randomUUID))

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

