(ns yes-she-codes.semana1.compras-agrupadas-por-categoria
  (:require [yes-she-codes.semana1.logica :as y.logica]
            [yes-she-codes.semana1.db :as y.db]))

(def lista-compra [y.db/compra3, y.db/compra18, y.db/compra3  , y.db/compra4, y.db/compra7, y.db/compra8, y.db/compra19] )

(defn valor-gasto-por-categoria
  [compras]
  (->> compras
       (map y.logica/get-valor)
       (reduce +)))

(defn gasto-por-categoria
  [ [categoria compras]]
  { :categoria categoria
    :total-gasto (valor-gasto-por-categoria compras)})

(defn gasto-total-por-categoria
  [lista-compra]
  (->> lista-compra
       (group-by :categoria)
       (map gasto-por-categoria)))

(gasto-total-por-categoria lista-compra)