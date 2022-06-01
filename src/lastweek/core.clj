(ns lastweek.core
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [lastweek.db :as db]
            [lastweek.model :as model]))


(def conn (db/cria-conexao))

(db/cria-schema conn)


(model/salva-compra! conn (model/nova-compra "12-02-2020"
                   13.2M
                   "IFOOD"
                   "Alimentação"
                   1234123412341234))



;(db/apaga-banco)