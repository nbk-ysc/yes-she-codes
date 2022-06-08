(ns yes-she-codes.solucoes.semana2.logic)

(defn proximo-id [entidades]
  (if-not (empty? entidades)
    (+ 1 (apply max (map :id entidades)))
    1))