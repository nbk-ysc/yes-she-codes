(ns yes-she-codes.semana2.logic
  (:use [clojure pprint]))

(defn proximo-id [entidades]
  (if-not (empty? entidades)
    (+ 1 (apply max (map :ID entidades)))
    1))

;(pprint (proximo-id [{:ID 1 :Data "18/05/2022" :Valor 10 :Estabelecimento "Araujo" :Categoria "Saude" :Cartao 1111222233334444}
;                     {:ID 2 :Data "19/05/2022" :Valor 10 :Estabelecimento "Nucleo Artistico" :Categoria "Lazer" :Cartao 1111222233334444}]))