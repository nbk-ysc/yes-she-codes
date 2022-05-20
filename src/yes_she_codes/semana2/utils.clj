(ns yes_she_codes.semana2.utils)

(defn proximo-id [entidades]
  (if-not (empty? entidades)
    (+ 1 (apply max (map :id entidades)))
    1))

(defn mesmo-id?
  [id entidade]
  (= id (:id entidade)))