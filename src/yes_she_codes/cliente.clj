(ns yes-she-codes.cliente
  (:use clojure.pprint)
  (:require [yes-she-codes.db :as y.db]))

;DEFINE UM NOVO CLIENTE
(defn novo-cliente [nome cpf email]
  {:nome nome
   :cpf cpf
   :email email})

;(pprint (novo-cliente "Alura Nubank" "111222333014" "alura@nubank.com.br"))

;ADICIONA UM NOVO CLIENTE NA LISTA
(defn add-cliente [nome cpf email]
  (conj y.db/clientes (novo-cliente nome cpf email)))

;LISTA OS CLIENTES
(defn lista-clientes []
  (pprint y.db/clientes))

;(lista-clientes)

