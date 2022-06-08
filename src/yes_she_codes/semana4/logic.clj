(ns yes-she-codes.semana4.logic
  (:require [clojure.string :as str]))


;(println "\n\n ---------------- csv reader ---------------- ")

(defn processa-csv [arquivo mapeamento]
  (->> (slurp arquivo)
       str/split-lines
       rest
       (map #(str/split % #","))
       (mapv mapeamento)
       ))