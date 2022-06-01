(ns datomic.util
  (:require [clojure.string :as str]))

(defn processa-csv [arquivo funcao-mapeamento]
  (->> (slurp arquivo)
       str/split-lines
       rest
       (map #(str/split % #","))
       (mapv funcao-mapeamento)
       ))

(defn get-moth [mes]
  (get (str/split mes #"-") 1))