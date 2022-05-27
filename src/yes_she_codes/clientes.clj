(ns yes_she_codes.clientes
  (:require [yes_she_codes.db :as y.db]))

(defn novo-cliente [nome cpf email]
  {:nome  nome,
   :cpf   cpf,
   :email email})

(defn transforma-cliente [[nome cpf email]]
  (novo-cliente nome cpf email))

(defn transforma-clientes [clientes]
  (map transforma-cliente clientes))

(defn lista-clientes []
  (transforma-clientes (y.db/registros-de-clientes)))
