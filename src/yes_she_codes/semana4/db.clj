(ns yes-she-codes.semana4.db
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [yes-she-codes.semana4.model :as y.model]
            [yes_she_codes.semana1.logic :as y.logic]))

(def db-uri "datomic:dev://localhost:4334/she-codes")

(defn cria-conexao! []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn apaga-conexao! []
  (d/delete-database db-uri))

(def schema [{:db/ident       :compra/data
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
              :db/valueType   :db.type/long
              :db/cardinality :db.cardinality/one}])

(defn cria-schema
  [conn schema]
  (d/transact conn schema))

(defn lista-compras []
  (let [lista-de-compras-nao-tratados (y.logic/csv-to-map "resources/compras.csv")
        lista-de-compras-tratados (map y.logic/extrai-dados-compras lista-de-compras-nao-tratados)]
    (->> lista-de-compras-tratados
         (mapv y.model/nova-compra))))

(defn salva-compra!
  [conn compra]
  (d/transact conn [compra]))

(defn carrega-compras-no-banco!
  [conn]
  (let [compras-csv (lista-compras)]
    (mapv #(salva-compra! conn %) compras-csv)))

(defn lista-compras!
  [db]
  (d/q '[:find (pull ?entidade [*])
         :where [?entidade :compra/estabelecimento]] db))

(defn lista-compras-por-cartao!
  ([db numero-cartao-procurado mes-procurado]
   (d/q '[:find (pull ?compra [*])
          :in $ ?cartao ?mes
          :where [?compra :compra/cartao ?cartao]
                 [?compra :compra/data ?data]
                 [(#(inc (.getMonth %)) ?data) ?mes-compra]
                 [(= ?mes-compra ?mes)]]
        db numero-cartao-procurado mes-procurado))
  ([db numero-cartao-procurado]
   (d/q '[:find (pull ?compra [*])
          :in $ ?cartao
          :where [?compra :compra/cartao ?cartao]]
        db numero-cartao-procurado)))

;(defn lista-gastos-por-categoria!
;  [db cartao-procurado]
;  (d/q '[:find ?categoria (pull (distinct ?compra) [*])
;         :in $ ?cartao
;         :keys categoria compras
;         :where [?compra :compra/cartao ?cartao]
;                [?compra :compra/categoria ?categoria]]
;       db cartao-procurado))