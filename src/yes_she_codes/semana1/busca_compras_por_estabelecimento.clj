
(ns yes-she-codes.semana1.busca-compras-por-estabelecimento
  (:require [yes-she-codes.semana1.db :as y.db]
            [yes-she-codes.semana1.logica :as y.logica]))

(def lista-compra [y.db/compra19 y.db/compra8 y.db/compra9  y.db/compra2 y.db/compra7 ] )

(defn compras-no-estabelecimento-recursivo
  ([estabelecimento compras]
   (compras-no-estabelecimento-recursivo estabelecimento compras []))

  ([estabelecimento compras compras-naquele-estabelecimento]
   (let [compra (first compras)]
     (if  (some? compra)
       (do
         (if (= estabelecimento (:estabelecimento compra))
           (recur estabelecimento (next compras) (conj compras-naquele-estabelecimento compra))
           (recur estabelecimento (next compras) compras-naquele-estabelecimento )))
       compras-naquele-estabelecimento))))

(compras-no-estabelecimento-recursivo "Alura" lista-compra)


(defn compras-no-estabelecimento
  [estabelecimento lista-compra]
  (->> lista-compra
       (filter #(= (:estabelecimento %) estabelecimento) )))

(compras-no-estabelecimento "Alura" lista-compra)