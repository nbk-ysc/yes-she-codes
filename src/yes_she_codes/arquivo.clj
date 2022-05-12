(ns yes-she-codes.arquivo
  (:require [clojure.data.csv :as csv]
            [clojure.java.io :as io]))

(defn carrega-csv [arquivo]
  (with-open [dados (io/reader arquivo)]
    (->> (csv/read-csv dados)
         (take 5)
         ;; other intermediate steps go here
         (doall)
         (next))))
