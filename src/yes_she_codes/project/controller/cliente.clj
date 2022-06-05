(ns yes-she-codes.project.controller.cliente
  (:require [schema.core :as s]
            [yes-she-codes.project.diplomat.csv.csv :as diplomat.csv]
            [yes-she-codes.project.logic.common.common :as logic.common]
            [yes-she-codes.project.adapter.cliente :as adapter.cliente]
            [yes-she-codes.project.model.cliente :as model.cliente]
            [yes-she-codes.project.db.cliente :as db.cliente]))

;;; ATOM

(defn insere-cliente!
  [clientes record]
  (swap! clientes logic.common/insere-record record))

(defn lista-clientes-dominio!
  [clientes]
  (logic.common/lista-entidade @clientes))

(defn pesquisa-cliente-por-id!
  [clientes id]
  (logic.common/pesquisa-record-por-id @clientes id))

(defn exclui-cliente!
  [clientes id]
  (swap! clientes logic.common/exclui-record id))

(s/defn carrega-clientes-no-domínio!
  [filepath-dados :- s/Str
   atom]
  (if-let [clientes (adapter.cliente/csv->model
                     (diplomat.csv/read-csv filepath-dados))]
    (mapv (partial insere-cliente! atom) clientes)))


;;; DATABASE

(s/defn lista-clientes! :- [model.cliente/Cliente]
  [db]
  (if-let [datomic-obj (db.cliente/lista-clientes db)]
    (mapv adapter.cliente/datomic->model datomic-obj)))

(s/defn salva-cliente!
  [conn
   cliente :- model.cliente/Cliente]
  (db.cliente/salva-cliente! conn (adapter.cliente/model->datomic cliente)))

(s/defn cartoes-por-cliente!
  [db
   id-cliente :- s/Uuid]
  (if-let [datomic-obj (db.cliente/cartoes-por-cliente! db id-cliente)]
    {:cliente (db.cliente/cliente-por-id! db id-cliente)
     :cartoes datomic-obj}))

(s/defn carrega-clientes-no-banco!
  [filepath-dados :- s/Str
   conn]
  (if-let [clientes (adapter.cliente/csv->model
                   (diplomat.csv/read-csv filepath-dados))]
    (mapv (partial salva-cliente! conn) clientes)))
