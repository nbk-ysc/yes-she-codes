(ns yes-she-codes.compra
  (:require [java-time :as t]
            [clojure.string :as str]
            [yes-she-codes.cartao :refer :all]
            [yes-she-codes.cliente :refer :all]
            [yes-she-codes.simulador :refer :all]))

(defn compra? [parametro-compra]
  (if-let [[data valor estabelecimento categoria	numero] parametro-compra]
    (let [cartao  (cartao-lista? numero)]
      (and
        (not (nil? data))
        (not (nil? valor))
        (not (nil? estabelecimento))
        (not (nil? categoria))
        (cartao? [:numero cartao :cvv cartao :validade cartao :limite cartao :cliente cartao])
        ))))

(defn nova-compra [parametro-compra]
  (if (compra? parametro-compra)
    (if-let [[data valor estabelecimento categoria numero] parametro-compra]
      {:data (t/local-date "yyyy-MM-dd" data)
       :valor (Double/parseDouble valor)
       :estabelecimento (str/lower-case estabelecimento)
       :categoria (str/lower-case categoria)
       :cartao (Long/parseLong (str/replace numero " " ""))
        })
    (throw (ex-info "Compra invalida" {:compra parametro-compra}))))

(defn lista-compras [caminho-arquivo]
  (map nova-compra (csv-data caminho-arquivo)))