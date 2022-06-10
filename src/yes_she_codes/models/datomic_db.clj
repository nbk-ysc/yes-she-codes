(ns yes-she-codes.models.datomic-db)

(def schema-datomic [{:db/ident       :data
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one}
                     {:db/ident       :valor
                      :db/valueType   :db.type/bigdec
                      :db/cardinality :db.cardinality/one}
                     {:db/ident       :estabelecimento
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one}
                     {:db/ident       :categoria
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one}
                     {:db/ident       :cartao
                      :db/valueType   :db.type/long
                      :db/cardinality :db.cardinality/one}])
