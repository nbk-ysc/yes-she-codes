(ns yes-she-codes.simulador
  (:require [clojure.string :as str]))

(defn total-gasto [lista-compras]
  (apply + (map :valor lista-compras)))

(defn busca-compras-mes [mes lista-compras]
  (filter #(str/includes? (:data %) mes) lista-compras))

(defn busca-compras-estabelecimento [estabelecimento lista-compras]
  (filter #(= (str/lower-case (:estabelecimento %)) (str/lower-case estabelecimento)) lista-compras))

(defn total-gasto-no-mes [cartao mes lista-compras]
  (->> lista-compras
      (filter #(= (:cartao %) cartao) ,,,)
      (busca-compras-mes mes ,,,)
      (total-gasto ,,,)
      ))
