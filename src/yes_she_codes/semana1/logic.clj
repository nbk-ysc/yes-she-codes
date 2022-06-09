(ns yes-she-codes.semana1.logic
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]))

(defn csv-data->maps [csv-data]
  (map zipmap
       (->> (first csv-data)
            (map keyword)
            repeat)
       (rest csv-data)))

(defn read-csv
  [filepath]
  (with-open [reader (io/reader filepath)]
    (doall
      (csv/read-csv reader))))
