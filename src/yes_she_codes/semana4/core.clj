(ns yes_she_codes.semana4.core
  (:require [datomic.api :as d]
            [yes-she-codes.semana4.compra :as compra]
            [yes_she_codes.semana4.db :as db])
  (:use [clojure.pprint]))


;Chama funçao que cria o banco e faz conexão
(def conn (db/carrega-db!))

;(db/salva-compra! conn (compra/nova-compra "2022-01-02" 260.00M "Dentista" "Saúde" 1234123412341234))

;Funçao que lista tdas as compras
(defn lista-compras! [conn]
  (db/cria-snapshot conn)
  (vec (flatten (d/q '[:find (pull ?compra [*])
                       :where [?compra :compra/valor]]
                     db))))

;(pprint (lista-compras! conn))

;Funçao que lista tdas as compras por cartao
(defn lista-compras-cartao! [conn cartao]
  (db/cria-snapshot conn)
  (d/q '[:find (pull ?compra [*])
         :in $ ?cartao
         :where [?compra :compra/cartao ?cartao]]
       db cartao))

;(pprint (lista-compras-cartao! conn 123123123134))

(defn lista-compras-cartao-mes! [conn cartao mes]
  (db/cria-snapshot conn)
  (d/q '[:find [(pull ?compra [*])]
         :in $ ?cartao ?mes
         :where [?cartao :compra/cartao ?cartao]
         [?compra :compra/data ?data]
         [(re-find #"\d{4}-(\d{2})-\d{2}" ?data) ?matcher]
         [(second ?matcher) ?mes]
         [(= ?mes ?data)]]
       db cartao mes))

;(pprint (lista-compras-cartao-mes! conn 123123123134 03))