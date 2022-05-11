(ns yes-she-codes.simulador
  (:require [clojure.string :as str]))

(defn total-gasto [lista-compras]
  (apply + (map :valor lista-compras)))

(defn busca-compras-mes [lista-compras mes]
  (filter #(str/includes? (:data %) mes ) lista-compras))
