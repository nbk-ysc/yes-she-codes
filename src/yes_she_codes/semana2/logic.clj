(ns yes-she-codes.semana2.logic
  (:use [clojure pprint]))

(defn proximo-id [entidades]
  (if-not (empty? entidades)
    (+ 1 (apply max (map :id entidades)))
    1))