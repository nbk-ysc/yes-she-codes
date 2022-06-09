(ns yes-she-codes.util
  (:require [java-time :as time]
            [schema.core :as s]))

(defn str-to-long [valor]
  (Long/parseLong (clojure.string/replace valor #" " "")))

; outra forma de pegar a data em string sem usar o substr
(defn retorna-mes [data]
  (second (re-matches #"\d{4}-(\d{2})-\d{2}" data)))

; função intermediária genérica (assim como a insere-entidade)
; para usar nos filtros de compra
(defn filtra-compras [funcao compras]
  (vec (filter funcao compras)))

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

(defn data-valida? [data]
  (re-matches #"\d{4}-(\d{2})-\d{2}" data))

(defn proximo-id [entidades]
  (if-not (empty? entidades)
    (+ 1 (apply max (map :id entidades)))
    1))

; poderia usar o (> valor min max)?
(defn entre-valores [min max valor]
  (and (>= valor min) (<= valor max)))

(defn min-caracteres [n]
  (s/constrained s/Str #(>= (count %) n)))


(defn opcional [schema]
  (s/maybe schema))

(def InteiroPositivo (s/pred pos-int?))
(def IdOpcional (opcional InteiroPositivo))
(def ValorPositivo (s/constrained BigDecimal (comp not neg?)))

(defn local-date-time->date [data]
  (if (time/local-date-time? data)
    (->> (.atZone data (time/zone-id))
         .toInstant
         java.util.Date/from)
    data))

(defn local-date->date [data]
  (if (time/local-date? data)
    (->> (time/zone-id)
         (.atStartOfDay data)
         (.toLocalDateTime)
         (local-date-time->date))
    data))

(defn date->local-date-time [data]
  (if (= java.util.Date (class data))
    (-> (.toInstant data)
        (java.time.LocalDateTime/ofInstant (time/zone-id)))
    data))

(defn converte-valores-de-um-mapa [fn mapa]
  (clojure.walk/postwalk fn mapa))

(def converte-java-time-para-date
  (partial converte-valores-de-um-mapa (comp local-date->date local-date-time->date)))

(def converte-date-para-java-time
  (partial converte-valores-de-um-mapa date->local-date-time))