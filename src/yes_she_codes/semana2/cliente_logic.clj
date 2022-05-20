(ns yes_she_codes.semana2.cliente_logic
  (:require [yes_she_codes.semana2.utils :as ysc.utils]))

(def repositorio-de-clientes (atom []))

(defrecord Cliente [^Long id, ^String nome, ^String cpf, ^String email])

(defn insere-cliente
  [vetor-clientes cliente]
  (let [inserted_id (ysc.utils/proximo-id vetor-clientes)
        inserted_cliente (assoc cliente :id inserted_id)]
    (conj vetor-clientes inserted_cliente)))

(defn insere-cliente!
  [atomo-clientes cliente]
  (swap! atomo-clientes insere-cliente cliente))

(defn lista-clientes!
  [atomo-clientes]
  (println @atomo-clientes))

(defn pesquisa-cliente-por-id
  [vetor-clientes id]
  (first (filter #(ysc.utils/mesmo-id? id %) vetor-clientes)))

(defn pesquisa-cliente-por-id!
  [atomo-clientes id]
  (pesquisa-cliente-por-id @atomo-clientes id))

(defn excluir-cliente
  [vetor-clientes id]
  (remove #(ysc.utils/mesmo-id? id %) vetor-clientes))

(defn excluir-cliente!
  [atomo-clientes id]
  (swap! atomo-clientes excluir-cliente id))
