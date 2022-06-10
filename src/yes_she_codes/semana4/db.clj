(ns yes-she-codes.semana4.db
  (:require [datomic.api :as d]
            [yes-she-codes.utilities.logica :as y.logica]
            [yes-she-codes.semana3.schemas :as s]))


(def db-url "datomic:dev://localhost:4334/she-codes")

(defn cria-conexao []
  (d/create-database db-url)
  (d/connect db-url))

(defn apaga-banco []
  (d/delete-database db-url))

(def schema-datomic [{:db/ident     :compra/data
                      :db/valueType :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc "A data de uma compra"}
                     {:db/ident     :compra/valor
                      :db/valueType :db.type/bigdec
                      :db/cardinality :db.cardinality/one
                      :db/doc "O valor de uma compra"
                      }
                     {:db/ident     :compra/estabelecimento
                      :db/valueType :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc "O estabelecimento de uma compra"}
                     {:db/ident     :compra/categoria
                      :db/valueType :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc "O estabelecimento de uma compra"}
                     {:db/ident     :compra/cartao
                      :db/valueType :db.type/long
                      :db/cardinality :db.cardinality/one
                      :db/doc "Cartao que a compra esta relacionado"
                      }])

(defn cria-schema-datomic [conn]
  (d/transact conn schema-datomic))


(defn compra->compra-datomic [compra]
  (-> compra
        (clojure.set/rename-keys {
                                  :data-compra     :compra/data
                                  :valor           :compra/valor
                                  :estabelecimento :compra/estabelecimento
                                  :cartao          :compra/cartao
                                  :categoria       :compra/categoria})))
(defn salva-compra!
  [conn compra]
  (print compra)
  (->> compra
      compra->compra-datomic
      (conj [])
      (d/transact conn)
))

(defn lista-compras!
  [db]
  (d/q '[:find  ?data, ?valor, ?estbelecimento ?categoria ?cartao
         :where [?produto :compra/data ?data]
                [?produto :compra/valor ?valor]
                [?produto :compra/estabelecimento ?estbelecimento]
                [?produto :compra/categoria ?categoria]
                [?produto :compra/cartao ?cartao]] db))


(defn carrega-compras-no-banco! [conn]
  (let [compras (s/processa-arquivo-de-compras!)]
    (->> compras
         (map #(salva-compra! conn %) ))))

;(defn lista-compras-por-cartao! [db cartao]
;  (d/q '[:find  ?data, ?valor, ?estbelecimento ?categoria ?cartao
;         :in $ ?cartao
;         :where [?produto :compra/cartao ?cartao]
;                [?produto :compra/data ?data]
;                [?produto :compra/valor ?valor]
;                [?produto :compra/estabelecimento ?estbelecimento]
;                [?produto :compra/categoria ?categoria]
;                [?produto :compra/cartao ?cartao]]
;       db cartao))



;(defn lista-compras-por-cartao! [db cartao ]
;  (d/q '[:find (pull ?produto [:compra/data, :compra/valor, :compra/estabelecimento, :compra/categoria, :compra/cartao])
;         :in $ ?cartao
;         :where [?produto :compra/cartao ?cartao]]
;       db cartao ))

(defn lista-compras-por-cartao! [db cartao ]
  (d/q '[:find (pull ?produto [*])
         :in $ ?cartao
         :where [?produto :compra/cartao ?cartao]]
       db cartao ))


(defn get-mes
  [data]
  (subs data 5 7))

(defn filtro-mes [data mes]
  (-> (get-mes data)
      (= mes)))

;(defn lista-compras-por-cartao-e-mes! [db cartao mes ]
;  (-> '[:find (pull ?produto [*])
;         :in $ ?cartao ?mes
;         :where [?produto :compra/cartao ?cartao]
;                [?produto :compra/data ?data]
;                [(yes-she-codes.semana4.db/filtro-mes ?data ?mes)]]
;      (d/q db cartao mes)
;      flatten
;      (#(mapv compra->compra-datomic %))))



;(defn lista-compras-por-cartao-e-mes [snapshot cartao mes]
;  (-> '[:find (pull ?id-compra [*])
;        :in $ ?cartao ?mes
;        :where [?id-compra :compra/cartao ?cartao]
;        [?id-compra :compra/data ?data]
;        [(captura-mes ?data )]]
;      (d/q snapshot [:cartao/numero cartao] mes)
;      flatten
;      (#(mapv s/->Compra %))))


