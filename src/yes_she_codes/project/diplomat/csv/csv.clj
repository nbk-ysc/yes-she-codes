(ns yes-she-codes.project.diplomat.csv.csv
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [schema.core :as s]
            [yes-she-codes.project.wire.in.csv :as in.csv]))

(s/defn ler-csv :- in.csv/RawCsv
  [filepath :- s/Str]
  (vec (with-open [reader (io/reader filepath)]
         (doall
           (csv/read-csv reader)))))

(s/defn escrever-csv :- s/Any
  [filepath :- s/Str
   csv-data :- in.csv/RawCsv]
  (with-open [writer (io/writer filepath)]
    (csv/write-csv writer csv-data)))

