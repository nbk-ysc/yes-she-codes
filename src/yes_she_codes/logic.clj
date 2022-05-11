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

(defn valor-dentro-do-intervalo?
  [valor-minimo valor-maximo compra]
  (if (< valor-maximo valor-minimo)
    (let [valor-maximo-atualizado valor-minimo
          valor-minimo-atualizado valor-maximo]
      (and (> (:valor compra) valor-minimo-atualizado) (< (:valor compra) valor-maximo-atualizado)))

    (and (> (:valor compra) valor-minimo) (< (:valor compra) valor-maximo)))
  )

(defn compras-por-intervalo-de-valor
  [valor-minimo valor-maximo compras]
  (filter #(valor-dentro-do-intervalo? valor-minimo valor-maximo %) compras))

(defn total-gasto-por-categoria
  [[nome estabelecimento]]
  {:estabelecimento nome
   :total           (->> estabelecimento
                         (map :valor)
                         (reduce +))})

(defn gastos-agrupados-por-categoria
  [compras]
  (->> compras
       (group-by :estabelecimento)
       (map total-gasto-por-categoria)))

