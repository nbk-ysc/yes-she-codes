(ns yes_she_codes.semana2.compra_logic
  (:require [yes_she_codes.semana2.utils :as ysc.utils])
  (:import (java.time LocalDate))
  (:use [clojure pprint]))

(require '[java-time :as time])

(def repositorio-de-compras (atom []))

(defrecord Compra [^Long id, ^LocalDate data, ^BigDecimal valor, ^String estabelecimento, ^String categoria, ^Long cartao])

(defn insere-compra
  [vetor-compras compra]
  (let [inserted_id (ysc.utils/proximo-id vetor-compras)
        inserted_compra (assoc compra :id inserted_id)]
    (conj vetor-compras inserted_compra)))

(defn insere-compra!
  [atomo-compras compra]
  (swap! atomo-compras insere-compra compra))

(defn lista-compras!
  [atomo-compras]
  (println @atomo-compras))

(defn pesquisa-compra-por-id
  [vetor-compras id]
  (first (filter #(ysc.utils/mesmo-id? id %) vetor-compras)))

(defn pesquisa-compra-por-id!
  [atomo-compras id]
  (pesquisa-compra-por-id @atomo-compras id))

(defn excluir-compra
  [vetor-compras id]
  (remove #(ysc.utils/mesmo-id? id %) vetor-compras))

(defn excluir-compra!
  [atomo-compras id]
  (swap! atomo-compras excluir-compra id))

(defn valida-data-da-compra
  [compra]
  (let [data-da-compra (:data compra)
        agora (time/local-date)]
    (not (time/after? data-da-compra agora))))

(defn valida-valor-da-compra
  [compra]
  (and (> (:valor compra) 0)
       (= (type (:valor compra)) BigDecimal)))

(defn valida-estabelecimento-da-compra
  [compra]
  (>= (count (:estabelecimento compra)) 2))

(def categorias ["Alimentação", "Automóvel", "Casa", "Educação", "Lazer", "Saúde"])

(defn valida-categoria-da-compra
  [compra vec-categorias]
  (some #(= (:categoria compra) %) vec-categorias))

;;doseq
(defn valida-compra
  [compra categorias]
  (and (valida-data-da-compra compra)
       (valida-valor-da-compra compra)
       (valida-estabelecimento-da-compra compra)
       (valida-categoria-da-compra compra categorias)))

(defn insere-compra!
  [atomo-compras compra f-validadora categorias]
  (if (f-validadora compra categorias)
                   (swap! atomo-compras insere-compra compra)
                   (throw (ex-info "Compra inválida" {:insere-compra! compra}))))