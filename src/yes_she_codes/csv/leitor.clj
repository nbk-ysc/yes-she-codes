(ns yes_she_codes.csv.leitor
    (:require [yes_she_codes.semana4.model :as m]
      [yes-she-codes.semana4.core :as c]))

(defn- converte-valores-na-linha [funcoes-de-conversao linha]
       (map #(%1 %2) funcoes-de-conversao linha))


(defn- processa-csv [caminho-arquivo]
       (->> (slurp caminho-arquivo)
            clojure.string/split-lines ,,,,
            rest ,,,,                                       ;primeira linha
            (map #(clojure.string/split % #",")),,,,))


(def csv->compra [time/local-date
                  bigdec
                  identity
                  identity
                  y.util/str->long])


(defn processa-arquivo-de-compras! []
      (->> (processa-csv "dados/compras.csv")
           (map #(converte-valores-na-linha csv->compra %),,,,,,)
           (map #(apply m/salva-compra! %),,,,,)))

(defn processa-arquivo-de-compras! [conn]
      (->> (processa-csv "dados/compras.csv")
           (map #(converte-valores-na-linha csv->compra %),,,,,,)
           (map #(apply m/nova-compra %),,,,,)
           c/salva-compra! conn ,,,,
           ))

