(ns yes-she-codes.csv.leitor-csv
  (:require [java-time :as time]
            [yes-she-codes.util :as y.util]
            [yes-she-codes.dominio.compra :as y.compra]
            [yes-she-codes.dominio.cliente :as y.cliente]
            [yes-she-codes.dominio.cartao :as y.cartao]))

(defn- converte-valores-na-linha [funcoes-de-conversao linha]
  (map #(%1 %2) funcoes-de-conversao linha))


(defn- processa-csv [caminho-arquivo]
  (->> (slurp caminho-arquivo)
       clojure.string/split-lines
       rest
       (map #(clojure.string/split % #","))))

(def csv->cartao [y.util/str->long
                  y.util/str->long
                  time/year-month
                  bigdec
                  identity])

(def csv->compra [time/local-date
                  bigdec
                  identity
                  identity
                  y.util/str->long])


(def cria-cliente-sem-id (partial y.cliente/->Cliente nil))
(def cria-cartao-sem-id (partial y.cartao/->Cartao nil))
(def cria-compra-sem-id (partial y.compra/->Compra nil))


(defn processa-arquivo-de-clientes! []
  (->> (processa-csv "dados/clientes.csv")
       (map #(apply cria-cliente-sem-id %))))


(defn processa-arquivo-de-cartoes! []
  (->> (processa-csv "dados/cartoes.csv")
       (map #(converte-valores-na-linha csv->cartao %))
       (map #(apply cria-cartao-sem-id %))
       vec))

(defn processa-arquivo-de-compras! []
  (->> (processa-csv "dados/compras.csv")
       (map #(converte-valores-na-linha csv->compra %))
       (map #(apply cria-compra-sem-id %))
       vec))

