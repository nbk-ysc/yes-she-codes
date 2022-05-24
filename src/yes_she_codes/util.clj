(ns yes-she-codes.util
  (:require [java-time :as time]))

(defn str->long [valor]
  (Long/parseLong (clojure.string/replace valor #" " "")))


(defprotocol ExtratorDeMes
  (mes-da-data [data]
    "Dada uma data, extrai o valor do mês como um inteiro de 1 a 12"))

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



