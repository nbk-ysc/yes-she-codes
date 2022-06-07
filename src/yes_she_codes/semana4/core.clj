(ns yes_she_codes.semana4.core
  (:require [datomic.api :as d]
            [yes-she-codes.semana4.compra :as compra]
            [yes_she_codes.semana4.db :as db])
  (:use [clojure.pprint]))


;Chama funçao que cria o banco e faz conexão
(def conn (db/carrega-db!))

;Funcao salva dados de novas compras no banco
(defn salva-compra! [conn compra]
  (let [compra (db/compra->registro-datomic compra)]
    (d/transact conn [compra])))

;(salva-compra! conn (compra/nova-compra "2022-01-02" 260.00M "Dentista" "Saúde" 1234123412341234))

;Funçao que lista tdas as compras
(defn lista-compras! [conn]
  (db/cria-snapshot conn)
  (vec (flatten (d/q '[:find (pull ?compra [*])
                       :where [?compra :compra/valor]]
                     db))))

;(pprint (lista-compras! conn))

;Funçao que lista tdas as compras por cartao
(defn lista-compras-cartao! [conn]
  (db/cria-snapshot conn)
  (vec (flatten (d/q '[:find (pull ?compra [*])
                       :where [?compra :compra/cartao]]
                     db))))