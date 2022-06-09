(ns semana4.db
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [semana2.banco2 :as compras]
            [java-time :as t]
            [semana4.csv :as b.recupera-dados]))

(def lista-de-compras b.recupera-dados/lista-compras)
(def lista-de-compras-vetorizada (vec lista-de-compras))

(def schema-datomic
  [;Schema de compra
   {:db/ident       :compra/data
    :db/valueType   :db.type/instant
    :db/cardinality :db.cardinality/one
    :db/doc         "Data da compra"}
   {:db/ident       :compra/valor
    :db/valueType   :db.type/bigdec
    :db/cardinality :db.cardinality/one
    :db/doc         "Valor total da compra"}
   {:db/ident       :compra/estabelecimento
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "Local onde foi realizada a compra"}
   {:db/ident       :compra/categoria
    :db/valueType   :db.type/string
    :db/cardinality :db.cardinality/one
    :db/doc         "Categoria da compra"}
   {:db/ident       :compra/cartao
    :db/valueType   :db.type/long
    :db/cardinality :db.cardinality/one
    :db/doc         "Cartão utilizado na compra"}])

(def db-url
  "datomic:dev://localhost:4334/she-codes")

(defn cria-conexao []
  (d/create-database db-url)
  (d/connect db-url))

(defn apaga-banco! []
  (d/delete-database db-url))

(defn cria-schema! [conn]
  (d/transact conn schema-datomic))

(apaga-banco!)

(def conexao
  (cria-conexao))

(cria-schema! conexao)

(defn converte-compra-para-datomic [compra]
  (-> compra
      (clojure.set/rename-keys {:DATA            :compra/data
                                :VALOR           :compra/valor
                                :CARTÃO          :compra/cartao
                                :CATEGORIA       :compra/categoria
                                :ESTABELECIMENTO :compra/estabelecimento})
      (dissoc :ID)
      semana4.util/converte-java-time-para-util-date
      ))

(defn salva-compra! [conn compra]
  (let [registro-datomic (converte-compra-para-datomic compra)]
    (d/transact conn [registro-datomic])))

(def compra1 (compras/->Compra 2, (t/local-date-time), 10M, "Outback", "Comida", 1213115416464))

;(pprint compra1)

;(pprint (salva-compra! conexao compra1))

(defn lista-compras! [conn]
  (vec (flatten (d/q '[:find (pull ?compra [*])
                       :where [?compra :compra/valor]]
                     (d/db conn)))))

(defn carrega-compras-no-banco! [conn]
  (mapv
    (partial salva-compra! conn)
    lista-de-compras-vetorizada))

(carrega-compras-no-banco! conexao)

;(pprint (lista-compras! conexao))


(defn lista-compras-por-cartao! [snapshot cartao]
  (vec (flatten (d/q '[:find (pull ?compra [*])
                       :in $ ?cartao
                       :where [?compra :compra/cartao ?cartao]]
                     snapshot cartao))))

;(pprint (lista-compras-por-cartao! (d/db conexao) 3939393939393939))


(defn lista-compras-por-cartao-e-categoria! [snapshot cartao categoria]
  (vec (flatten (d/q '[:find (pull ?compra [*])
                       :in $ ?cartao ?categoria
                       :where [?compra :compra/cartao ?cartao]
                              [?compra :compra/categoria ?categoria]]
                     snapshot cartao categoria))))

(pprint (lista-compras-por-cartao-e-categoria! (d/db conexao) 3939393939393939 "Lazer"))

;;;;não consegui fazer filtrando a data também, então deixei um exemplo com cartão + categoria (vou estudar mais para fazer a data)