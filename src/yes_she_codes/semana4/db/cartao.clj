(ns yes-she-codes.semana4.db.cartao
  (:require [datomic.api :as d]
            [schema.core :as s]
            [yes-she-codes.semana4.model :as model]
            [yes-she-codes.semana4.leitor_csv :as csv]
            [yes-she-codes.semana4.db.entidade :as db.entidade]))

(s/defn adiciona-ou-altera! [conn, cartoes :- [model/Cartao]]
        (d/transact conn cartoes))

(defn carrega! [conn caminho]
  (let [cartoes (csv/carrega-cartoes caminho)]
    (adiciona-ou-altera! conn cartoes)))

(s/defn lista :- [model/Cartao] [db]
  (db.entidade/datomic-para-entidade (d/q '[:find [(pull ?cartao [*]) ...]
                                            :where [?cartao :cartao/numero]] db)))

(s/defn busca-por-numero [db, numero :- Long]
  (db.entidade/datomic-para-entidade (d/pull db '[*] [:cartao/numero numero])))