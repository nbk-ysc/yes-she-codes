(ns yes-she-codes.compra
  (:use clojure.pprint)
  (:require [yes-she-codes.db :as y.db]))

(defn nova-compra [data valor estabelecimento categoria cartao]
  (let [compras (y.db/todas-compras)]
    (conj compras {:data data
                   :valor valor
                   :estabelecimento estabelecimento
                   :categoria categoria
                   :cartao cartao})))

(pprint (nova-compra "2026-08-08" 25.6 "Ifood" "Alimentação" 4321432143214325))

(defn lista-compras []
  (pprint (y.db/todas-compras)))

(lista-compras)