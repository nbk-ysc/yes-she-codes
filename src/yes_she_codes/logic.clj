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





(println "\n\n ---------------- str-to-float ---------------- ")
(defn str-to-float
  [string]
  (if (string? string)
           (->> string
                (remove #{\ \.\-})
                (s/join)
                (Long/valueOf))
           string))





(println "\n\n ---------------- validar cliente ---------------- ")
(defn validar-cliente
  [cliente]
  (let [clientes (y.db/todos-clientes)]
    (some #{cliente} (map :cpf clientes)))
  )


























