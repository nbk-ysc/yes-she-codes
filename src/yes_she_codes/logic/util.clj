(ns yes-she-codes.logic.util
  (:require [clojure.java.io :as io]
            [clojure.string :as str]))


(defn mesmo-mes?
  [obj-data mes]
  (= mes (.getMonthValue obj-data)))


(defn pertence-ao-intervalo?
  "datas máxima e mínima inclusas"
  [data-max data-min data-cmp]
  (and
    (or (.equals data-max data-cmp) (.isAfter data-max data-cmp))
    (or (.equals data-cmp data-min) (.isAfter data-cmp data-min))))


(defn arquivo->vetor
  [path-arquivo]
  (try
    (with-open [rdr (io/reader path-arquivo)]
      (into [] (line-seq rdr)))
    (catch Exception e
      (println "Error:" (.getMessage e)))))


(defn string-sem-espacos
  [string]
  (str/replace string #"\s" ""))


(defn csv-splitter
  [full-str]
  (str/split full-str #","))

