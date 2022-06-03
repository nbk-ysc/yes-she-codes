(ns yes_she_codes.semana4.core
  (:require [datomic.api :as d]
            [yes-she-codes.semana4.compra :as compra]
            [yes_she_codes.semana4.db :as db])
  (:use [clojure.pprint]))


;Chama funçao que cria o banco e faz conexão
(def conn (db/cria-conexão))
(pprint conn)

(db/cria-esquema conn)

;Funcao salva dados de novas compras no banco
(defn salva-compra! [conn compra]
  (let [compra compra]
    (d/transact conn [compra])))

(salva-compra! conn (compra/nova-compra "/compra_dentista" "2022-01-02" 260.00M "Dentista" "Saúde" 1234123412341234))

(defn lista-compras! [conn]
  (let [conn conn]
    (def db (d/db conn))                                    ;Snapshot do banco nesse exato momento
    (d/q '[:find ?entidade
           :where [?entidade :compra/data]] db)))           ;Select que tras todas as compras de um estabelecimento

(pprint (lista-compras! conn))
