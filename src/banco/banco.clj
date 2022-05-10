(ns banco.banco
  (:require [banco.recupera-dados :as b.recupera-dados])
  (:use [clojure pprint]))

(def lista-de-clientes (b.recupera-dados/recupera-clientes))
(def lista-de-cartoes (b.recupera-dados/recupera-cartoes))
(def lista-de-compras (b.recupera-dados/recupera-compras))
(def lista-de-compras-vetorizada (vec (b.recupera-dados/recupera-compras)))



;função novo-cliente
(defn novo-cliente [cliente]
  (conj lista-de-clientes cliente))

(pprint (novo-cliente {:nome  "Feiticeira2"
                       :cpf   "000.111.222-33"
                       :email "feiticeira.poderosa@vingadoras.com.br"}))




;listar clientes, cartões e compras
(defn lista-clientes []
  (pprint lista-de-clientes))

(defn lista-cartoes []
  (pprint lista-de-cartoes))

(defn lista-compras []
  (println (vec lista-de-compras)))

(lista-compras)



(defn recupera-cliente
  "Retorna um cliente"
  [cpf]
  (pprint (filter #(= (:CPF %) cpf) lista-de-clientes)))

;(recupera-cliente "000.111.222-33")

(defn recupera-cartao
  "Retorna o cartão relacionado a um cliente"
  [cliente]
  (pprint (filter #(= (:CLIENTE %) cliente) lista-de-cartoes)))

;(recupera-cartao "000.111.222-33")

(defn recupera-compra
  "Retorna a compra de determinado cartão"
  [cartao]
  (pprint (filter #(= (:CARTÃO %) cartao) lista-de-compras)))

;(nova-compra "1234 1234 1234 1234")


(defn converte-valores [valor]
  (let [valor-convertido (Double/valueOf valor)]
    valor-convertido)
  )

(defn total-das-compras
  [compras]
  (->> compras
       (map :VALOR)
       (map converte-valores)
       (reduce +)))

(pprint (total-das-compras lista-de-compras-vetorizada))

(defn compras-de-um-cartao
  "Retorna total das compras de determinado cartão"
  [cartao]
  (filter #(= (:CARTÃO %) cartao) lista-de-compras))

(pprint (total-das-compras (compras-de-um-cartao "4321 4321 4321 4321")))

(defn compras-do-mes
  "Retorna lista das compras de determinado mês"
  [mes lista-de-compras]
  (filter #(= (:DATA %) mes) lista-de-compras))

(pprint (compras-do-mes "2022-02-01" lista-de-compras))

(defn compras-por-estabelecimento
  "Retorna total das compras de determinado estabelecimento"
  [estabelecimento]
  (filter #(= (:ESTABELECIMENTO %) estabelecimento) lista-de-compras))

(pprint (compras-por-estabelecimento "Alura"))

(defn compras-de-um-cartao-em-determinado-mes
  "Retorna total das compras de determinado cartão em um mês"
  [cartao mes]
  (let [compras-do-cartao (filter #(= (:CARTÃO %) cartao) (compras-do-mes mes lista-de-compras))]
    (total-das-compras compras-do-cartao)))

(println "\n\n" (compras-de-um-cartao-em-determinado-mes "6655 6655 6655 6655" "2022-03-01"))
