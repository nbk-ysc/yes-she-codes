(ns yes-she-codes.semana2.logic
  (:use [clojure pprint]))

(defn proximo-id [entidades]
  (if-not (empty? entidades)
    (+ 1 (apply max (map :id entidades)))
    1))

(defn valida-data
  [data]
  (if (re-matches #"\d{4}-\d{2}-\d{2}" data)
    true
    false))

(defn valida-valor
  [valor]
  (and (= (class valor) java.math.BigDecimal) (> valor 0)))

(defn valida-estabelecimento
  [estabelecimento]
  (>= (count estabelecimento) 2))

(defn valida-categoria
  [categoria]
  (let [categorias ["Alimentação" "Automóvel" "Casa" "Educação" "Lazer" "Saúde"]]
    (some #(= categoria %) categorias)))

(defn valida-compra
  [compra]
  (if (and (valida-data (:data compra))
           (valida-valor (:valor compra))
           (valida-estabelecimento (:estabelecimento compra))
           (valida-categoria (:categoria compra)))
    true
    false))


















