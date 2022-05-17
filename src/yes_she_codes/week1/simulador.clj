(ns yes-she-codes.week1.simulador
  (:require [yes-she-codes.week1.input.csv-reader :as i.csv]
            [yes-she-codes.week1.input.hard-code :as i.hc]
            [yes-she-codes.week1.logic.logic :as l]
            [clojure.pprint :as pp]))

(defn my-pprint
  [& args]
  (mapv #(pp/pprint %) args))

;; SIMULADOR SEMANA 1

(my-pprint "***Lista Clientes:" (i.csv/lista-clientes))
(my-pprint "*** Lista Cartões:" (i.csv/lista-cartoes))
(my-pprint "*** Lista Compras:" (i.csv/lista-compras))

(my-pprint "***Lista Clientes:" (i.hc/lista-clientes))
(my-pprint "*** Lista Cartões:" (i.hc/lista-cartoes))
(my-pprint "*** Lista Compras:" (i.hc/lista-compras))

(my-pprint "Total Gasto:"
           (l/total-gasto (i.csv/lista-compras)))
(my-pprint "Compras do Mes: Abril"
           (l/lista-de-compras-do-mes 04 (i.csv/lista-compras)))
(my-pprint "Total Gasto Mes: Abril"
           (l/total-gasto-no-mes 04 (i.csv/lista-compras)))
(my-pprint "Lista de compras intervalo: valor-max = 150, valor-min = 100"
           (l/lista-de-compras-por-intervalo-de-valores 150M 100M (i.csv/lista-compras)))
(my-pprint "Gasto por categoria"
           (l/gasto-por-categoria  (i.csv/lista-compras)))

