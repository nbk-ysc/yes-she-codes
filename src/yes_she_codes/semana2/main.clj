(ns yes-she-codes.semana2.main
  (:require [yes-she-codes.semana2.model :as ysc.model]
            [yes-she-codes.semana2.logic :as ysc.logic]))

(def repositorio-de-clientes (atom []))
(def repositorio-de-cartoes (atom []))
(def repositorio-de-compras (atom []))

(def cliente1 (ysc.model/->Cliente nil "bruna" "111.111.111-11" "bruna@gmail.com"))
(def cliente2 (ysc.model/->Cliente nil "bia" "222.222.222-22" "bia@gmail.com"))
(def cliente3 (ysc.model/->Cliente nil "gabriel" "333.333.333-33" "gabriel@gmail.com"))

(ysc.logic/insere-item! repositorio-de-clientes cliente1)
(ysc.logic/insere-item! repositorio-de-clientes cliente2)
(ysc.logic/insere-item! repositorio-de-clientes cliente3)

(ysc.logic/lista-itens! repositorio-de-clientes)

(println (ysc.logic/pesquisa-item-por-id! repositorio-de-clientes 2))

(def cartao1 (ysc.model/->Cartao nil "1234123412341234" "123" "2025-05" 1000M "111.111.111-11"))
(def cartao2 (ysc.model/->Cartao nil "5678567856785678" "567" "2030-08" 15000M "333.333.333-33"))

(ysc.logic/insere-item! repositorio-de-cartoes cartao1)
(ysc.logic/insere-item! repositorio-de-cartoes cartao2)

(ysc.logic/lista-itens! repositorio-de-cartoes)

(def compra1 (ysc.model/->Compra nil "2022-01-01" 20M "amazon" "eletronicos" "1234123412341234"))
(def compra2 (ysc.model/->Compra 125 "2021-12-31" 80M "alura" "educacao" "1234123412341234"))

(ysc.logic/insere-item! repositorio-de-compras compra1)
(ysc.logic/insere-item! repositorio-de-compras compra2)

(ysc.logic/lista-itens! repositorio-de-compras)

(println (ysc.logic/pesquisa-item-por-id! repositorio-de-compras 1))

(println (ysc.logic/exclui-item! repositorio-de-compras 1))