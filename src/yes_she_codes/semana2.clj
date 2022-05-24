(ns yes-she-codes.semana2
  (:use [clojure pprint]))

(defn proximo-id [entidades]
  (if-not (empty? entidades)
    (+ 1 (apply max (map :id entidades)))
    1))

(def repositorio-de-compras (atom []))

(defrecord Compra [^Long id ^String data ^BigDecimal valor ^String estabelecimento ^String categoria ^String cartao])

(defn insere-compra
  [compras compra]
  (conj @compras (assoc compra :id (proximo-id @compras))))

(insere-compra repositorio-de-compras (Compra. nil, "2022-01-01", 129.90, "Outback", "Alimentação", 1234123412341234))

(defn insere-compra!
  [compras compra]
  (swap! compras conj (assoc compra :id (proximo-id @compras))))

(insere-compra! repositorio-de-compras (Compra. nil, "2022-01-01", 129.90, "Outback", "Alimentação", 1234123412341234))

(defn lista-compras!
  [compras]
  (pprint @compras))

(lista-compras! repositorio-de-compras)

(defn pesquisa-compra-por-id
  [compras id-compra]
  (filter #(= id-compra (:id %)) compras))

(pesquisa-compra-por-id @repositorio-de-compras 1)

(defn pesquisa-compra-por-id!
  [compras id-compra]
  (filter #(= id-compra (:id %)) @compras))

(pesquisa-compra-por-id! repositorio-de-compras 1)

(defn exclui-compra!
  [compras id-compra]
  (remove (pesquisa-compra-por-id compras id-compra)))

(exclui-compra! @repositorio-de-compras 1)

(pprint @repositorio-de-compras)