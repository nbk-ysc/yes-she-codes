(ns yes_she_codes.logic)

(defn total-gasto
  [compras]
  (->> compras
       (map :valor compras)
       (reduce +)))

(defn compra-dentro-do-mes?
  [mes compra]
  (= mes (subs (:data compra) 5 7)))

(defn buscar-compras-mes
  [mes compras]
  (filter #(compra-dentro-do-mes? mes %) compras))

(defn compra-dentro-do-estabelecimento?
  [estabelecimento compra]
  (= estabelecimento (:estabelecimento compra)))

(defn buscar-compras-por-estabelecimento
  [estabelecimento compras]
  (filter #(compra-dentro-do-estabelecimento? estabelecimento %) compras))

(defn total-gasto-no-mes
  [mes compras]
  (->> compras
       (buscar-compras-mes mes)
       total-gasto))

