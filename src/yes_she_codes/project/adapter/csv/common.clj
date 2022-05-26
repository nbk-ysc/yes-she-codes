(ns yes-she-codes.project.adapter.csv.common
  (:require [clojure.string :as string]
            [schema.core :as s]
            [yes-she-codes.project.wire.in.csv :as in.csv]))

(s/defn ^:private deaccent :- s/Str
  [str :- s/Str]
  (let [normalized (java.text.Normalizer/normalize str java.text.Normalizer$Form/NFD)]
    (string/replace normalized #"\p{InCombiningDiacriticalMarks}+" "")))

(s/defn ^:private normalize-keyword :- s/Keyword
  [str :- s/Str]
  (-> str
      deaccent
      string/lower-case
      keyword))

(s/defn csv-data->maps :- in.csv/CsvMapas
  [csv-data :- in.csv/RawCsv]
  (vec (map zipmap
            (->> (first csv-data)
                 (map normalize-keyword)
                 repeat)
            (rest csv-data))))


