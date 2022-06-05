(ns yes-she-codes.study.week2.adapter.common.common
  (:require [clojure.string :as str]))

(defn string-sem-espacos
  [string]
  (str/replace string #"\s" ""))

(defn string->long
  [str]
  (Long/parseLong (string-sem-espacos str)))

(defn csv-data->vector
  [caminho-arquivo]
  (->> (slurp caminho-arquivo)
       (str/split-lines)
       rest
       (mapv #(str/split % #","))))