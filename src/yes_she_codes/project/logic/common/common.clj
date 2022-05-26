(ns yes-she-codes.project.logic.common.common
  (:require [clojure.pprint :as pprint]))

;;;; apply to many schemas
;;;; see what to do

(defn ^:private proximo-id
  [entidades]
  (if-not (empty? entidades)
    (inc (apply max (map :id entidades))) 1))

(defn ^:private registro-com-id-atribuido
  [compras registro]
  (assoc registro :id (proximo-id compras)))

(defn insere-registro
  [entidade registro]
  (conj entidade (registro-com-id-atribuido entidade registro)))

(defn pesquisa-registro-por-id
  [entidade id]
  (->> entidade
       (filter #(= (:id %) id))
       first))

(defn exclui-registro
  [entidade id]
  (remove #(= (:id %) id) entidade))

(defn lista-entidade
  [entidade]
  (pprint/pprint entidade))
