(ns yes-she-codes.semana1.buscar-compras-em-um-intervalo
  (:require [yes-she-codes.semana1.logica :as y.logica]
            [yes-she-codes.semana1.db :as y.db]))

(def lista-compra [y.db/compra3, y.db/compra18, y.db/compra3 ] )


(defn valor-esta-no-intervalo?
  [inicio fim compra]
  (<= inicio  (get compra :valor 0) fim ))


(defn compras-no-intervalo
  [inicio fim lista-compras]
  (->> lista-compras
       (filter #(valor-esta-no-intervalo? inicio fim %))))

(compras-no-intervalo 50 1000 lista-compra)


