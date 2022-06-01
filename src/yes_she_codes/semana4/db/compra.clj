(ns yes-she-codes.semana4.db.compra
  (:require [datomic.api :as d]
            [schema.core :as s]
            [yes-she-codes.semana4.model :as model]
            [yes-she-codes.semana4.leitor_csv :as csv]
            [yes-she-codes.semana4.db.entidade :as db.entidade]
            [yes-she-codes.semana4.db.cartao :as db.cartao]))

(s/defn adiciona-ou-altera! [conn, compras :- [model/Compra]]
        (d/transact conn compras))

(defn- adiciona-cartao-na-compra [conn compra numero-cartao]
  (let [cartao (db.cartao/busca-por-numero (d/db conn) numero-cartao)]
    (assoc compra :compra/cartao cartao)))

(defn carrega! [conn caminho]
  (let [compras (csv/carrega-compras caminho)
        compras-com-cartao (map #(adiciona-cartao-na-compra conn % (get % :compra/cartao)) compras)]
    (adiciona-ou-altera! conn compras-com-cartao)))

(s/defn lista :- [model/Compra] [db]
  (db.entidade/datomic-para-entidade (d/q '[:find [(pull ?compra [* {:compra/cartao [*]}]) ...]
                                            :where [?compra :compra/data]] db)))

(s/defn lista-por-cartao :- [model/Compra]
  ([db, numero-cartao :- Long]
   (db.entidade/datomic-para-entidade (d/q '[:find [(pull ?compra [* {:compra/cartao [*]}]) ...]
                                             :where [?cartao :cartao/numero ?numero-cartao]
                                                    [?compra :compra/cartao ?cartao]
                                             :in $ ?numero-cartao]
                                           db numero-cartao)))
  ([db, numero-cartao :- Long, mes :- s/Str]
   (db.entidade/datomic-para-entidade (d/q '[:find [(pull ?compra [* {:compra/cartao [*]}]) ...]
                                             :where [?cartao :cartao/numero ?numero-cartao]
                                                    [?compra :compra/cartao ?cartao]
                                                    [?compra :compra/data ?data]
                                                    [(re-matches #"\d{4}-(\d{2})-\d{2}" ?data) ?re-match-result]
                                                    [(second ?re-match-result) ?mes-compra]
                                                    [(= ?mes-compra ?mes)]
                                             :in $ ?numero-cartao ?mes]
                                           db numero-cartao mes))))

(s/defn lista-por-categoria [db, numero-cartao :- Long]
  (group-by :compra/categoria (db.entidade/datomic-para-entidade (d/q '[:find [(pull ?compra [* {:compra/cartao [*]}]) ...]
                                                                        :where [?cartao :cartao/numero ?numero-cartao]
                                                                               [?compra :compra/cartao ?cartao]
                                                                        :in $ ?numero-cartao]
                                                                      db numero-cartao))))