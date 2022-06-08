(ns yes-she-codes.project.controller.compra
  (:require [schema.core :as s]
            [yes-she-codes.project.diplomat.csv.csv :as diplomat.csv]
            [yes-she-codes.project.logic.common.common :as logic.common]
            [yes-she-codes.project.model.compra :as model.compra]
            [yes-she-codes.project.model.cartao :as model.cartao]
            [yes-she-codes.project.adapter.compra :as adapter.compra]
            [yes-she-codes.project.db.compra :as db.compra]
            [yes-she-codes.project.db.cartao :as db.cartao]
            [yes-she-codes.project.db.config :refer [snapshot!]]))

;;; ATOM

(defn insere-compra!
  [compras record]
  (swap! compras logic.common/insere-record record))

(defn lista-compras-dominio!
  [compras]
  (logic.common/lista-entidade @compras))

(defn pesquisa-compra-por-id!
  [compras id]
  (logic.common/pesquisa-record-por-id @compras id))

(defn exclui-compra!
  [compras id]
  (swap! compras logic.common/exclui-record id))

(s/defn carrega-compras-no-domínio!
  [filepath-dados :- s/Str
   atom]
  (if-let [cartoes (adapter.compra/csv->model
                     (diplomat.csv/read-csv! filepath-dados))]
    (mapv (partial insere-compra! atom) cartoes)))


;;; DATABASE

(s/defn output-datomic :- [model.compra/CompraComComponentes]
  [datomic-vector]
  (mapv adapter.compra/datomic->model-with-components datomic-vector))

(s/defn lista-compras! :- [model.compra/CompraComComponentes]
  [db]
  (if-let [datomic-obj (db.compra/lista-compras db)]
    (output-datomic datomic-obj)))

(s/defn salva-compra!
  [conn
   compra :- model.compra/Compra]
  (if-let [cartao-vinculado (db.cartao/cartao-por-numero (snapshot! conn) (:compra/cartao compra))]
    (db.compra/salva-compra!
      conn
      (assoc (adapter.compra/model->datomic compra) :compra/cartao cartao-vinculado))))

(s/defn lista-compras-por-cartao! :- [model.compra/CompraComComponentes]
  [db
   numero-cartao :- model.cartao/NumeroCartao]
  (if-let [datomic-obj (db.compra/lista-compras-por-cartao db numero-cartao)]
    (output-datomic datomic-obj)))

(s/defn lista-compras-por-cartao-mes! :- [model.compra/CompraComComponentes]
  [db
   numero-cartao :- model.cartao/NumeroCartao
   mes :- s/Int]
  (if-let [datomic-obj (db.compra/lista-compras-por-cartao-mes db numero-cartao mes)]
    (output-datomic datomic-obj)))

(s/defn lista-gastos-por-categoria!
  [db
   numero-cartao :- model.cartao/NumeroCartao]
  (->> numero-cartao
       (lista-compras-por-cartao! db)
       (group-by :compra/categoria)))

(s/defn carrega-compras-no-banco!
  [filepath-dados :- s/Str
   conn]
  (if-let [compras (adapter.compra/csv->model
                     (diplomat.csv/read-csv! filepath-dados))]
    (mapv (partial salva-compra! conn) compras)))

(s/defn carrega-relatorio-compras-no-csv!
  [filepath :- s/Str
   relatorio :- [model.compra/CompraComComponentes]]
  (diplomat.csv/write-csv!
    filepath
    (adapter.compra/model->csv relatorio)))
