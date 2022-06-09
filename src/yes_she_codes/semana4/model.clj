(ns yes-she-codes.semana4.model
    (:require [datomic.api :as d]))

(defn nova-compra [nome valor estabelecimento categoria cartao]
      {:compra/nome nome
       :compra/valor valor
       :compra/estabelecimento estabelecimento
       :compra/categoria categoria
       :compra/cartao cartao})

(def schema-datomic [{:db/ident :compra/data
                      :db/valueType :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc "o nome do produto"}

                   {:db/ident :compra/valor
                    :db/valueType :db.type/digdec
                    :db/cardinality :db.cardinality/one
                    :db/doc "o caminho para acessar via http"}

                   {:db/ident :compra/estabelecimento
                    :db/valueType :db.type/digdec
                    :db/cardinality :db.cardinality/one
                    :db/doc "o preco do produto"}

                     {:db/ident :compra/categoria
                      :db/valueType :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc "o preco do produto"}

                     {:db/ident :compra/cartao
                      :db/valueType :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc "o preco do produto"}
                    ])
