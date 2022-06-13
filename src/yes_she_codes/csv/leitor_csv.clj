(ns yes-she-codes.csv.leitor-csv
  (:require [java-time :as time]
           [yes-she-codes.logic :as logic]
           [yes-she-codes.dominio.compra :as dominio.compra]
           [yes-she-codes.dominio.cliente :as dominio.cliente]
           [yes-she-codes.dominio.cartao :as dominio.cartao]))

(defn- converte-valores-na-linha [funcoes-de-conversao linha]
  (map #(%1 %2) funcoes-de-conversao linha))


(defn- processa-csv [caminho-arquivo]
  (->> (slurp caminho-arquivo)
       clojure.string/split-lines
       rest
       (map #(clojure.string/split % #","))))

(def csv->cartao [logic/format-numero
                  logic/format-numero
                  logic/format-data-cartao
                  bigdec
                  identity])

(def csv->compra [logic/format-data-compra
                  bigdec
                  identity
                  identity
                  logic/format-numero])


(def cria-cliente-sem-id (partial dominio.cliente/->Cliente nil))
(def cria-cartao-sem-id (partial dominio.cartao/->Cartao nil))
(def cria-compra-sem-id (partial dominio.compra/->Compra nil))


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
