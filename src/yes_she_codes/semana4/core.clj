(ns yes-she-codes.semana4.core
  (:use [clojure pprint])
  (:require [datomic.api :as d]
            [yes-she-codes.semana4.db :as y.db]
            [yes-she-codes.semana4.model :as y.model]))



(def conn (y.db/cria-conexao))

(y.db/cria-schema conn)

(def compra1 (y.model/nova-compra ["2022-02-06" 10M "The Coffee" "Alimentação" 1234123412341234]))
(def compra2 (y.model/nova-compra ["2022-03-06" 10M "The Coffee" "Alimentação" 1234123412341235]))
(def compra3 (y.model/nova-compra ["2022-03-06" 10M "The Coffee" "Alimentação" 1234123412341234]))

(y.db/salva-compra! conn compra1)

(y.db/salva-compra! conn compra2)
(y.db/salva-compra! conn compra3)

(def banco (d/db conn))


(let [celular-barato (novo-produto "Celualr Barato" "/celular-barato" 888.10M)
      resultado @(d/transact conn [celular-barato])
      id-entidade (-> resultado :tempids vals first)]
  @(d/transact conn [[:db/add id-entidade :produto/preco 0.1M]])
  @(d/transact conn [[:db/retract id-entidade :produto/slug "/celular-barato"]]))

(pprint (y.db/lista-compras! (d/db conn)))

(pprint (y.db/lista-compras-por-cartao! (d/db conn) 1234123412341234 "02"))

(y.db/carrega-compras-no-banco! conn)











