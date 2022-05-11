(ns yes-she-codes.core
  (:use clojure.pprint)
  (:require yes-she-codes.db))


;assumindo que a função receberia uma lista de hashmaps de compra como os acima
(defn total-gasto
  [lista]
  (reduce + (map :valor lista)))

(def minhas-compras (yes-she-codes.db/lista-compras yes-she-codes.db/compras))


(defn buscar-por-estabelecimento
  [estabelecimento lista]
  (filter #(= estabelecimento (:estabelecimento %)) lista))

(defn buscar-por-mes
  [mes lista]
  (let [nova-lista (map #(assoc % :mes (subs (% :data) 5 7)) lista)]
    (filter #(= mes (:mes  %)) nova-lista)))

(defn total-gasto-no-mes
  [mes lista]
  (let [nova-lista (buscar-por-mes mes lista)]
    (reduce + (map :valor nova-lista))))

(defn filtro-intervalo
  [valor-minimo valor-maximo lista]
  (filter #(and (<= valor-minimo (:valor %)) (>= valor-maximo (:valor %))) lista))

(defn gastos-por-categoria
  [lista]
  (map
    (fn [[key values]]
      {:categoria key
       :total (reduce + (map :valor values))})
    (group-by :categoria lista)))



































