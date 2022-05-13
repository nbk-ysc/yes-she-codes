(ns yes-she-codes.semana1.buscar-compras-por-mes
  (:require [yes-she-codes.semana1.db :as y.db]
            [yes-she-codes.semana1.logica :as y.logica]))

(def lista-compra [y.db/compra1 y.db/compra2 y.db/compra3 ] )


(defn gasto-no-mes
  [mes lista-compras]
  (->> lista-compras
       (filter #(= (y.logica/get-mes (y.logica/get-data %)) mes ))))

(gasto-no-mes "01" lista-compra)


(defn gasto-no-mes-recursivo
  ([mes compras]
   (gasto-no-mes-recursivo mes compras []))

  ([mes compras compras-naquele-mes]
   (let [compra (first compras)]
     (if  (some? compra)
       (do
         (if (= mes (y.logica/get-mes (y.logica/get-data compra)))
           (recur mes (next compras) (conj compras-naquele-mes compra))
           (recur mes (next compras) compras-naquele-mes )))
       compras-naquele-mes))))

(gasto-no-mes-recursivo "01" lista-compra)
