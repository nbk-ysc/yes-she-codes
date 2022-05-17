(ns yes-she-codes.week2.logic.util
  (:require [clojure.string :as str]
            [java-time :as jt]))


(defn string-sem-espacos
  [string]
  (str/replace string #"\s" ""))


(defn string->long
  [str]
  (Long/parseLong (string-sem-espacos str)))


(defn csv-data->vector
  [caminho-arquivo]
  (->> (slurp caminho-arquivo)
       (str/split-lines)
       rest
       (mapv #(str/split % #","))))


(defn proximo-id
  [entidades]
  (if-not (empty? entidades)
    (inc (apply max (map :id entidades))) 1))


(defn data-menor-igual-a-hoje?
  [data]
  (not (.isAfter data (jt/local-date))))


(defn bigdec-positivo?
  [valor]
  (and (= (type valor) BigDecimal)
       (> valor 0)))


(defn pelo-menos-dois-chars?
  [estabelecimento]
  (>= (count estabelecimento) 2))


(defn pertence-as-opcoes?
  [categoria]
  (let [categorias-permitidas #{"Alimentação", "Automóvel", "Casa", "Educação", "Lazer", "Saúde."}]
    (contains? categorias-permitidas categoria )))



















