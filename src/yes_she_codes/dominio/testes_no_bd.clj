(ns yes-she-codes.dominio.testes_no_bd
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [yes-she-codes.persistencia.db :as db]
            [yes-she-codes.dominio.compra :refer :all]
            [java-time :as time]))

;************************TESTES***********************
(def conn (db/cria-conexao))
(db/cria-schema conn)

(def compra1 (map->Compra {:id              1
                           :data            (time/local-date-time),
                           :valor           300.00M,
                           :estabelecimento "Cinema",
                           :categoria       "Lazer",
                           :cartao          3939393939393939}))

(def compra2 (map->Compra {:id              2
                           :data            (time/local-date-time),
                           :valor           380.00M,
                           :estabelecimento "Farmacia",
                           :categoria       "Saude",
                           :cartao          1234123412341234}))


(db/salva-compra conn compra1)
(db/salva-compra conn compra2)
(db/lista-compras (d/db conn))

(db/carrega-compras-no-banco conn)
(db/lista-compras (d/db conn))

(db/lista-compras-por-cartao (d/db conn) 1234123412341234)

(db/lista-compras-por-cartao-e-mes (d/db conn) 1234123412341234 1)
(db/lista-compras-por-categoria (d/db conn))
(db/carrega-cartoes-no-banco conn)
(db/lista-cartoes (d/db conn))
;(db/apaga-banco)