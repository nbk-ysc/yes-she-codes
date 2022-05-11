(ns yes-she-codes.simulador)


(defn total-gasto [lista-compras]
  (apply + (map :valor lista-compras)))