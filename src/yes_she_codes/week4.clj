(ns yes-she-codes.week4
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [clojure.string :as str]))

(def db-uri "datomic:dev://localhost:4334/she-codes")

(defn cria-conexao []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn apaga-banco! []
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
  ([conexao]
   (carrega-compras-no-banco! conexao (abre-csv "resources/compras.csv")))

  ([conexao lista-de-listas]
    (map (comp (partial salva-compra! conexao) nova-compra) lista-de-listas)))

(defn lista-compras!
  [conexao]
  (let [db (d/db conexao)]
    (vec (flatten (d/q '[:find (pull ?entidade [:compra/data
                                                :compra/valor
                                                :compra/estabelecimento
                                                :compra/categoria
                                                :compra/cartao])
                         :where [?entidade :compra/data]] db)))))


(defn lista-compras-por-cartao!
  [conexao cartao]
  (let [db (d/db conexao)]
    (vec (flatten (d/q '[:find (pull ?entidade [*])
                         :in $ ?cartao-buscado
                         :where [?entidade :compra/cartao ?cartao-buscado]] db cartao)))))


(defn lista-compras-por-cartao-e-mes!
  [conexao cartao mes]
  (let [db (d/db conexao)]
    (vec (flatten (d/q '[:find (pull ?entidade [*])         ;(pull ?entidade [*])
                         :in $ ?cartao-buscado ?mes-buscado
                         :where [?entidade :compra/cartao ?cartao-buscado]
                         [?entidade :compra/data ?data-buscada]
                         [(re-find #"\d{4}-(\d{2})-\d{2}" ?data-buscada) ?matcher]
                         [(second ?matcher) ?mes]
                         [(= ?mes ?mes-buscado)]
                         ] db cartao mes)))))


;(defn ista-gastos-por-categoria!
;  [conexao cartao]
;  )