(ns yes-she-codes.compra
  (:require [java-time :as t]
            [clojure.string :as str]
            [yes-she-codes.cartao :refer :all]
            [yes-she-codes.cliente :refer :all]
            [yes-she-codes.simulador :refer :all]))

(defn compra? [parametro-compra]
  (if-let [[data valor estabelecimento categoria	numero] parametro-compra]
    (let [cartao (first (cartao-lista? numero))]
      (and
        (not (nil? data))
        (not (nil? valor))
        (not (nil? estabelecimento))
        (not (nil? categoria))
        (cartao? [(:numero cartao) (:cvv cartao) (:validade cartao) (:limite cartao) (:cliente cartao)])
        ))))

(defn nova-compra [parametro-compra]
  (if (compra? parametro-compra)
    (if-let [[data valor estabelecimento categoria numero] parametro-compra]
      {:data data
       :valor valor
       :estabelecimento estabelecimento
       :categoria categoria
       :cartao numero
        })
    (throw (ex-info "Compra invalida" {:compra parametro-compra}))))

(defn lista-compras [parametro-compra]
  (map nova-compra parametro-compra))

(defn data->nova-compra [data]
  (if-let [[data valor estabelecimento categoria numero] data]
    (nova-compra [(t/local-date "yyyy-MM-dd" data)
                  (Double/parseDouble valor)
                  (str/lower-case estabelecimento)
                  (str/lower-case categoria)
                  (Long/parseLong (str/replace numero " " ""))
                  ])))

(defn csv->lista-compras [caminho-arquivo]
  (map data->nova-compra (csv-data caminho-arquivo)))