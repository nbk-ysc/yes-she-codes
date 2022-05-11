(ns yes-she-codes.logic
  (:require [yes-she-codes.db :as ysc.db]))

(def caminho-para-arquivo-clientes "")
(def caminho-para-arquivo-cartoes "")
(def caminho-para-arquivo-compras "")

(defn lista-clientes
  "Retorna um vetor de clientes."
  []
  (vec (ysc.db/carrega-clientes caminho-para-arquivo-clientes)))

(defn lista-cartoes
  "Retorna um vetor de cartões."
  []
  (vec (ysc.db/carrega-cartoes caminho-para-arquivo-cartoes)))

(defn lista-compras
  "Retorna um vetor de compras."
  []
  (vec (ysc.db/carrega-compras caminho-para-arquivo-compras)))

(defn total-gasto
  "Recebe uma lista de compras e retorna a soma dos valores gastos."
  [compras]
  (reduce + (map :valor compras)))

(defn compra-feita-no-estabelecimento? [compra estabelecimento]
  (= (get compra :estabelecimento) estabelecimento))

(defn compras-por-estabelecimento
  "Recebe uma lista de compras e um estabelecimento e retorna uma lista de compras feitas somente naquele estabelecimento."
  [compras estabelecimento]
  (filter #(compra-feita-no-estabelecimento? % estabelecimento) compras))

(defn compra-feita-no-mes? [compra mes]
  (= (subs (get compra :data) 5 7) mes))

(defn compras-por-mes
  "Recebe uma lista de compras e um mês e retorna uma lista de compras feitas somente naquele mês."
  [compras mes]
  (filter #(compra-feita-no-mes? % mes) compras))

(defn compra-feita-no-cartao? [compra cartao]
  (= (get compra :cartao) cartao))

(defn compras-por-cartao
  "Recebe uma lista de compras e um número de cartão e retorna uma lista de compras feitas somente naquele cartão."
  [compras cartao]
  (filter #(compra-feita-no-cartao? % cartao) compras))

(defn total-gasto-no-mes
  "Calcula a soma dos valores gastos em um determinado cartão em um mês."
  [compras cartao mes]
  (let [compras-do-cartao (compras-por-cartao compras cartao)]
    (compras-por-mes compras-do-cartao mes)))

(defn esta-no-intervalo? [compra min max]
  (let [valor (get compra :valor)]
    (and (<= min valor) (<= valor max)))) ; min <= valor <= max

(defn compras-por-intervalo-de-valor
  "Retorna as compras que estão dentro de um intervalo de valor máximo e mínimo."
  [compras valor-min valor-max]
  (filter #(esta-no-intervalo? % valor-min valor-max) compras))

(defn compra-da-categoria? [compra categoria]
  (= (get compra :categoria) categoria))

(defn compras-por-categoria
  "Recebe uma lista de compras e uma categoria e retorna uma lista de compras feitas somente naquela categoria."
  [compras categoria]
  (filter #(compra-da-categoria? % categoria) compras))

(defn total-gasto-por-categoria
  "Calcula a soma dos valores gastos em uma única categoria."
  [compras categoria]
  (let [compras-da-categoria (compras-por-categoria compras categoria)]
    (reduce + (map :valor compras-da-categoria))))