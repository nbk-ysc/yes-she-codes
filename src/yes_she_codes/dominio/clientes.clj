(ns yes-she-codes.dominio.clientes
  (:require [yes-she-codes.db :as y.db])
  (:require [clojure.string :as str]))


; schema para clientes

(defn novo-cliente [nome, cpf, email]
  {:nome  nome
   :cpf   cpf
   :email email})

; recebe o array quebrado; e passa os dados para novo-cliente.
(defn transforma-cliente [[nome, cpf, email]]
  (novo-cliente nome, cpf, email))

; recebe o array, que é o meu banco de dados com todos os clientes. Quebra o array através do map
(defn transforma-clientes [array]
  (map transforma-cliente array))

(defn lista-clientes []
  (transforma-clientes (y.db/busca-registros-de-clientes)))


