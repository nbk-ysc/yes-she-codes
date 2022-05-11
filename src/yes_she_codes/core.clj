(ns yes-she-codes.core
  (:require [yes-she-codes.logic :as ysc.logic]))

(def clientes (ysc.logic/lista-clientes))
(println "Clientes:" clientes)

(def cartoes (ysc.logic/lista-cartoes))
(println "Cartões:" cartoes)

(def compras (ysc.logic/lista-compras))
(println "Compras:" compras)

(println "Total gasto:" (ysc.logic/total-gasto compras))

; (println (total-gasto [{:valor 100.00M},
;                        {:valor 250.00M},
;                        {:valor 400.00M}]))

(println "Filtrando as compras feitas na Alura:" (ysc.logic/compras-por-estabelecimento compras "Alura"))

(println "Filtrando as compras feitas em janeiro:" (ysc.logic/compras-por-mes compras 1))

(println "Filtrando as compras feitas no cartão 1598159815981598:" (ysc.logic/compras-por-cartao compras 1598159815981598))

(println "Filtrando as compras feitas no cartão 1234123412341234 em janeiro:" (ysc.logic/total-gasto-no-mes compras 1234123412341234 1))

(println "Filtrando as compras por valor no intervalo [10, 100]:" (ysc.logic/compras-por-intervalo-de-valor compras 10M 100M))

(println "Calculando o total gasto na categoria Alimentação:" (ysc.logic/total-gasto-por-categoria compras "Alimentação"))

; (println (ysc.logic/total-gasto-por-categoria [{:categoria "Educação" :valor 700.00M},
;                                                {:categoria "Saúde" :valor 1500.00M},
;                                                {:categoria "Educação" :valor 50.00M},
;                                                {:categoria "Alimentação" :valor 100.00M},
;                                                {:categoria "Alimentação" :valor 89.90M}] "Saúde"))