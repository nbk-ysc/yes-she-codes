(ns yes-she-codes.week4
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [clojure.string :as str]))

;; para iniciar: bin/transactor -Ddatomic.printConnectionInfo=true config/dev-transactor-template.properties
(def db-uri "datomic:dev://localhost:4334/she-codes")

(defn cria-conexao []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn apaga-banco []
  (pprint (d/delete-database db-uri)))


(defn schema-datomic [conexao]
  (d/transact conexao [{:db/ident       :compra/data
                        :db/valueType   :db.type/string
                        :db/cardinality :db.cardinality/one
                        :db/doc         "A data de uma compra"}
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
                        :db/valueType   :db.type/long
                        :db/cardinality :db.cardinality/one}]))


(defn nova-compra
  ([[data valor estabelecimento categoria cartao]]
   (nova-compra data valor estabelecimento categoria cartao))

  ([data valor estabelecimento categoria cartao]
   {:compra/data            data
    :compra/valor           (bigdec valor)
    :compra/estabelecimento estabelecimento
    :compra/categoria       categoria
    :compra/cartao          (Long/parseLong (str/replace cartao #" " ""))}))


(defn salva-compra!
  [conexao compra-map-ou-record]
  (d/transact conexao [compra-map-ou-record]))


(defn abre-csv
  [path]
  (->> (slurp path)
       str/split-lines
       rest
       (map #(str/split % #","))))


(defn carrega-compras-no-banco!
  ([]
   (carrega-compras-no-banco! (abre-csv "resources/compras.csv")))

  ([lista-de-listas]
    (map (comp (partial salva-compra! (cria-conexao)) nova-compra) lista-de-listas)))

(defn lista-compras!
  [conexao]
  (let [db (d/db conexao)]
    (vec (d/q '[:find ?entidade
                :where [?entidade :compra/data]] db))))

;
;(defn lista-compras-por-cartao!
;  [conexao cartao]
;  (let [db (d/db conexao)]
;  (vec (d/q '[:find ?entidade
;              :where [?entidade :compra/cartao cartao]] db))))