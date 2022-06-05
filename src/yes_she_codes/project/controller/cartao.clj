(ns yes-she-codes.project.controller.cartao
  (:require [schema.core :as s]
            [yes-she-codes.project.diplomat.csv.csv :as diplomat.csv]
            [yes-she-codes.project.logic.common.common :as logic.common]
            [yes-she-codes.project.model.cartao :as model.cartao]
            [yes-she-codes.project.db.cartao :as db.cartao]
            [yes-she-codes.project.db.cliente :as db.cliente]
            [yes-she-codes.project.adapter.cartao :as adapter.cartao]
            [yes-she-codes.project.db.config :refer [snapshot!]]))

;;; ATOM

(defn insere-cartao!
  [cartoes record]
  (swap! cartoes logic.common/insere-record record))

(defn lista-cartoes-dominio!
  [cartoes]
  (logic.common/lista-entidade @cartoes))

(defn pesquisa-cartao-por-id!
  [cartoes id]
  (logic.common/pesquisa-record-por-id @cartoes id))

(defn exclui-cartao!
  [cartoes id]
  (swap! cartoes logic.common/exclui-record id))

(s/defn carrega-cartoes-no-domínio!
  [filepath-dados :- s/Str
   atom]
  (if-let [cartoes (adapter.cartao/csv->model
                     (diplomat.csv/read-csv filepath-dados))]
    (mapv (partial insere-cartao! atom) cartoes)))


;;; DATABASE

(s/defn lista-cartoes! :- [model.cartao/CartaoComComponents]
  [db]
  (if-let [datomic-obj (db.cartao/lista-cartoes db)]
    (mapv adapter.cartao/datomic->model-with-components datomic-obj)))

(s/defn salva-cartao!
  [conn
   cartao :- model.cartao/Cartao]
  (if-let [cliente-vinculado (db.cliente/cliente-por-cpf! (snapshot! conn) (:cartao/cliente cartao))]
    (db.cartao/salva-cartao!
      conn
      (assoc (adapter.cartao/model->datomic cartao) :cartao/cliente cliente-vinculado))))

(s/defn carrega-cartoes-no-banco!
  [filepath-dados :- s/Str
   conn]
  (if-let [cartoes (adapter.cartao/csv->model
                   (diplomat.csv/read-csv filepath-dados))]
    (mapv (partial salva-cartao! conn) cartoes)))
