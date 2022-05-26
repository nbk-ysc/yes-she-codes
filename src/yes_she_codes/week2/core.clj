(ns yes-she-codes.week2.core
  (:require [yes-she-codes.week2.logic.cliente :as logic.cliente]
            [yes-she-codes.week2.logic.cartao :as logic.cartao]
            [yes-she-codes.week2.logic.compra :as logic.compra]
            [yes-she-codes.week2.adapter.cliente :as adapter.cliente]
            [yes-she-codes.week2.adapter.cartao :as adapter.cartao]
            [yes-she-codes.week2.adapter.compra :as adapter.compra]))

;; SEMANA 2

;;; SIMULANDO CRUD COMPRAS

(def repositorio-de-compras (atom []))

(let [lista-records-compra (adapter.compra/csv->compra "data/in/compras.csv")]
  (doseq
    [record lista-records-compra]
    (logic.compra/insere-compra! repositorio-de-compras record)))
(logic.compra/lista-compras! repositorio-de-compras)
(logic.compra/pesquisa-compra-por-id! repositorio-de-compras 2)
(logic.compra/exclui-compra! repositorio-de-compras 5)


;;; SIMULANDO CRUD CLIENTES

(def repositorio-de-clientes (atom []))
(let [lista-records-clientes (adapter.cliente/csv->cliente "data/in/clientes.csv")]
  (doseq
    [record lista-records-clientes]
    (logic.cliente/insere-cliente! repositorio-de-clientes record)))
(logic.cliente/lista-clientes! repositorio-de-clientes)
(logic.cliente/pesquisa-cliente-por-id! repositorio-de-clientes 5)
(logic.cliente/exclui-cliente! repositorio-de-clientes 2)


;;; SIMULANDO CRUD CARTÕES

(def repositorio-de-cartoes (atom []))
(let [lista-records-cartoes (adapter.cartao/csv->cartao "data/in/cartoes.csv")]
  (doseq
    [record lista-records-cartoes]
    (logic.cartao/insere-cartao! repositorio-de-cartoes record)))
(logic.cartao/lista-cartoes! repositorio-de-cartoes)
(logic.cartao/pesquisa-cartao-por-id! repositorio-de-cartoes 3)
(logic.cartao/exclui-cartao! repositorio-de-cartoes 1)

