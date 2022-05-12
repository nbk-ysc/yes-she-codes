(ns yes-she-codes.logic
  (:require [yes-she-codes.db :as y.db]
            [clojure.string :as s]))


(let [clientes (y.db/todos-clientes)
      cartoes (y.db/todos-cartoes)
      compras (y.db/todas-compras)]
  (println "clientes ---" (sort-by :nome clientes))
  (println "cartoes ---" (sort-by :cliente cartoes))
  (println "compras ---" (reverse (sort-by :cartao compras)))
)


(defn str-to-float
  [string]
  (if (string? string)
           (->> string
                (remove #{\ \.\-})
                (s/join)
                (Long/valueOf))
           string))



(defn validar-cliente
  [cliente]
  (let [clientes (y.db/todos-clientes)]
    (some #{cliente} (map :cpf clientes)))
  )


























