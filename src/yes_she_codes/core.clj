(ns yes-she-codes.core
  (:require [yes-she-codes.db :as l.db]))

(def conta-clients {})

(def cartoes-clients {})

(def compras-clients {})

(defn novo-cliente
  [[nome cpf email]]
  (let [client (conj {:nome nome :cpf cpf :email email})]
    client))

(defn novo-cartao
  [[numero cvv validate limite cpf]]
  (let [client (conj {:numero numero, :cvv cvv, :validate validate :limite limite :cpf cpf})]
    client))

(defn nova-compra
  [[data valor estabelecimento categoria cartao]]
  (let [client (conj {:data data, :valor valor, :estabelecimento estabelecimento :categoria categoria :cartao cartao})]
    client))


(defn parametros-clients []
  (let [clients (l.db/param-clientes)]
    (assoc-in conta-clients [:clients] (vec (map novo-cliente clients)))))


(defn parametros-cartoes []
  (let [cartoes (l.db/param-cartoes)]
    (assoc-in cartoes-clients [:cartoes] (vec (map novo-cartao cartoes)))))

(defn parametros-compras []
  (let [compras (l.db/param-compras)]
    (assoc-in compras-clients [:compras] (vec (map nova-compra compras)))))

(defn lista-clientes []
  (:clients (parametros-clients)))

(defn lista-cartoes []
  (:cartoes (parametros-cartoes)))

(defn lista-compras []
  (:compras (parametros-compras)))

(println (lista-compras))

(defn total-gasto [compras]
  (reduce + (map #(:valor %) compras)))


(println (total-gasto (:compras (parametros-compras))))







