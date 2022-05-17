(ns yes-she-codes.compra
  (:use clojure.pprint)
  (:require [yes-she-codes.db :as y.db]
            [clojure.string :as str]))

(defn str->long [valor]
  (Long/parseLong (clojure.string/replace valor #" " "")))

;DEFINE UMA NOVA COMPRA
(defn nova-compra [data valor estabelecimento categoria cartao]
  {:data            data
   :valor           (bigdec valor)
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          (str->long cartao)})

;(pprint (nova-compra "2026-08-08" 25.6 "Ifood" "Alimentação" 4321432143214325))

;ADICIONA UMA NOVA COMPRA NA LISTA
(defn add-compra [data valor estabelecimento categoria cartao]
  (conj y.db/compras (nova-compra data valor estabelecimento categoria cartao)))

;(pprint (add-compra "2026-08-08" 25.6 "Ifood" "Alimentação" 4321432143214325))

;LISTA AS COMPRAS
(defn lista-compras []
  (pprint y.db/compras))

;(lista-compras)