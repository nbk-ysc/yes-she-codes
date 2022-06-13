(ns yes-she-codes.db.datomic)

(def schema-datomic
  [
   {:db/ident       :compra/data
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "Data em que a compra foi realizada."}
   {:db/ident       :compra/valor
    :db/valueType   :db.type/bigdec
    :db/cardinality :db.cardinality/one
    :db/doc         "Valor total da compra."}
   {:db/ident       :compra/estabelecimento
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "Local onde a compra foi realizada."}
   {:db/ident       :compra/categoria
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "Grupo de gastos ao qual a compra pertence."}
   {:db/ident       :compra/cartao
    :db/valueType   :db.type/ref
    :db/cardinality :db.cardinality/one
    :db/doc         "Cartão utilizado na compra."}

   {:db/ident       :cartao/numero
    :db/valueType   :db.type/long
    :db/cardinality :db.cardinality/one
    :db/unique      :db.unique/identity
    :db/doc         "Número identificador do cartão."}
   {:db/ident       :cartao/cvv
    :db/valueType   :db.type/long
    :db/cardinality :db.cardinality/one
    :db/doc         "Número de segurança do cartão."}
   {:db/ident       :cartao/validate
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "Número de segurança do cartão."}
   {:db/ident       :cartao/limite
    :db/valueType   :db.type/bigdec
    :db/cardinality :db.cardinality/one
    :db/doc         "Valor máximo de compras para um cartão."}
   {:db/ident       :cartao/cliente
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "Dono do cartão."}])
