(ns yes-she-codes.semana1.total-gasto-em-um-mes
  (:require [yes-she-codes.semana1.buscar-compras-por-mes :as y.busca-mes]
            [yes-she-codes.semana1.total-gasto-em-um-cartao :as y.gasto-no-cartao]
            [yes-she-codes.semana1.db :as y.db]))

(def lista-compra [y.db/compra3, y.db/compra3, y.db/compra3 ] )

(defn total-gasto-no-mes
  [mes lista-compras]
  (->> lista-compras
       (y.busca-mes/gasto-no-mes mes)
       (y.gasto-no-cartao/total-gasto)))

(total-gasto-no-mes "02" lista-compra)