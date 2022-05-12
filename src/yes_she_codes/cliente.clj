(ns yes-she-codes.cliente
  (:require [yes-she-codes.arquivo :as ysc.arquivo]))

(def dados_clientes (ysc.arquivo/carrega-csv "clientes.csv"))

(defn novo-cliente
  "criar uma estrutura de cliente"
  [nome cpf email]
    {:Nome nome
    :CPF cpf
    :Email email})

(defn adiciona-cliente
  "Adicionar um novo cliente"
  [item]
  (novo-cliente (get item 0 "") (get item 1 "") (get item 2 "")))

(defn lista-clientes
  "Lista os clientes"
  [dados]
  (map adiciona-cliente dados))

(println (lista-clientes dados_clientes))