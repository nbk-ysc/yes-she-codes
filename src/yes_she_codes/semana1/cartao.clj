(ns yes-she-codes.semana1.cartao
  (:require [yes-she-codes.semana1.db :as y.db]
            [yes-she-codes.semana1.compras :as y.compras]
            [yes-she-codes.semana1.utilities  :as y.logica]))


(def lista-compra [y.db/compra3, y.db/compra3, y.db/compra3 ] )

(defn total-gasto
  [ compras ]
  (->> compras
       (map y.logica/get-valor)
       (reduce +)))

(total-gasto lista-compra)


(defn total-gasto-no-mes
  [mes lista-compras]
  (->> lista-compras
       (y.compras/gasto-no-mes mes)
       (total-gasto)))

(total-gasto-no-mes "02" lista-compra)