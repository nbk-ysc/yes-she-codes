(ns yes_she_codes.semana2.cartao_logic
  (:require [yes_she_codes.semana2.utils :as ysc.utils])
  (:import (java.time YearMonth))
  (:use [clojure pprint]))

(require '[java-time :as time])

(def repositorio-de-cartoes (atom []))

(defrecord Cartao [^Long id, ^Long numero, ^Long cvv, ^YearMonth validade, ^BigDecimal limite, ^String cliente])

(defn insere-cartao
  [vetor-cartoes cartao]
  (let [inserted_id (ysc.utils/proximo-id vetor-cartoes)
        inserted_cartao (assoc cartao :id inserted_id)]
    (conj vetor-cartoes inserted_cartao)))

(defn insere-cartao!
  [atomo-cartoes cartao]
  (swap! atomo-cartoes insere-cartao cartao))

(defn lista-cartoes!
  [atomo-cartoes]
  (println @atomo-cartoes))

(defn pesquisa-cartao-por-id
  [vetor-cartoes id]
  (first (filter #(ysc.utils/mesmo-id? id %) vetor-cartoes)))

(defn pesquisa-cartao-por-id!
  [atomo-cartoes id]
  (pesquisa-cartao-por-id @atomo-cartoes id))

(defn excluir-cartao
  [vetor-cartoes id]
  (remove #(ysc.utils/mesmo-id? id %) vetor-cartoes))

(defn excluir-cartao!
  [atomo-cartoes id]
  (swap! atomo-cartoes excluir-cartao id))