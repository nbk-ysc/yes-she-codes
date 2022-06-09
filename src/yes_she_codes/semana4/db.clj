(ns yes-she-codes.semana4.db
    (:require [datomic.api :as d]
      [yes-she-codes.semana4.model :as m]))

(def db-uri "datomic:dev://localhost:4334/she-codes")

(defn cria-conexao []
      (d/create-database db-uri)
      (d/connect db-uri))

(defn apaga-banco []
      (d/delete-database db-uri))

(defn cria-schema! [conn]
      @(d/transact conn m/schema-datomic))

(defn todos-as-compras [db]
      (d/q '[:find (pull ?entidade [*])
             :where [?entidade :compra/nome]] db))

(defn filtra-mes [data mes]
     ( = mes (subs data 5 7)))

(defn filtrar-por-cartao [conn numero-cartao]
      (d/q '[:find (pull ?entidade [*])
              :in $, ?numerocartao
             :where [?entidade :compra/cartao ?numerocartao]] conn numero-cartao))

(defn filtrar-compar-por-cartao-mes [conn numero-cartao mes]
      (d/q '[:find (pull ?entidade [*])
             :in $, ?numerocartao, ?mes
             :where [?entidade :compra/data ?data]
                    [?entidade :compra/cartao ?numerocartao]
                    [(filtra-mes ?data ?mes)]] conn,numero-cartao,mes))

