(ns yes-she-codes.csvload
  (:require
    [clojure.data.csv :as csv]
    [clojure.java.io :as io]
    [clojure.string :as str])
  )

(defn read-csv [filepath]
  (with-open [reader (io/reader filepath)]
    (doall
      (csv/read-csv reader)))

  )

(defn csv-data->maps [csv-data]
  (map zipmap
       (->> (first csv-data)
            (map keyword)
            repeat)
       (rest csv-data)))

(defn lee-e-formata-csv [filepath]
   (csv-data->maps (read-csv filepath))
  )

(println (lee-e-formata-csv "clientes.csv"))
