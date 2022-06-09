(ns semana4.util
  (:require [java-time :as t])
  (:import (java.util Date)
           (java.time LocalDateTime)))

(defn local-date-time->date [valor]
  (if (t/local-date-time? valor)
    (->> (.atZone valor (t/zone-id))
         .toInstant
         Date/from)
    valor))

(defn local-date->date [valor]
  (if (t/local-date? valor)
    (->> (t/zone-id)
         (.atStartOfDay valor)
         (.toLocalDateTime)
         (local-date-time->date))
    valor))

(defn date->local-date-time [valor]
  (if (= Date (class valor))
    (-> (.toInstant valor)
        (LocalDateTime/ofInstant (t/zone-id)))
    valor))

(defn- converte-valores-mapa [fn mapa]
  (clojure.walk/postwalk fn mapa))

(def converte-java-time-para-util-date
  (partial converte-valores-mapa (comp local-date->date local-date-time->date)))

(def converte-util-date-para-java-time
  (partial converte-valores-mapa date->local-date-time))