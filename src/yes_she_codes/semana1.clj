(ns yes-she-codes.semana1
  (:require [yes-she-codes.db :as y.db]
            [java-time :as t]))

(defn transforma-datas
  [data]
  (t/local-date data))

(defn novo-cliente
  [nome cpf email]
  {:nome nome, :cpf cpf, :email email})

(defn novo-cartao
  [numero cvv validade limite cliente]
  {:numero numero :cvv cvv :validade validade :limite limite :cliente cliente})

(defn nova-compra
  [data valor estabelecimento categoria cartao]
  {:data (transforma-datas data) :valor valor :estabelecimento estabelecimento :categoria categoria :cartao cartao})

(defn transforma-clientes [registros]
  (map (fn [[nome cpf email]]
         (novo-cliente nome, cpf, email))
       registros))

(defn transforma-cartoes [registros]
  (map (fn [[numero cvv validade limite cliente]]
         (novo-cartao numero, cvv, validade, limite, cliente))
       registros))

(defn transforma-compras [registros]
  (map (fn [[data valor estabelecimento categoria cartao]]
         (nova-compra data, valor, estabelecimento, categoria, cartao))
       registros))

(defn compras-de-um-cartao
  [compras cartao]
  (filter #(= (:cartao %) cartao) compras))

(defn total-gasto
  [compras]
  (reduce + (map :valor compras)))

(defn compras-por-estabelecimento
  [compras estabelecimento]
  (filter #(= (:estabelecimento %) estabelecimento) compras))

(defn mes-da-data [data]
  (.getValue (t/month data)))

(defn compras-por-mes
  [compras mes]
  (->> compras
       (filter #(= mes (mes-da-data (:data %))))))

(defn total-gasto-no-mes
  [compras mes]
  (->> compras
       (filter #(= mes (mes-da-data (:data %))))
       total-gasto))

(def clientes (transforma-clientes (y.db/lista-clientes)))
(def cartoes (transforma-cartoes (y.db/lista-cartoes)))
(def compras (transforma-compras (y.db/lista-compras)))
(total-gasto compras)
(total-gasto (compras-de-um-cartao compras 1234123412341234))
(compras-por-estabelecimento compras "Alura")
(compras-por-mes (compras-de-um-cartao compras 1234123412341234) 1)
(total-gasto-no-mes (compras-de-um-cartao compras 1234123412341234) 2)