(ns yes-she-codes.semana4.compra
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [yes-she-codes.semana4.db :as db]))

(def conn (db/cria-conexao))

(db/cria-schema-datomic conn)

(let [compra  {:data-compra "2022-01-07", :valor 129.90M, :estabelecimento "Outback", :categoria "Alimentação", :cartao 1234123412341234M}]
  (db/salva-compra! conn compra))

(db/carrega-compras-no-banco! conn)

(db/lista-compras! (d/db conn))

(db/lista-compras-por-cartao-e-mes! (d/db conn) 6655665566556655 "03")

;(db/apaga-banco)
