(ns yes-she-codes.comprasatom
  (:require [yes-she-codes.logic :as y.logic])
  (:use [clojure.pprint]))



;1- Definir átomo como banco de dados em memória, vazio
(def repositorio-de-compras (atom []))
;(pprint (class @repositorio-de-compras))

;2- Criar Record para Compra
(defrecord Compras [^Long id, ^String data, ^BigDecimal valor, ^String estabelecimento, ^String categoria, ^Long cartao])

;3- Criar a função insere-compra. Ela vai atribuir um id a uma compra e armazená-la num vetor.
(defn insere-compra [vetor-compras-cadastradas record-compras]
  (->> vetor-compras-cadastradas
       (y.logic/proximo-id)
       (assoc record-compras :id)
       (conj vetor-compras-cadastradas)))

;4- Inserir compra no átomo
(defn insere! [atomo-compras record-compras]
  (swap! atomo-compras insere-compra record-compras))


;5- Crie a função lista-compras!, que lista uma lista de compras de um átomo.
(defn lista-compras! [atomo-compras]
  (println "atomo" @atomo-compras))

;6- Crie a função pesquisa-compra-por-id, que pesquisa por uma compra de determinado id num vetor.
(defn pesquisa-compra-por-id [id vetor-compras]
  (->> vetor-compras
       (filter #(= (:id %) id))
       first))

;7- Crie a função pesquisa-compra-por-id!, que pesquisa por uma compra em um átomo.
(defn pesquisa-compra-por-id! [id atomo-compras]
  (pesquisa-compra-por-id id @atomo-compras))

;8- Crie a função exclui-compra, que exclui uma compra de determinado id de um vetor.
(defn exlui-compra [vetor-compras id]
  (if-not (= nil (pesquisa-compra-por-id id vetor-compras))
    (->> vetor-compras
         (remove #(= (:id %) id))
         vec)
    vetor-compras
    ))

;9- Criar a função exclui-compra! para uma compra de um átomo por meio de swap!.
(defn exlui-compra! [id atomo-compras]
  (swap! atomo-compras exlui-compra id))