(ns yes-she-codes.semana2.core
  (:use [clojure pprint])
  (:require [yes-she-codes.semana2.logic :as y2.logic]))

(def repositorio-de-compras (atom []))

(defrecord Compra [id data valor estabelecimento categoria cartao])


(defn insere-compra
  [compra lista-de-compras]
  (conj lista-de-compras (assoc compra :id (y2.logic/proximo-id lista-de-compras))))

(def compra1 (map->Compra {:data "01/01/2021"
                                 :valor 50.00M
                                 :estabelecimento "Alura"
                                 :categoria "Educação"
                                 :cartao 1234123412341234}))

(defn insere-compra!
  [compra lista-de-compras]
  (swap! lista-de-compras conj (assoc compra :id (y2.logic/proximo-id @lista-de-compras))))

(pprint (insere-compra compra1 []))

(pprint (insere-compra! compra1 repositorio-de-compras))




















