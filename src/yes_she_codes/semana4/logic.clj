(ns yes_she_codes.semana4.logic
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [yes_she_codes.semana4.db :as y.db]
            [yes_she_codes.semana4.utils :as y.utils]
            [yes_she_codes.semana4.models.compra :as y.compra]
            [yes_she_codes.semana4.models.cartao :as y.cartao]
            [java-time :as time]))

(def conn (y.db/abre-conexao))

(pprint (y.db/cria-schema conn))

;(pprint (y.db/carrega-cartoes-no-banco! conn "/Users/carolina.nunes/IdeaProjects/yes-she-codes/files/cartoes.csv" y.cartao/transforma-cartao))

;(pprint (y.db/carrega-compras-no-banco-com-ref! conn "/Users/carolina.nunes/IdeaProjects/yes-she-codes/files/compras.csv" y.compra/transforma-compra))

;(pprint (y.db/lista-cartoes! (d/db conn)))
;
;(pprint (y.db/lista-compras! (d/db conn)))
;
;(pprint (y.db/lista-compras-por-cartao-2! (d/db conn) 4321432143214321))

;(y.db/apaga-banco)