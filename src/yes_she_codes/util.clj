(ns yes-she-codes.util
  (:require [java-time :as time]
            [schema.core :as s]))

(defn str->long
  [valor]
  (Long/parseLong (clojure.string/replace valor #" " "")))

(defn data-valida?
  [data]
  (re-matches #"\d{4}-(\d{2})-\d{2}" data))

(defn proximo-id
  [entidades]
  (if-not (empty? entidades)
    (+ 1 (apply max (map :id entidades)))
    1))

(defn entre-valores
  [min max valor]
  (and (>= valor min) (<= valor max)))

(defn min-caracteres
  [n]
  (s/constrained s/Str #(>= (count %) n)))

(defn opcional
  [schema]
  (s/maybe schema))

(def InteiroPositivo
  (s/pred pos-int?))

(def IdOpcional
  (opcional InteiroPositivo))

(def ValorPositivo
  (s/constrained BigDecimal (comp not neg?)))

(defn local-date-time->date
  [valor]
  (if (time/local-date-time? valor)
    (->> (.atZone valor (time/zone-id))
         .toInstant
         java.util.Date/from)
    valor))

(defn local-date->date
  [valor]
  (if (time/local-date? valor)
    (->> (time/zone-id)
         (.atStartOfDay valor)
         (.toLocalDateTime)
         (local-date-time->date))
    valor))

(defn date->local-date-time
  [valor]
  (if (= java.util.Date (class valor))
    (-> (.toInstant valor)
        (java.time.LocalDateTime/ofInstant (time/zone-id)))
    valor))

(defn- converte-valores-de-um-mapa
  [fn mapa]
  (clojure.walk/postwalk fn mapa))

(def converte-java-time-para-date
  (partial converte-valores-de-um-mapa (comp local-date->date local-date-time->date)))

(def converte-date-para-java-time
  (partial converte-valores-de-um-mapa date->local-date-time))

(defprotocol ExtratorDeMes
  (mes-da-data [data]))

(extend-type java.lang.String ExtratorDeMes
  (mes-da-data [data]
    (->> data
         (re-matches #"\d{4}-(\d{2})-\d{2}")
         second
         Long/valueOf)))

(extend-type java.time.LocalDate ExtratorDeMes
  (mes-da-data [data]
    (.getValue (time/month data))))

(extend-type java.util.Date ExtratorDeMes
  (mes-da-data [data]
    (-> data
        date->local-date-time
        .toLocalDate
        mes-da-data)))