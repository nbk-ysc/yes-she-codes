(ns yes-she-codes.core
  (:require [yes-she-codes.model :as ysc.model]
            [yes-she-codes.logic :as ysc.logic]))

(def clientes (ysc.logic/lista-clientes))
(println "Clientes:" clientes)

(def cartoes (ysc.logic/lista-cartoes))
(println "Cartões:" cartoes)

(def compras (ysc.logic/lista-compras))
(println "Compras:" compras)

(println "Total gasto:" (ysc.logic/total-gasto compras))

(println "Filtrando as compras feitas na Alura:" (ysc.logic/compras-por-estabelecimento compras "Alura"))

(println "Filtrando as compras feitas em janeiro:" (ysc.logic/compras-por-mes compras "01"))

(println "Filtrando as compras feitas no cartão 1598159815981598:" (ysc.logic/compras-por-cartao compras 1598159815981598))

(println "Filtrando as compras feitas no cartão 1234123412341234 em janeiro:" (ysc.logic/total-gasto-no-mes compras 1234123412341234 "01"))