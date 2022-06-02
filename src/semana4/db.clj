(ns semana4.db
  (:require [datomic.api :as d]))

(def db-uri "datomic:dev://localhost:4334/she-codes")

(defn cria-conexao! []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn apaga-banco! []
  (d/delete-database db-uri))

(def schema-datomic [{:db/ident       :compra/id
                      :db/valueType   :db.type/uuid
                      :db/cardinality :db.cardinality/one
                      :db/doc         "ID da compra"
                      :db/unique      :db.unique/identity}
                     {:db/ident       :compra/data
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Data da compra"}
                     {:db/ident       :compra/valor
                      :db/valueType   :db.type/bigdec
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Valor da compra"}
                     {:db/ident       :compra/estabelecimento
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Estabelecimento da compra"}
                     {:db/ident       :compra/categoria
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Categoria da compra"}
                     {:db/ident       :compra/cartao
                      :db/valueType   :db.type/long
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Cartao da compra"}
                     {:db/ident       :cartao/id
                      :db/valueType   :db.type/uuid
                      :db/cardinality :db.cardinality/one
                      :db/doc         "ID do cartao"
                      :db/unique      :db.unique/identity}
                     {:db/ident       :cartao/numero
                      :db/valueType   :db.type/long
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Numero do Cartão"}
                     {:db/ident       :cartao/cvv
                      :db/valueType   :db.type/long
                      :db/cardinality :db.cardinality/one
                      :db/doc         "CVV do Cartão"}
                     {:db/ident       :cartao/validade
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Validade do Cartão"}
                     {:db/ident       :cartao/limite
                      :db/valueType   :db.type/bigdec
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Limite do Cartão"}
                     {:db/ident       :cartao/cliente
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/many
                      :db/doc         "Cliente do Cartão"}])

(defn aplica-schema! [conn]
  (d/transact conn schema-datomic))

(defn salva-compra! [conn compra]
  (d/transact conn compra))

(defn lista-compras! [db]
  (d/q '[:find (pull ?compra [*])
         :where [?compra :compra/data]]
       db))

(defn salva-cartao! [conn cartao]
  (d/transact conn cartao))

(defn lista-cartoes! [db]
  (d/q '[:find (pull ?cartao [*])
         :where [?cartao :cartao/numero]]
       db))

(defn lista-compras-por-cartao!
  ([db numero]
   (d/q '[:find (pull ?compra [*])
          :in $ ?numero
          :where [?compra :compra/cartao ?numero]]
        db numero))
  ([db numero mes]
   (d/q '[:find (pull ?compra [*])
          :in $ ?numero ?mes
          :where [?compra :compra/cartao ?numero]
                 [?compra :compra/data ?data]
                 [(subs ?data 5 7) ?mes-compra]
                 [(= ?mes-compra ?mes)]]
        db numero mes)))

(defn lista-gastos-por-categorias! [db numero]
  (d/q '[:find ?categoria (pull ?compra [*])
         :in $ ?numero
         :where [?compra :compra/cartao ?numero]
         [?compra :compra/categoria ?categoria]]
       db numero))

