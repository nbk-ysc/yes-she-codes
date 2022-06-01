(ns lastweek.db
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [lastweek.model :as model]
            [lastweek.util :as util]))

(def she-codes "datomic:dev://localhost:4334/yes-she-codes")

(defn cria-conexao []
  (d/create-database she-codes)
  (d/connect she-codes))

(defn apaga-banco []
  (d/delete-database she-codes))

(def schema-datomic [{:db/ident       :compra/data
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "A data de uma compra"}
                     {:db/ident       :compra/valor
                      :db/valueType   :db.type/bigdec
                      :db/cardinality :db.cardinality/one
                      :db/doc         "O valor de uma compra"}
                     {:db/ident       :compra/estabelecimento
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "O estabelecimento de uma compra"}
                     {:db/ident       :compra/categoria
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "A categoria de uma compra"}
                     {:db/ident       :compra/cartao
                      :db/valueType   :db.type/long
                      :db/cardinality :db.cardinality/one
                      :db/doc         "O cartao de uma compra"}])

(defn cria-schema [conn]
  (d/transact conn schema-datomic))

(defn salva-compra! [conn compra]
  @(d/transact conn [compra]))

(defn carrega-compras-no-banco! [conn]
  (let [lista-compras (util/processa-csv "dados/compras.csv" (fn [[data valor estabelecimento categoria cartao]]
                                                               (model/nova-compra data valor estabelecimento categoria cartao)))]
  (doseq [compra lista-compras] (salva-compra! conn compra))))

(defn lista-compras! [conn]
  (d/q '[:find (pull ?e [*])
         :where [?e :compra/cartao]] conn))


(defn lista-compras-por-cartao! ([conn cartao mes]
                                 (d/q '[:find (pull ?e [*])
                                        :in $ ?cartao ?mes
                                        :where [?e :compra/cartao ?cartao]
                                        [?e :compra/data ?data]
                                        [(util/get-moth ?data)]
                                        [(= ?data ?mes)]] conn cartao mes)
                                 )
  ([conn cartao]
  (d/q '[:find (pull ?e [*])
         :in $ ?cartao
         :where [?e :compra/cartao ?cartao]] conn cartao)))

