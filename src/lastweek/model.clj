(ns lastweek.model
  (:require [datomic.api :as d]))

(defn nova-compra [data valor estabelecimento categoria cartao]
  {:compra/data  data
   :compra/valor  valor
   :compra/estabelecimento estabelecimento
   :compra/categoria categoria
   :compra/cartao cartao})

(defn salva-compra! [conn compra]
  @(d/transact conn [compra]))
