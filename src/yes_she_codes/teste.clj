(ns yes_she_codes.teste
  (:require [yes_she_codes.db :as y.db])
  (:require [yes-she-codes.cliente :as y.cliente])
  (:require [yes_she_codes.cartao :as y.cartao])
  (:require [yes_she_codes.compras :as y.compras])
  (:require [clojure.string :as str]))

(defn total-gasto [lista-compras]
  (->> lista-compras
       (map :valor)
       (apply +)))

(defn lista-gastos [[cartao compras]]
  { :cartao cartao
   :gasto-total (total-gasto compras)})

(defn soma-compras-mes [mes cartao lista-compras]
  (->> lista-compras
       (filter (and #(str/includes? (:data %) (str mes))
                    #(= (:cartao %) (str cartao))))
       (total-gasto)
       println))

(soma-compras-mes 1 1234123412341234 (y.compras/lista-compra (y.db/lista-dados-compra)))
