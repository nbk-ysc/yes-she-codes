(ns yes-she-codes.db.datomic-db
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [schema.core :as s]
            [yes-she-codes.models.datomic-db :as y.models.datomic-db]
            [yes-she-codes.models.compras :as y.models.compras]
            [yes-she-codes.models.common :as y.models.common]
            [yes-she-codes.logic.compras :as y.logic.compras]
            [yes-she-codes.logic.utils :as y.logic.utils])
  (:import (datomic.peer Connection)
           (datomic.db Db)))

(def db-uri "datomic:dev://localhost:4334/she-codes")

(s/defn cria-conexao :- Connection []
  (d/create-database db-uri)
  (d/connect db-uri))

; To do: Verificar se precisa colocar schema no retorno
(defn apaga-banco! []
  (d/delete-database db-uri))

; To do: Verificar se precisa colocar schema no retorno
(s/defn cria-schema! [conn :- Connection]
  (d/transact conn y.models.datomic-db/schema-datomic))

; To do: Verificar se precisa colocar schema no retorno
(s/defn salva-compra! [conn :- Connection, compra :- y.models.compras/CompraSchema]
  (d/transact conn [compra]))

; To do: Verificar se precisa colocar schema no retorno
(s/defn carrega-compras-no-banco! [conn :- Connection]
  (doseq
    [compra (y.logic.compras/lista-compras)]
    (salva-compra! conn compra)))

; To do: Verificar se precisa colocar schema no retorno
(s/defn lista-compras! [db :- Db]
  (d/q '[:find (pull ?compra [*])
         :where [?compra :data]] db))

; To do: Verificar se precisa colocar schema no mês
(s/defn compra-realizada-no-mes? :- Boolean [mes, data-da-compra :- y.models.compras/DataSchema]
  (= mes (y.logic.utils/get-mes data-da-compra)))

; To do: Verificar se precisa colocar schema no retorno
(s/defn lista-compras-por-cartao!
  ([db :- Db, cartao-procurado :- y.models.common/NumeroDoCartaoSchema]
   (d/q '[:find (pull ?compra [*])
          :in $, ?cartao-procurado
          :where [?compra :cartao ?cartao-procurado]]
        db, cartao-procurado))
  ; To do: Verificar se precisa colocar schema no mês
  ([db :- Db, cartao-procurado :- y.models.common/NumeroDoCartaoSchema, mes-procurado]
   (d/q '[:find (pull ?compra [*])
          :in $, ?cartao-procurado, ?mes-procurado
          :where [?compra :cartao ?cartao-procurado]
          [?compra :data ?data]
          [(yes-she-codes.db.datomic-db/compra-realizada-no-mes? ?mes-procurado ?data)]]
        db, cartao-procurado, mes-procurado)))
