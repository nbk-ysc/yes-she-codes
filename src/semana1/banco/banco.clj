(ns semana1.banco.banco
  (:require [semana1.banco.recupera-dados :as b.recupera-dados]
            [semana1.banco.logic :as b.logic]
            [java-time :as t])
  (:use [clojure pprint]))


(def lista-de-clientes b.recupera-dados/lista-clientes)
(def lista-de-cartoes b.recupera-dados/lista-cartoes)
(def lista-de-compras b.recupera-dados/lista-compras)

(def lista-de-compras-vetorizada (vec lista-de-compras))
(pprint lista-de-compras-vetorizada)

;(pprint (b.logic/novo-cliente lista-de-clientes
;                              {:nome  "Feiticeira2"
;                               :cpf   "000.111.222-33"
;                               :email "feiticeira.poderosa@vingadoras.com.br"}))

;(pprint lista-de-compras)

;(b.logic/lista-compras lista-de-compras-vetorizada)

;(b.logic/recupera-cliente lista-de-clientes "000.111.222-33")

;(b.logic/recupera-cartao lista-de-cartoes "000.111.222-33")

;(b.logic/recupera-compra lista-de-compras "1234 1234 1234 1234")


;(println "Valor total das compras: R$" (b.logic/total-das-compras lista-de-compras-vetorizada))
;
;(println "Valor das compras do cartão [4321 4321 4321 4321]: R$"
;         (b.logic/total-das-compras (b.logic/compras-de-um-cartao lista-de-compras "4321 4321 4321 4321")))

;(pprint (b.logic/compras-do-mes lista-de-compras 02))

;(pprint (b.logic/compras-por-estabelecimento lista-de-compras "Alura"))



(def data-exemplos (t/format "dd/MM/yyyy" (t/local-date "dd/MM/yyyy" "01/01/2022")))
(def data-exemplos2 (t/format "dd/MM/yyyy" (t/local-date "dd/MM/yyyy" "01/02/2022")))

(println (t/as (t/local-date "dd/MM/yyyy" data-exemplos2) :month-of-year))

;(println "Fatura do cartão no mês/ano:" (b.logic/total-gasto-no-mes lista-de-compras "6655 6655 6655 6655" 04))

;(println "Fatura do cartão no mês/ano:" (b.logic/total-gasto-no-mes lista-de-compras "6655 6655 6655 6655" 03))

;(pprint (b.logic/categorias-agrupadas lista-de-compras))

;(pprint (b.logic/recupera-compra-por-valor lista-de-compras-vetorizada 300 400))