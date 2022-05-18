(ns yes-she-codes.core
  (:require [yes-she-codes.cliente :as ysc.cliente]
            [yes-she-codes.cartao :as ysc.cartao]
            [yes-she-codes.compra :as ysc.compra]
            [yes-she-codes.funcoes :as ysc.funcoes]))

(def clientes (ysc.cliente/lista-clientes))
(def cartoes (ysc.cartao/lista-cartoes))
(def compras (ysc.compra/lista-compras))

;(println clientes)
;(println cartoes)
;(println compras)

(def teste (ysc.funcoes/agrupar-categoria compras))

(println teste)