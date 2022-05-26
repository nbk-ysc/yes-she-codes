(ns yes-she-codes.project.diplomat.csv
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [schema.core :as s]
            [yes-she-codes.project.wire.in.csv :as in.csv]))

;;;; ler/ escrever no arquivo csv


;(with-open [writer (io/writer "write.csv")]
;  (csv/write-csv writer
;                 [["abc" "def"]
;                  ["ghi" "jkl"]]))


(s/set-fn-validation! true)

(s/defn read-csv :- in.csv/RawCsv
  [filepath] :- s/Str
  (vec (with-open [reader (io/reader filepath)]
         (doall
           (csv/read-csv reader)))))

(read-csv "data/in/compras.csv")





