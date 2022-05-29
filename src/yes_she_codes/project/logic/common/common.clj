(ns yes-she-codes.project.logic.common.common
  (:require [schema.core :as s]
            [clojure.pprint :as pprint]))

(s/defn ^:private proximo-id
  [entidade]
  (if-not (empty? entidade)
    (inc (apply max (map :id entidade))) 1))

(s/defn ^:private registro-com-id-atribuido 
  [entidade registro]
  (assoc registro :id (proximo-id entidade)))

(s/defn insere-registro 
  [entidade registro]
  (conj entidade (registro-com-id-atribuido entidade registro)))

(s/defn insere-registros 
  [entidade registros]
  (reduce insere-registro entidade registros))

(s/defn pesquisa-registro-por-id 
  [entidade id]
  (->> entidade
       (filter #(= (:id %) id))
       first))

(s/defn exclui-registro
  [entidade id]
  (remove #(= (:id %) id) entidade))

(s/defn lista-entidade
  [entidade]
  (pprint/pprint entidade))
