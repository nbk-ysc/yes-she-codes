(ns yes-she-codes.persistencia.cliente
  (:use clojure.pprint)
  (:require [yes-she-codes.db.cliente :as db.cliente]
            [yes-she-codes.dominio.cliente :as dominio.cliente]
            [yes-she-codes.logic :as logic]
            [yes-she-codes.persistencia.compra :as compras]
            [yes-she-codes.persistencia.cartao :as cartao]
            [schema.core :as s]))

(s/defn novo-cliente :- dominio.cliente/ClienteSchema
  [[nome cpf email]]
  (dominio.cliente/->Cliente nil nome cpf email))


(defn parametros-clients []
  (let [clients (db.cliente/param-clientes)]
    (vec (map novo-cliente clients))))


(defn lista-clientes []
  (parametros-clients))


(defn agregate-client-total-gasto [compras]
  (let [total (compras/total-compras-por-cartao compras)]
    (if (empty? total) 0 (:R$ (first total)))))

(defn agregate-client-cpf [cpf]
  (let [client (into {} (logic/filtra-data :cpf cpf (lista-clientes)))
        cartao (cartao/agregate-cartao cpf)
        compras  (compras/agregate-compras (:numero (:cartao cartao)))
        total-gasto (assoc-in cartao [:cartao :total-gasto] (agregate-client-total-gasto compras))
        conta (assoc-in total-gasto [:cartao :compras] compras)]
    (conj client conta)))

(defn agregate-clients []
  (map (fn [client] (agregate-client-cpf (:cpf client))) (lista-clientes)))
