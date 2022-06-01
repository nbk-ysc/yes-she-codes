(ns datomic.db
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [datomic.model :as model]
            [datomic.util :as util]))

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


(defn lista-compras-por-cartao!
  ([conn cartao mes]
   (d/q '[:find (pull ?e [*])
          :in $ ?cartao ?mes
          :where [?e :compra/cartao ?cartao]
          [_ :compra/data ?data]
          ;[(util/get-moth ?data) ?month]
          [(= ?month ?mes)]] conn cartao mes))
  ([conn cartao]
  (d/q '[:find (pull ?e [*])
         :in $ ?cartao
         :where [?e :compra/cartao ?cartao]] conn cartao)))

(defn lista-compras-por-categoria! [conn cartao]
  (d/q '[:find ?categoria (pull ?e [*])
         :in $ ?cartao
         :where [?e :compra/cartao ?cartao]
         [?e :compra/categoria ?categoria]] conn cartao))


;PARA CARTAO

(def schema-datomic-cartao
                    [{:db/ident       :cartao/numero
                      :db/valueType   :db.type/long
                      :db/cardinality :db.cardinality/one
                      :db/doc         "O numero de um cartao"}
                     {:db/ident       :cartao/cvv
                      :db/valueType   :db.type/long
                      :db/cardinality :db.cardinality/one
                      :db/doc         "O CVV de um cartão"}
                     {:db/ident       :cartao/validade
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "A validade de um cartão"}
                     {:db/ident       :cartao/limite
                      :db/valueType   :db.type/bigdec
                      :db/cardinality :db.cardinality/one
                      :db/doc         "O limite de um cartão"}
                     {:db/ident       :cartao/cliente
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "O cpf de um cliente"}
                    ])

(defn cria-schema-cartao [conn]
  (d/transact conn schema-datomic-cartao))

(defn carrega-cartoes-no-banco! [conn]
  (let [lista-cartoes (util/processa-csv "dados/cartoes.csv" (fn [[numero cvv validade limite cliente]]
                                                          (model/novo-cartao numero cvv validade limite cliente)))]
    (doseq [cartao lista-cartoes] (salva-compra! conn cartao))))

(defn lista-cartoes! [conn]
  (d/q '[:find (pull ?e [*])
         :where [?e :cartao/numero]] conn))