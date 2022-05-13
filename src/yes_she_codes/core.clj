(ns yes-she-codes.core
  (:require [yes-she-codes.logic :as y.logic]))

(println "Lista de clientes" (y.logic/lista-clientes))
(println "Lista de cartoes" (y.logic/lista-cartoes))
(println "Lista de compras" (y.logic/lista-compras))
(println "Total gasto em compras de todos os cartões" (y.logic/total-gasto (y.logic/lista-compras)))
(println "Total gasto em compras de apenas um cartao" (y.logic/total-gasto-no-cartao 1234123412341234 (y.logic/lista-compras)))
(println "Busca das compras de um mes" (y.logic/compras-no-mes "04" (y.logic/lista-compras)))
(println "Calcular o total da fatura de um mês" (y.logic/total-gasto-no-mes "04" (y.logic/lista-compras)))
(println "Busca de estabelecimento" (y.logic/comprei-no-estabelecimento? "Alura" (y.logic/lista-compras)))
