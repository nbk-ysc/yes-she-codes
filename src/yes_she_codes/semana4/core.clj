(ns yes_she_codes.semana4.core
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [yes_she_codes.semana4.db :as db]
            [yes-she-codes.semana4.compra :as compra]))


;Chama funçao que cria o banco e faz conexão
(def conn (db/cria-conexão))
(pprint conn)

(db/cria-esquema conn)

(let [compra (compra/nova-compra "/compra_outback" "2022-01-02" 345.83M "Outback" "Alimentacao" 1234123412341234)]
  (d/transact conn [compra]))

(d/q )