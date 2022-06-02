(ns yes-she-codes.semana4.core
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [yes-she-codes.semana4.db :as db]
            [yes-she-codes.semana4.model :as model]))


; criando conexao com o banco
(def conn (db/cria-conexao))
(pprint conn)


; mas antes de transacionarmos a compra, temos que transacionar o schema
(db/cria-schema conn)

(db/salva-compra! conn (model/nova-compra "02-06-2022" 150.2M "Nucleo Artistico" "Lazer" 1111222233334444))

