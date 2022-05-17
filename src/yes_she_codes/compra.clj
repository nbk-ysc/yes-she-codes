(ns yes-she-codes.compra
  (:require [yes-she-codes.db :as db]
            [yes-she-codes.core :as core]))

(defn nova-compra [data valor estabelecimento categoria numero-cartao]
  {:data            data
   :valor           (bigdec valor)
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          (core/str-to-long numero-cartao)})

(defn lista-compras []
  (db/processa-csv "dados/compras.csv" (fn [[data valor estabelecimento categoria numero-cartao]]
                                         (nova-compra data valor estabelecimento categoria numero-cartao))))

(defn lista-compras-por-mes
  [mes compras]
  (filter #(if (= (subs (:data %) 5 7) mes) true) compras))

(defn lista-compras-por-cartao
  [cartao compras]
  (filter #(if (= (:cartao %) cartao) true) compras))

(defn lista-compras-por-estabelecimento
  [estabelecimento compras]
  (filter #(if (= (:estabelecimento %) estabelecimento) true) compras))

(defn total-gasto [compras]
  (reduce #(+ %1 (:valor %2)) 0 compras))

(defn total-gasto-por-mes
  [mes compras]
  (let [lista-compras-mes (lista-compras-por-mes mes compras)]
    (total-gasto lista-compras-mes)))

(defn total-gasto-por-mes-e-cartao
  [cartao mes compras]
  (let [lista-compras-cartao (lista-compras-por-cartao cartao compras)
        lista-compras-mes (lista-compras-por-mes mes lista-compras-cartao)]
    (total-gasto lista-compras-mes)))

(defn filtra-compras-por-valor
  [valor-minimo valor-maximo compras]
  (filter #(if (and (>= (:valor %) valor-minimo) (<= (:valor %) valor-maximo)) true) compras))

(defn agrupa-gastos-por-categoria [compras]
  (vec (map (fn [[categoria compras-da-categoria]]
              {:categoria   categoria
               :total-gasto (total-gasto compras-da-categoria)})
            (group-by :categoria compras))))
