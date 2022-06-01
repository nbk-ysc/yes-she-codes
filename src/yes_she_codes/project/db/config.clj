(ns yes-she-codes.project.db.config
  [:require [datomic.api :as d]
            [schema.core :as s]])

(def db-uri "datomic:dev://localhost:4334/she-codes")

(defn criar-conexao!
  []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn apagar-db!
  []
  (d/delete-database db-uri))


(s/defschema schema-datomic [

                             ;;; CLIENTE
                             {:db/ident       :cliente/id
                              :db/valueType   :db.type/uuid
                              :db/cardinality :db.cardinality/one
                              :db/unique      :db.unique/identity}
                             {:db/ident       :cliente/nome
                              :db/valueType   :db.type/string
                              :db/cardinality :db.cardinality/one}
                             {:db/ident       :cliente/cpf
                              :db/valueType   :db.type/string
                              :db/cardinality :db.cardinality/one}
                             {:db/ident       :cliente/email
                              :db/valueType   :db.type/string
                              :db/cardinality :db.cardinality/one}

                             ;;; CARTAO
                             {:db/ident       :cartao/id
                              :db/valueType   :db.type/uuid
                              :db/cardinality :db.cardinality/one
                              :db/unique      :db.unique/identity}
                             {:db/ident       :cartao/numero
                              :db/valueType   :db.type/long
                              :db/cardinality :db.cardinality/one}
                             {:db/ident       :cartao/cvv
                              :db/valueType   :db.type/long
                              :db/cardinality :db.cardinality/one}
                             {:db/ident       :cartao/validade
                              :db/valueType   :db.type/instant
                              :db/cardinality :db.cardinality/one}
                             {:db/ident       :cartao/limite
                              :db/valueType   :db.type/bigdec
                              :db/cardinality :db.cardinality/one}
                             {:db/ident       :cartao/cliente
                              :db/valueType   :db.type/string
                              :db/cardinality :db.cardinality/one}

                             ;;; COMPRA
                             {:db/ident       :compra/id
                              :db/valueType   :db.type/uuid
                              :db/cardinality :db.cardinality/one
                              :db/unique      :db.unique/identity}
                             {:db/ident       :compra/data
                              :db/valueType   :db.type/instant
                              :db/cardinality :db.cardinality/one}
                             {:db/ident       :compra/valor
                              :db/valueType   :db.type/bigdec
                              :db/cardinality :db.cardinality/one}
                             {:db/ident       :compra/estabelecimento
                              :db/valueType   :db.type/string
                              :db/cardinality :db.cardinality/one}
                             {:db/ident       :compra/categoria
                              :db/valueType   :db.type/string
                              :db/cardinality :db.cardinality/one}
                             {:db/ident       :compra/cartao
                              :db/valueType   :db.type/ref
                              :db/cardinality :db.cardinality/one}




                             ])

(defn criar-schema!
  [conn]
  (d/transact conn schema-datomic))