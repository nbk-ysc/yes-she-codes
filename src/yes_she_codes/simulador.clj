(ns yes-she-codes.simulador
  (:require [clojure.string :as str]
            [java-time :as t]
            [clojure.data.csv :as csv]
            [clojure.java.io :as io]))

(defn total-gasto [lista-compras]
  (double (apply + (map :valor lista-compras))))

(defn busca-compras-mes [mes lista-compras]
  (filter #(= (t/year-month (:data %)) (t/year-month mes )) lista-compras))

(defn busca-compras-estabelecimento [estabelecimento lista-compras]
  (filter #(= (:estabelecimento %) (str/lower-case estabelecimento)) lista-compras))

(defn total-gasto-no-mes [cartao mes lista-compras]
  (->> lista-compras
      (filter #(= (:cartao %) cartao) ,,,)
      (busca-compras-mes mes ,,,)
      (total-gasto ,,,)))

(defn intervalo-compras [minimo maximo lista-compras]
  (filter #(and (<= minimo (:valor %) ) (>= maximo (:valor %))) lista-compras))

(defn gasto-categoria [lista-compras]
  (map
    (fn [[grp-key values]]
      {:categoria grp-key
       :total (total-gasto values)})
    (group-by :categoria lista-compras)))

(defn csv-data[caminho-arquivo]
  (with-open [reader (io/reader caminho-arquivo)]
    (doall (next
      (csv/read-csv reader)))))
