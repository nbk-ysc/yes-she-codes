(ns yes_she_codes.compras
  (:require [yes_she_codes.db :as y.db]))

(defn novo-compra [data valor estabelecimento categoria cartao]
  {:data            data,
   :valor           valor,
   :estabelecimento estabelecimento,
   :categoria       categoria,
   :cartao          cartao})

(defn transforma-compra [[data valor estabelecimento categoria cartao]]
  (novo-compra data valor estabelecimento categoria cartao))

(defn transforma-compras [compras]
  (map transforma-compra compras))

(defn lista-compras []
  (transforma-compras (y.db/registros-de-compras)))
