(ns semana1.banco.logic
  (:require [java-time :as t])
  (:use [clojure pprint]))

(defn novo-cliente [lista-de-clientes cliente]
  (conj lista-de-clientes cliente))

;region listar clientes, cartões e compras

(defn lista-clientes [lista-de-clientes]
  (pprint lista-de-clientes))

(defn lista-cartoes [lista-de-cartoes]
  (pprint lista-de-cartoes))

(defn lista-compras [lista-de-compras]
  (pprint lista-de-compras))

;endregion

;region filtra cliente/cartão/valor por parametros passados

(defn recupera-cliente
  "Retorna um cliente"
  [lista-de-clientes, cpf]
  (pprint (filter #(= (:CPF %) cpf) lista-de-clientes)))

(defn recupera-cartao
  "Retorna o cartão relacionado a um cliente"
  [lista-de-cartoes, cliente]
  (pprint (filter #(= (:CLIENTE %) cliente) lista-de-cartoes)))

(defn recupera-compra
  "Retorna a compra de determinado cartão"
  [lista-de-compras, cartao]
  (pprint (filter #(= (:CARTÃO %) cartao) lista-de-compras)))

(defn compras-de-um-cartao
  "Retorna total das compras de determinado cartão"
  [lista-de-compras cartao]
  (filter #(= (:CARTÃO %) cartao) lista-de-compras))

;(defn compras-do-mes
;  "Retorna lista das compras de determinado mês"
;  [lista-de-compras mes]
;  (filter #(clojure.string/includes? (:DATA %) mes) lista-de-compras))

(defn compras-do-mes
  "Retorna lista das compras de determinado mês"
  [lista-de-compras mes]
  (println "função nova")
  (filter #(= (t/as (t/local-date "dd/MM/yyyy" (:DATA %)) :month-of-year) mes) lista-de-compras))

(defn compras-por-estabelecimento
  "Retorna total das compras de determinado estabelecimento"
  [lista-de-compras estabelecimento]
  (filter #(= (:ESTABELECIMENTO %) estabelecimento) lista-de-compras))

;endregion

(defn total-das-compras
  [compras]
  (->> compras
       (map :VALOR)
       (reduce +)))

(defn total-gasto-no-mes
  "Retorna total das compras de determinado cartão em um mês"
  [lista-de-compras cartao mes]
  (let [compras-no-mes (compras-do-mes lista-de-compras mes)
        compras-do-cartao (filter #(= (:CARTÃO %) cartao) compras-no-mes)]
    (total-das-compras compras-do-cartao)))

(defn gasto-por-categoria [[categoria compras]]
  {:Categoria   categoria
   :Valor-total (total-das-compras compras)})

(defn categorias-agrupadas [compras]
  (->> compras
       (group-by :CATEGORIA)
       (map gasto-por-categoria)))


(defn gasto-por-categoria [[categoria compras]]
  {:Categoria   categoria
   :Valor-total (total-das-compras compras)})

(defn categorias-agrupadas [compras]
  (->> compras
       (group-by :CATEGORIA)
       (map gasto-por-categoria)))

(defn recupera-compra-por-valor
  "Retorna compras entre determinado valor"
  [lista-de-compras, valor-minimo, valor-maximo]
  (filter #(and (>= (:VALOR %) valor-minimo)
                (<= (:VALOR %) valor-maximo)) lista-de-compras))
