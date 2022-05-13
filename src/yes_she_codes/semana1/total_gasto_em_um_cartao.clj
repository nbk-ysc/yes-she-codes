(ns yes-she-codes.semana1.total-gasto-em-um-cartao
  (:require [yes-she-codes.semana1.db :as y.db]
            [yes-she-codes.semana1.logica  :as y.logica]))

(defn total-gasto
  [ compras ]
  (->> compras
       (map y.logica/get-valor)
       (reduce +)))

(def lista-compra [y.db/compra1, y.db/compra2, y.db/compra3 ] )

(total-gasto lista-compra)