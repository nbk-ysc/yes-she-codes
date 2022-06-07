(ns yes_she_codes.semana4.db
  (:require [datomic.api :as d])
  (:use [clojure.pprint]))


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
                      :db/doc         "O estabelecimento de uma compra"}])

(def db-uri "datomic:dev://localhost:4334/she-codes")

(defn cria-conexao []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn apaga-banco! []
  (d/delete-database db-uri))

(defn cria-esquema! [conn]
  (d/transact conn schema-datomic))

;Snapshot do banco no momento q é invocado
(defn cria-snapshot [conn]
  (def db (d/db conn)))


;Exemplo do Cacio
#_(defn compras-csv! []
    (->> (y.csv/processa-arquivo-de-compras!)
         (map compra->registro-datomic)))

(defn carrega-db! []
  (apaga-banco!)
  (let [conn (cria-conexao)]
    (println "Criou o squema" @(cria-esquema! conn))))


(defn compra->registro-datomic [compra]
  (-> compra
      (clojure.set/rename-keys {:data            :compra/data
                                :valor           :compra/valor
                                :categoria       :compra/categoria
                                :estabelecimento :compra/estabelecimento
                                :cartao          :compra/cartao})))