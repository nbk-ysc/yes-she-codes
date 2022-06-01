(ns lastweek.core
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [lastweek.db :as db]
            [lastweek.model :as model]))


(def conn (db/cria-conexao))

(db/cria-schema conn)

;(db/salva-compra! conn (model/nova-compra "12-02-2020" 13.2M "IFOOD" "Alimentação" 1234123412341234))

;(db/carrega-compras-no-banco! conn)

(db/apaga-banco)

(pprint (db/lista-compras! (d/db conn)))

(count (db/lista-compras! (d/db conn)))

(pprint (db/lista-compras-por-cartao! (d/db conn) 3939393939393939))

(pprint (db/lista-compras-por-cartao! (d/db conn) 3939393939393939 "2022-04-10"))
