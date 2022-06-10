(ns yes-she-codes.semana1.compras
  (:require [yes-she-codes.utilities.logica :as y.logica]
            [yes-she-codes.semana1.db :as y.db]))

(def lista-compra [y.db/compra3, y.db/compra18, y.db/compra3 ] )


(defn compras-no-intervalo
  [inicio fim lista-compras]
  (->> lista-compras
       (filter #(y.utilities/valor-esta-no-intervalo? inicio fim %))))

(compras-no-intervalo 50 1000 lista-compra)

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


(defn gasto-total-por-categoria
  [lista-compra]
  (->> lista-compra
       (group-by :categoria)
       (map y.utilities/gasto-por-categoria)))

(gasto-total-por-categoria lista-compra)