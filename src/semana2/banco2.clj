(ns semana2.banco2
  (:use [clojure pprint])
  (:require [java-time :as t]
            [semana1.banco.recupera-dados :as b.recupera-dados]))

(def lista-de-compras b.recupera-dados/lista-compras)
(def lista-de-compras-vetorizada (vec lista-de-compras))

;(pprint lista-de-compras-vetorizada)
;atomos de cada uma das entidades
(def repositorio-de-clientes (atom []))
(def repositorio-de-cartoes (atom []))
(def repositorio-de-compras (atom []))


(defrecord Compra
  [^Long ID, ^String Data, ^BigDecimal Valor, ^String Estabelecimento, ^String Categoria, ^Long Cartao])

(defn lista-compras! [repositorio-de-compras]
  (pprint @repositorio-de-compras))

;(swap! repositorio-de-compras (Compra. 2, "18/05/2022", 10M, "Outback", "Comida", 1213115416464))

;(pprint (Compra. 2, "18/05/2022", 10M, "Outback", "Comida", 1213115416464))
;(swap! repositorio-de-compras conj (Compra. 0, "18/05/2022", 10M, "Outback", "Comida", 1213115416464))

(defn proximo-id [entidades]
  (if-not (empty? entidades)
    (+ 1 (apply max (map :ID entidades)))
    0))

(defn insere-compra! [repositorio-de-compras compra]
  (swap! repositorio-de-compras conj compra))

(defn insere-compra [data, valor, estabelecimento, categoria, cartao]
  (let [id (proximo-id @repositorio-de-compras)]
    (Compra. id, data, valor, estabelecimento, categoria, cartao)))


(defn insere-compra-com-id [[data, valor, estabelecimento, categoria, cartao]]
  (println data)
  (let [id (proximo-id @repositorio-de-compras)]
    (Compra. id, data, valor, estabelecimento, categoria, cartao)))

(defn insere-varias-compras [lista-de-compras]
  (->> lista-de-compras
      (map insere-compra-com-id)))

;(pprint (insere-varias-compras lista-de-compras-vetorizada))

;(pprint (insere-compra "2022-01-01" "129.90" "Outback" "Alimentação" "1234 1234 1234 1234"))

(insere-compra! repositorio-de-compras (insere-compra "2022-01-01" "129.90" "Outback" "Alimentação" "1234 1234 1234 1234"))
(insere-compra! repositorio-de-compras (insere-compra "2022-01-02" "260.00" "Dentista" "Saúde" "1234 1234 1234 1234"))

;(lista-compras! repositorio-de-compras)

(defn pesquisa-compra-por-id [repositorio-de-compras id]
  (seq (filter #(= (:ID %) id) @repositorio-de-compras)))

(defn pesquisa-compra-por-id! [repositorio-de-compras id]
  (seq (filter #(= (:ID %) id) @repositorio-de-compras)))

;(pprint (pesquisa-compra-por-id! repositorio-de-compras 1))

;(pprint @repositorio-de-compras)

;(pprint (Compra. :ID)) 72675