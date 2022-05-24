(ns yes-she-codes.week2.logic.common.common
  (:require [clojure.pprint :as pprint]))

(defmulti valida-entidade (fn [record] (pr-str (type record))))

(defmethod valida-entidade :default [_] true)

(defn ^:private proximo-id
  [entidades]
  (if-not (empty? entidades)
    (inc (apply max (map :id entidades))) 1))

(defn ^:private record-com-id-atribuido
  [compras record]
  (assoc record :id (proximo-id compras)))

(defn insere-record
  [entidade record]
  {:pre [(valida-entidade record)]}
  (conj entidade (record-com-id-atribuido entidade record)))

(defn pesquisa-record-por-id
  [entidade id]
  (->> entidade
       (filter #(= (:id %) id))
       first))

(defn exclui-record
  [entidade id]
  (remove #(= (:id %) id) entidade))

(defn lista-entidade
  [entidade]
  (pprint/pprint entidade))