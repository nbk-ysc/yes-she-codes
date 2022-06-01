(ns semana4.db
  (:require [datomic.api :as d]))

(def db-uri "datomic:dev://localhost:4334/she-codes")

(defn cria-conexao []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn apaga-banco []
  (d/delete-database db-uri))

(def schema-datomic [{:db/ident       :data
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Data da compra"}
                     {:db/ident       :valor
                      :db/valueType   :db.type/bigdec
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Valor da compra"}
                     {:db/ident       :estabelecimento
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Estabelecimento da compra"}
                     {:db/ident       :categoria
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Categoria da compra"}
                     {:db/ident       :cartao
                      :db/valueType   :db.type/long
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Cartao da compra"}])

(defn aplica-schema [conn]
  (d/transact conn schema-datomic))

(defn salva-compra! [conn compra]
  (d/transact conn compra))

(defn lista-compras! [db]
  (d/q '[:find (pull ?compra [*])
         :where [?compra :data]]
       db))

(defn lista-compras-por-cartao!
  ([db numero]
   (d/q '[:find (pull ?compra [*])
          :in $ ?numero
          :where [?compra :cartao ?numero]]
        db numero))
  ([db numero mes]
   (d/q '[:find (pull ?compra [*])
          :in $ ?numero ?mes
          :where [?compra :cartao ?numero]
                 [?compra :data ?data]
                 [(subs ?data 5 7) ?mes-compra]
                 [(= ?mes-compra ?mes)]]
        db numero mes)))

(defn lista-gastos-por-categorias! [db numero]
  (d/q '[:find ?categoria (pull ?compra [*])
         :in $ ?numero
         :where [?compra :cartao ?numero]
         [?compra :categoria ?categoria]]
       db numero))

