(ns yes-she-codes.util)

(defn proximo-id [entidades]
  (if-not (empty? entidades)
    (+ 1 (apply max (map :id entidades)))
    1))

(defn deve-remover? [id item]
  (->> item
       :id
       (= id)))