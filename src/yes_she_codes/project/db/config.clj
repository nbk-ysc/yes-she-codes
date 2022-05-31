;(ns yes-she-codes.project.db.config
;  [:require []
;            [datomic.api :as d]
;            [schema.core :as s]
;            ])
;
;(def db-uri "datomic:dev://localhost:4334/ecommerce")
;
;(defn open-conn! []
;  (d/create-database db-uri)
;  (d/connect db-uri)
;  )
;
;(defn delete-db!
;  []
;  (d/delete-database db-uri)
;  )
;
;
;; Products
;; id?
;; :name String 1 => Expensive Computer
;; :slug String 1 => /expensive_computer
;; :price Float 1 => 3500.10
;;
;; entity_id attribute_name attribute_value tx_id operation
;; 15 :product/name Expensive Computer #101010 true
;; 15 :product/slug /expensive_computer #101010 true
;; 15 :product/price 3500.10 #101010 true
;; 17 :product/name Fancy Mobile #456456 true
;; 17 :product/slug /fancy_mobile #456456 true
;; 17 :product/price 888.88 #456456 true
;
;; 37 :category/name electronics
;
;
;(def schema [
;
;
;             ;;; COMPRA
;             {:db/ident :compra/data
;              :db/valueType :db.type/string
;              :db/cardinality :db.cardinality/one
;              :db/doc "The name of the Product in the Catalog"}
;             {:db/ident :product/slug
;              :db/valueType :db.type/string
;              :db/cardinality :db.cardinality/one
;              :db/doc "The uri fragment required to access a Product using http"}
;             {:db/ident :product/price
;              :db/valueType :db.type/bigdec
;              :db/cardinality :db.cardinality/one
;              :db/doc "The price of the Product with monetary precision"}
;             {:db/ident :product/keyword
;              :db/valueType :db.type/string
;              :db/cardinality :db.cardinality/many
;              :db/doc "Tag info"}
;             {:db/ident :product/id
;              :db/valueType :db.type/uuid
;              :db/cardinality :db.cardinality/one
;              :db/unique :db.unique/identity}
;             {:db/ident :product/category
;              :db/valueType :db.type/ref
;              :db/cardinality :db.cardinality/one
;              :db/doc "The category ref in witch the product belongs"}
;
;
;             ;;; CATEGORY
;             {:db/ident :category/name
;              :db/valueType :db.type/string
;              :db/cardinality :db.cardinality/one}
;             {:db/ident :category/id
;              :db/valueType :db.type/uuid
;              :db/cardinality :db.cardinality/one
;              :db/unique :db.unique/identity}
;
;
;
;
;
;             ;;; TRANSACTIONS
;             {:db/ident :tx-data/ip
;              :db/valueType :db.type/string
;              :db/cardinality :db.cardinality/one}
;             ])
