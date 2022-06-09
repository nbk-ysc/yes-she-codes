(ns yes-she-codes.semana4.core
  (:use [clojure pprint])
  (:require [datomic.api :as d]
            [yes-she-codes.semana4.db :as y.db]))



(def conn (y.db/cria-conexao))

(y.db/cria-schema conn)


(pprint (y.db/lista-compras! (d/db conn)))

(pprint (y.db/lista-compras-por-cartao! (d/db conn) 1234123412341234 "01"))

(y.db/carrega-compras-no-banco! conn)

(y.db/lista-compras-por-categoria! (d/db conn))

(y.db/carrega-cartoes-no-banco! conn)

(y.db/lista-cartoes! (d/db conn))















