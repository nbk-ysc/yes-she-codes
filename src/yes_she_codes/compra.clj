(ns yes-she-codes.compra
  (:require [java-time :as t]
            [yes-she-codes.cartao :refer :all]
            [yes-she-codes.cliente :refer :all]))

(defn nova-compra [parametro-compra]
  (if-let [[data valor	estabelecimento	categoria	cartao] parametro-compra]
    (if-let [numero (:numero cartao)]
      {:data (t/local-date "yyyy-MM-dd" data)
       :valor valor
       :estabelecimento estabelecimento
       :categoria categoria
       :cartao numero
       }
      (throw (ex-info "Cartao invalido" {:cartao cartao})))
    (throw (ex-info "Compra invalida" {:compra parametro-compra}))))

(defn lista-compras [parametros-compras]
  (map nova-compra parametros-compras))
