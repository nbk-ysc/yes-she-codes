(ns yes-she-codes.logic.cliente
  (:require
    [yes-she-codes.db.db :as y.db]))

(defn novo-cliente
  [nome cpf email]
  {:nome nome, :cpf cpf, :email email})

(defn transforma-clientes [registros]
  (map (fn [[nome cpf email]]
         (novo-cliente nome, cpf, email))
       registros))

(def clientes (transforma-clientes (y.db/lista-clientes)))



