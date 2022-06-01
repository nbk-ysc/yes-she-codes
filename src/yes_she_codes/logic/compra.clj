(ns yes-she-codes.logic.compra
  (:require [yes-she-codes.db.db :as y.db]
            [yes-she-codes.util :as y.util])
  (:use [clojure pprint]))

;;;;; Semana 1
(defn nova-compra
  [id data valor estabelecimento categoria cartao]
  {:id id :data (y.util/transforma-datas data) :valor valor :estabelecimento estabelecimento :categoria categoria :cartao cartao})

(defn transforma-compras [registros]
  (map (fn [[id data valor estabelecimento categoria cartao]]
         (nova-compra id data, valor, estabelecimento, categoria, cartao))
       registros))

(def compras (transforma-compras (y.db/lista-compras)))

(defn total-gasto
  [compras]
  (reduce + (map :valor compras)))

(defn compras-por-estabelecimento
  [compras estabelecimento]
  (filter #(= (:estabelecimento %) estabelecimento) compras))

(defn compras-por-mes
  [compras mes]
  (->> compras
       (filter #(= mes (y.util/mes-da-data (:data %))))))

(defn total-gasto-no-mes
  [compras mes]
  (->> compras
       (filter #(= mes (y.util/mes-da-data (:data %))))
       total-gasto))

(defn gastos-por-categoria
  [compras]
  (group-by #(get % :categoria) compras))

;;;;; Semana 2
(defn insere-compra
  [compras compra]
  (conj @compras (assoc compra :id (y.util/proximo-id @compras))))

(defn insere-compra!
  [compras compra]
  (swap! compras conj (assoc compra :id (y.util/proximo-id @compras))))

(defn lista-compras!
  [compras]
  (pprint @compras))

(defn pesquisa-compra-por-id
  [compras id-compra]
  (filter #(= id-compra (:id %)) compras))

(defn pesquisa-compra-por-id!
  [compras id-compra]
  (filter #(= id-compra (:id %)) @compras))

(defn exclui-compra!
  [compras id-compra]
  (remove (pesquisa-compra-por-id compras id-compra)))