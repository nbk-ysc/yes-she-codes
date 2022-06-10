(ns yes-she-codes.semana1.cartao
  (:require yes-she-codes.semana1.compra :as yes-she-codes.semana1.compras ))

;(:require [yes-she-codes.semana1.db :as y.db]
;            [yes-she-codes.semana1.compras :as y.compras]
;            [yes-she-codes.utilities.logica  :as y.utilities])
(def lista-compra [y.db/compra3, y.db/compra3, y.db/compra3 ] )

(defn total-gasto
  [ compras ]
  (->> compras
       (map y.utilities/get-valor)
       (reduce +)))

(total-gasto lista-compra)

(defn total-gasto-no-mes
  [mes lista-compras]
  (->> lista-compras
       (y.compras/gasto-no-mes mes)
       (total-gasto)))

(total-gasto-no-mes "02" lista-compra)