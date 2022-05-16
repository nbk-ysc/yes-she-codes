(ns yes-she-codes.core
  (:require [yes-she-codes.input.csv-reader :as i]
            [yes-she-codes.logic.logic :as l]
            [clojure.pprint :as pp]))

(defn my-pprint
  [& args]
  (mapv #(pp/pprint %) args))

;;; SIMULADOR

;(my-pprint "***Lista Clientes:" (i/lista-clientes))
;(my-pprint "*** Lista Cartões:" (i/lista-cartoes))
;(my-pprint "*** Lista Compras:" (i/lista-compras))

(my-pprint "Total Gasto:"
           (l/total-gasto (i/lista-compras)))
(my-pprint "Compras do Mes: Abril"
           (l/lista-de-compras-do-mes 04 (i/lista-compras)))
(my-pprint "Total Gasto Mes: Abril"
           (l/total-gasto-no-mes 04 (i/lista-compras)))
(my-pprint "Lista de compras intervalo: valor-max = 150, valor-min = 100"
           (l/lista-de-compras-por-intervalo-de-valores 150M 100M (i/lista-compras)))
(my-pprint "Gasto por categoria"
           (l/gasto-por-categoria  (i/lista-compras)))