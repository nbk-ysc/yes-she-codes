(ns yes-she-codes.semana4.core
    (:require [datomic.api :as d]
              [yes-she-codes.semana4.db :as db]
               [yes-she-codes.semana4.model :as m]
              [ yes_she_codes.csv.leitor :as l]))

(def conn (db/cria-conexao))

(defn salva-compra! [conn compra]
  (d/transact conn [compra]))

(defn lista-compras! [conn]
      (db/todos-as-compras conn) )

(defn lista-compras-por-cartao!
      ([conn cartao]
      (db/filtrar-por-cartao conn cartao))

      ([conn cartao mes]
       (db/filtrar-compar-por-cartao-mes conn cartao mes))
      )

(defn carrega-compras-no-banco! [conn]
      l/processa-arquivo-de-compras! conn)



