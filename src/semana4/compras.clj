(ns semana4.compras
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [semana4.db :as db]
            [semana4.model :as model]
            [semana1.db :as y.db]))

(def conn (db/cria-conexao!))
(db/aplica-schema! conn)


(defn lista-compras []
  (map (fn [[data valor estabelecimento categoria cartao]]
         (model/nova-compra data valor estabelecimento categoria cartao)) (y.db/compras)))

(pprint @(db/salva-compra! conn (lista-compras)))


(def compras (db/lista-compras! (d/db conn)))
(pprint compras)


(pprint (db/lista-compras-por-cartao! (d/db conn) 4321432143214321))

(pprint (db/lista-compras-por-cartao! (d/db conn) 4321432143214321 "02"))

(pprint (db/lista-gastos-por-categorias! (d/db conn) 6655665566556655))


(defn lista-cartoes []
  (map (fn [[numero cvv validade limite cliente]]
         (model/novo-cartao numero cvv validade limite cliente)) (y.db/cartoes)))

(pprint @(db/salva-cartao! conn (lista-cartoes)))


(def cartoes (db/lista-cartoes! (d/db conn)))
(pprint cartoes)


;(db/apaga-banco!)