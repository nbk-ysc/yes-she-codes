(ns yes-she-codes.semana4.db
  (:use [clojure pprint])
  (:require [datomic.api :as d]
            [yes-she-codes.semana4.model :as y.model]))

(def db-uri "datomic:dev://localhost:4334/she-codes")

(defn cria-conexao []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn apaga-banco []
  (d/delete-database db-uri))

(def schema-datomic [{:db/ident       :compra/data
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc          "Data da compra"}
                     {:db/ident       :compra/valor
                      :db/valueType   :db.type/bigdec
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Valor da compra"}
                     {:db/ident       :compra/estabelecimento
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Lugar da compra"}
                     {:db/ident       :compra/categoria
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Categoria da compra"}
                     {:db/ident       :compra/cartao
                      :db/valueType   :db.type/long
                      :db/cardinality :db.cardinality/one
                      :db/doc         "O cartão que efetuou a compra"}])


(defn cria-schema [conn]
  (d/transact conn schema-datomic))

(defn salva-compra! [conn compra]
  (d/transact conn [compra]))

(defn lista-compras! [banco]
  (d/q '[:find  (pull ?entidade [*])
         :where [?entidade :compra/data]] banco))

(defn lista-compras-por-cartao!
 ([banco cartao]
  (d/q '[:find  (pull ?entidade [*])
         :in $ ?cartao-procurado
         :where [?entidade :compra/cartao ?cartao-procurado]] banco cartao))
  ([banco cartao data]
   (d/q '[:find  (pull ?entidade [*])
          :in $ ?cartao-procurado ?data-procurada
          :where [?entidade :compra/cartao ?cartao-procurado]
                  [?entidade :compra/data ?data-procurada]] banco cartao data)))



(def csv->compra [str
                  bigdec
                  str
                  str
                  #(Long/parseLong (clojure.string/replace % #" " ""))])

(defn- processa-csv [caminho-arquivo]
  (->> (slurp caminho-arquivo)
       clojure.string/split-lines
       rest
       (map #(clojure.string/split % #","))))

(defn- converte-valores-na-linha [funcoes-de-conversao linha]
  (map #(%1 %2) funcoes-de-conversao linha))

(defn carrega-compras-no-banco! [conn]
  )












