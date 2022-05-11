(ns yes-she-codes.nubank
  (:require [yes-she-codes.db :as y.db]))


(println "Lista de clientes:" (y.db/lista-clientes))
(println "Lista de cartões:" (y.db/lista-cartoes))
(println "Lista de compras:" (y.db/lista-compras))



;(defn total-gasto [lista-de-compras]
;  (->> lista-de-compras
;       (map :valor)
;       (reduce +)))

(defn total-gasto [lista-de-compras]
  (reduce + (map :valor lista-de-compras)))

(println "TOTAL:" (total-gasto (y.db/lista-compras)))



(defn mes-data [data]
  (subs data 5 7))

(defn compra-por-mes
  [mes lista-de-compras]
  (filter #(= (mes-data (:data %)) mes) lista-de-compras))

(println "Lista de compras por mês:" (compra-por-mes "02" (y.db/lista-compras)))



(defn compra-por-estabelecimento
  [estabelecimento lista-de-compras]
  (filter #(= (:estabelecimento %) estabelecimento) lista-de-compras))

(println "Lista de compras por estabelecimento:"
         (compra-por-estabelecimento "Cinema" (y.db/lista-compras)))



(defn total-gasto-no-mes
  [mes lista-de-compras]
  (reduce + (map :valor (compra-por-mes mes lista-de-compras))))

(println "Total por mês:" (total-gasto-no-mes "02" (y.db/lista-compras)))



(defn compras-intervalo-de-valores
  [valor-minimo valor-maximo lista-de-compras]
  (filter #(and (<= valor-minimo (:valor %)) (>= valor-maximo (:valor %))) lista-de-compras))

(println "Compras de valor maior ou igual a 100 e menor ou igual a 200:"
         (compras-intervalo-de-valores 100 200 (y.db/lista-compras)))



(defn valor-por-categoria
  [[categoria lista-de-compras]]
  {:categoria   categoria
   :valor-total (total-gasto lista-de-compras)})

(defn total-gasto-agrupado-por-categoria
  [lista-de-compras]
  (map valor-por-categoria (group-by :categoria lista-de-compras)))

;(defn total-gasto-agrupado-por-categoria
;  [lista-de-compras]
;  (->> lista-de-compras
;       (group-by :categoria)
;       (map valor-por-categoria)))

(println "Total gasto agrupado por categoria:"
         (total-gasto-agrupado-por-categoria (y.db/lista-compras)))





