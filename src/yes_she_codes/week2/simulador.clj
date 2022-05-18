(ns yes-she-codes.week2.simulador
  (:require [yes-she-codes.week2.logic.cliente :as logic.cliente]
            [yes-she-codes.week2.logic.cartao :as logic.cartao]
            [yes-she-codes.week2.logic.compra :as logic.compra]
            [yes-she-codes.week2.adapter.cliente :as adapter.cliente]
            [yes-she-codes.week2.adapter.cartao :as adapter.cartao]
            [yes-she-codes.week2.adapter.compra :as adapter.compra]
            [clojure.pprint :as pp]))

(defn my-pprint
  [& args]
  (mapv #(pp/pprint %) args))

; atoms -> [coll param]

;; SIMULADOR SEMANA 2

;;; TESTANDO CRUD COMPRAS

(def lista-records-compra (adapter.compra/csv->compra "data/compras.csv"))
(def repositorio-de-compras (atom []))
(my-pprint (get lista-records-compra 1))
(logic.compra/insere-compra! repositorio-de-compras (get lista-records-compra 1))
(logic.compra/insere-compra! repositorio-de-compras (get lista-records-compra 2))
(logic.compra/insere-compra! repositorio-de-compras (get lista-records-compra 3))
(logic.compra/pesquisa-compra-por-id! repositorio-de-compras 2)
(logic.compra/exclui-compra! repositorio-de-compras 1)
(logic.compra/insere-compra! repositorio-de-compras (get lista-records-compra 5))
(logic.compra/lista-compras! repositorio-de-compras)




;;; TESTANDO CRUD CLIENTES

(def lista-records-cliente (adapter.cliente/csv->cliente "data/clientes.csv"))
(def repositorio-de-clientes (atom []))
(logic.cliente/insere-cliente! repositorio-de-clientes (get lista-records-cliente 1))
(logic.cliente/insere-cliente! repositorio-de-clientes (get lista-records-cliente 2))
(logic.cliente/insere-cliente! repositorio-de-clientes (get lista-records-cliente 3))
(logic.cliente/pesquisa-cliente-por-id! repositorio-de-clientes 2)
(logic.cliente/exclui-cliente! repositorio-de-clientes 1)
(logic.cliente/insere-cliente! repositorio-de-clientes (get lista-records-cliente 5)) ;; tratar caso de quanto tenta inserir vetor vazio
(logic.cliente/lista-clientes! repositorio-de-clientes)


;;; TESTANDO CRUD CARTÕES

(def lista-records-cartao (adapter.cartao/csv->cartao "data/cartoes.csv"))
(def repositorio-de-cartoes (atom []))
(logic.cartao/insere-cartao! repositorio-de-cartoes (get lista-records-cartao 1))
(logic.cartao/insere-cartao! repositorio-de-cartoes (get lista-records-cartao 2))
(logic.cartao/insere-cartao! repositorio-de-cartoes (get lista-records-cartao 3))
(logic.cartao/pesquisa-cartao-por-id! repositorio-de-cartoes 2)
(logic.cartao/exclui-cartao! repositorio-de-cartoes 1)
(logic.cartao/insere-cartao! repositorio-de-cartoes (get lista-records-cartao 4))
(logic.cartao/lista-cartoes! repositorio-de-cartoes)





