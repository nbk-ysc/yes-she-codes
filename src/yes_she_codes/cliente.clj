(ns yes-she-codes.cliente
  (:use clojure.pprint)
  (:require [yes-she-codes.db :as y.db]))

(defn novo-cliente [nome cpf email]
  (let [clientes (y.db/todos-clientes)]
    (conj clientes {:nome nome
                    :cpf cpf
                    :email email})))

(pprint (novo-cliente "Alura Nubank" "111222333014" "alura@nubank.com.br"))

(defn lista-clientes []
  (pprint (y.db/todos-clientes)))

(lista-clientes)