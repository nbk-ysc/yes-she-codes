(ns yes-she-codes.week2.simulador
  (:require [yes-she-codes.week2.logic.logic :as l]
            [yes-she-codes.week2.adapter.adapter :as a]
            [clojure.pprint :as pp]))

(defn my-pprint
  [& args]
  (mapv #(pp/pprint %) args))

; atoms -> [coll param]

;; SIMULADOR SEMANA 2

;;; TESTANDO CRUD COMPRAS

(def lista-records-compra (a/lista-record-compra))
(def repositorio-de-compras (atom []))
(my-pprint (get lista-records-compra 1))
(l/insere-compra! repositorio-de-compras (get lista-records-compra 1))
(l/insere-compra! repositorio-de-compras (get lista-records-compra 2))
(l/insere-compra! repositorio-de-compras (get lista-records-compra 3))
(l/pesquisa-compra-por-id! repositorio-de-compras 2)
(l/exclui-compra! repositorio-de-compras 1)
(l/insere-compra! repositorio-de-compras (get lista-records-compra 5))
(l/lista-compras! repositorio-de-compras)



;;; TESTANDO CRUD CLIENTES

(def lista-records-cliente (a/lista-record-cliente))
(def repositorio-de-clientes (atom []))
(l/insere-cliente! repositorio-de-clientes (get lista-records-cliente 1))
(l/insere-cliente! repositorio-de-clientes (get lista-records-cliente 2))
(l/insere-cliente! repositorio-de-clientes (get lista-records-cliente 3))
(l/pesquisa-cliente-por-id! repositorio-de-clientes 2)
(l/exclui-cliente! repositorio-de-clientes 6)
(l/insere-cliente! repositorio-de-clientes (get lista-records-cliente 5)) ;; tratar caso de quanto tenta inserir vetor vazio
(l/lista-clientes! repositorio-de-clientes)


;;; TESTANDO CRUD CARTÕES

(def lista-records-cartao (a/lista-record-cartao))
(def repositorio-de-cartoes (atom []))
(l/insere-cartao! repositorio-de-cartoes (get lista-records-cartao 1))
(l/insere-cartao! repositorio-de-cartoes (get lista-records-cartao 2))
(l/insere-cartao! repositorio-de-cartoes (get lista-records-cartao 3))
(l/pesquisa-cartao-por-id! repositorio-de-cartoes 2)
(l/exclui-cartao! repositorio-de-cartoes 1)
(l/insere-cartao! repositorio-de-cartoes (get lista-records-cartao 4))
(l/lista-cartoes! repositorio-de-cartoes)





