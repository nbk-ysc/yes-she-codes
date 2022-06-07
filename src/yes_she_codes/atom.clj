(ns yes-she-codes2.atom
  (:use [clojure pprint])
  (:require [yes-she-codes2.db :as y.db]))

(def repositorio-de-compras (atom []))

(defrecord Compras [id,
                    data,
                    valor,
                    estabelecimento,
                    categoria,
                    cartao])

(defn lista-de-compras-atomo []
  @repositorio-de-compras)

(defn id-uuid []
  (.toString (java.util.UUID/randomUUID)))

(defn inserir-id-compra [[data, valor, estabelecimento, categoria, cartao]]
  (Compras. (id-uuid), data, valor, estabelecimento, categoria, cartao))

(defn inserir-id-compra-vetor [repositorio-de-compras antiga-compra]
  (let [nova-compra
        (inserir-id-compra antiga-compra)]
    (conj repositorio-de-compras nova-compra)))

(defn inserir-compras-atom! [antiga-compra]
  (swap! repositorio-de-compras inserir-id-compra-vetor antiga-compra))

(defn transforma-compras [array]
  (doseq [x array]
    (inserir-compras-atom! x)))

(defn lista-compras []
  (transforma-compras (y.db/busca-registros-de-compras)))

(lista-compras)

(pprint (lista-de-compras-atomo))

(defn pesquisa-compra-por-id! [entidades id]
  (first (filter #(= id (:id %)) entidades)))

(defn pesquisa-compra-id-atom [id repositorio-de-compras]
  (pesquisa-compra-por-id! @repositorio-de-compras id))

(pesquisa-compra-por-id! @repositorio-de-compras "1b78b5e0-940e-4dd9-aabb-c588742bcd82")
(pesquisa-compra-id-atom "1b78b5e0-940e-4dd9-aabb-c588742bcd82" repositorio-de-compras)
