(ns yes-she-codes.semana2.main
  (:require [yes-she-codes.semana2.model :as ysc.model]
            [yes-she-codes.semana2.logic :as ysc.logic]))

(def repositorio-de-compras (atom []))

(def compra1 (ysc.model/->Compra nil "2022-01-01" 20M "amazon" "eletronicos" "1234123412341234"))
(def compra2 (ysc.model/->Compra 125 "2021-12-31" 80M "alura" "educacao" "1234123412341234"))

(ysc.logic/insere-compra! repositorio-de-compras compra1)
(ysc.logic/insere-compra! repositorio-de-compras compra2)

(ysc.logic/lista-compras! repositorio-de-compras)

(println (ysc.logic/pesquisa-compra-por-id! repositorio-de-compras 1))

(println (ysc.logic/exclui-compra! repositorio-de-compras 1))