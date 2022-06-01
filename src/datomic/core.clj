(ns datomic.core
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [datomic.db :as db]))

(defn inicia-conexao-e-insere []
  (def conn (db/cria-conexao))
  (db/cria-schema conn)
  (db/carrega-compras-no-banco! conn))

;(inicia-conexao-e-insere)

(def conn (db/cria-conexao))

;(db/cria-schema conn)

;(db/salva-compra! conn (model/nova-compra "12-02-2020" 13.2M "IFOOD" "Alimentação" 1234123412341234))

;(db/carrega-compras-no-banco! conn)

;(db/apaga-banco)

(pprint (db/lista-compras! (d/db conn)))

(count (db/lista-compras! (d/db conn)))

(pprint (db/lista-compras-por-cartao! (d/db conn) 3939393939393939))
;(pprint (db/lista-compras-por-cartao! (d/db conn) 3939393939393939 "04"))

(pprint (db/lista-compras-por-categoria! (d/db conn) 3939393939393939))

;(db/cria-schema-cartao conn)
;(db/carrega-cartoes-no-banco! conn)

(pprint (db/lista-cartoes! (d/db conn)))