(ns yes-she-codes.week2.logic.logic
  (:require [yes-she-codes.week2.logic.util :as u]
            [clojure.pprint :as pp]))


(defn record-com-id-atribuido
  [compras record]
  (assoc record :id (u/proximo-id compras)))

(defn insere-record
  "atribui um id a um record e retorna um vetor de compras com ele armazenado"
  [entidade record]
  (conj entidade (record-com-id-atribuido entidade record)))

(defn pesquisa-record-por-id
  "retorna o record relacionado a determinado id"
  [entidade id]
  (->> entidade
       (filter #(= (:id %) id))
       first))

(defn exclui-record
  "retorna o vetor entidade com o record de id removido"
  [entidade id]
  (remove #(= (:id %) id) entidade))

(defn lista-entidade
  [entidade]
  (pp/pprint entidade))


;; Clientes

(defn insere-cliente!
  "atribui um id a um record e o armazena no vetor de clientes"
  [clientes record]
  (swap! clientes insere-record record))

(defn lista-clientes!
  [clientes]
  (lista-entidade @clientes))

(defn pesquisa-cliente-por-id!
  "retorna a compra relacionada a determinado id"
  [clientes id]
  (pesquisa-record-por-id @clientes id))

(defn exclui-cliente!
  "retorna o vetor clientes com a compra de id removida"
  [clientes id]
  (swap! clientes exclui-record id))


;; Cartões

(defn insere-cartao!
  "atribui um id a um record e o armazena no vetor de cartoes"
  [cartoes record]
  (swap! cartoes insere-record record))

(defn lista-cartoes!
  [cartoes]
  (lista-entidade @cartoes))

(defn pesquisa-cartao-por-id!
  "retorna a compra relacionada a determinado id"
  [cartoes id]
  (pesquisa-record-por-id @cartoes id))

(defn exclui-cartao!
  "retorna o vetor cartoes com a compra de id removida"
  [cartoes id]
  (swap! cartoes exclui-record id))


;; Compras

(defn valida-compra
  [{_0 :_0 data :data valor :valor estabelecimento :estabelecimento categoria :categoria _1 :_1}]
  (and (u/data-menor-igual-a-hoje? data)
       (u/bigdec-positivo? valor)
       (u/pelo-menos-dois-chars? estabelecimento)
       (u/pertence-as-opcoes? categoria)))

(defn insere-compra!
  "atribui um id a um record e o armazena no vetor de compras"
  [compras record]
  (if (valida-compra record)
      (swap! compras insere-record record)
      @compras))

(defn lista-compras!
  [compras]
  (lista-entidade @compras))

(defn pesquisa-compra-por-id!
  "retorna a compra relacionada a determinado id"
  [compras id]
  (pesquisa-record-por-id @compras id))

(defn exclui-compra!
  "retorna o vetor compras com a compra de id removida"
  [compras id]
  (swap! compras exclui-record id))




