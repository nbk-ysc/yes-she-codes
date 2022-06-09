(ns yes-she-codes.semana2.core
  (:use clojure.pprint))


;Definindo atomos compras
(def repositorio-de-compras (atom []))

(defrecord Compra [id data valor estabelecimento categoria cartao])

;apply max -> encontra o maior item
(defn proximo-id [compras]
  (not (if (empty? compras)
       (+ 1 (apply max (map :id compras) ))
       1)))


(defn insere-compra [compra atomo]
      (let [id (proximo-id atomo)
            compra-completa (assoc compra :id id )]
           (conj atomo compra-completa)))


(defn insere-compra! [compra atomo]
   (swap! repositorio-de-compras insere-compra compra))


(defn listar-compras! [repositorio-de-compras]
     (pprint @repositorio-de-compras) )

(defn pesquisa-compra [compras id]
      (first (filter #(= id (:id %))compras)))

(defn pesquisa-compra-por-id! [id repositorio-de-compras]
      (pesquisa-compra @repositorio-de-compras id))





















