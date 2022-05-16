(ns yes-she-codes.respostas
  (:use clojure.pprint)
  (:require [yes-she-codes.db :as y.db]
            [clojure.string :as str]))

(defn total-gasto [lista-compras]
  (->> lista-compras
       (map :valor)
       (reduce +)))

;RESPOSTAS

(defn filtra-compras [predicado compras]
  (vec (filter predicado compras)))

(defn mes-da-data [data]
  (second (re-matches #"\d{4}-(\d{2})-\d{2}" data)))

(defn RESPOSTA-filtra-compras-no-mes [mes compras]
  (filtra-compras #(= mes (mes-da-data (:data %))) compras)
  ;(vec (filter (fn [compra] (= mes (mes-da-data (:data compra)))) compras))
  )

;(pprint (RESPOSTA-filtra-compras-no-mes "02" lista-compras))

(defn RESPOSTA-filtra-compras-no-estabelecimento [estabelecimento compras]
  (vec (filter (fn [compra]
                 (= estabelecimento  (:estabelecimento compra)))
               compras)))

;(pprint (RESPOSTA-filtra-compras-no-estabelecimento "Outback" lista-compras))

(defn total-gasto-no-mes [mes lista-compras]
  (total-gasto (RESPOSTA-filtra-compras-no-mes mes lista-compras)))

(def total-gasto-no-mes
  (comp total-gasto RESPOSTA-filtra-compras-no-mes))

;(pprint (total-gasto-no-mes "01" lista-compras))


;FILTRAR COMPRAS NUM INTERVALO DE VALORES

(defn RESPOSTA-filtro-compras-por-valor [minimo maximo compras]
  (filtra-compras #(and (>= (:valor %) minimo)
                        (<= (:valor %) maximo))
                  compras))

;(pprint (RESPOSTA-filtro-compras-por-valor 5 84 lista-compras))

;AGRUPAR GASTOS POR CATEGORIA
(defn RESPOSTA-gasto-por-categoria [compras]
  (map (fn [[categoria compras]]
         {:categoria categoria
          :total-gasto (total-gasto compras)})
       (group-by :categoria compras))
  )

;(pprint (RESPOSTA-gasto-por-categoria lista-compras))