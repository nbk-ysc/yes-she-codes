(ns yes_she_codes.logic
  (:require [clojure.string :as str]))

; ======================================================

(defn soma-valor-das-compras [acc compra]
  (+ acc (:valor compra)))

(defn total-gasto [lista-de-compras]
  (reduce soma-valor-das-compras 0 lista-de-compras))

; =====================================================

(defn split-data [data]
  (str/split data #"-"))

(defn get-mes [data]
  (get (split-data data) 1))

(defn get-mes-da-compra [compra]
  (get-mes (:data compra)))

(defn compra-realizada-no-mes? [mes compra]
  (= mes (get-mes-da-compra compra)))

(defn compras-no-mes [mes lista-de-compras]
  (vec (filter #(compra-realizada-no-mes? mes %) lista-de-compras)))

; ====================================================

(defn compra-realizada-no-estabelecimento? [estabelecimento compra]
  (= estabelecimento (:estabelecimento compra)))

(defn compras-no-estabelecimento [estabelecimento lista-de-compras]
  (vec (filter #(compra-realizada-no-estabelecimento? estabelecimento %) lista-de-compras)))

; ====================================================

(defn total-gasto-no-mes [mes lista-de-compras]
  (total-gasto (compras-no-mes mes lista-de-compras)))

; =====================================================

(defn compra-esta-entre-min-e-max? [min max compra]
  (and (>= (:valor compra) min) (<= (:valor compra) max)))

(defn compras-entre-min-e-max [min max lista-de-compras]
  (vec (filter #(compra-esta-entre-min-e-max? min max %) lista-de-compras)))

; =====================================================

(defn agrupados-por-categoria [lista-de-compras]
  (group-by :categoria lista-de-compras))

(defn concatena-totais-por-categoria [acc [categoria lista-de-compras-da-categoria]]
  (conj acc {
             categoria (total-gasto lista-de-compras-da-categoria)
             }))

(defn total-gasto-por-categoria [lista-de-compras]
  (reduce concatena-totais-por-categoria {} (agrupados-por-categoria lista-de-compras)))
