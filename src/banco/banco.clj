(ns banco.banco
  (:require [banco.recupera-dados :as b.recupera-dados]
            [banco.logic :as b.logic])
  (:use [clojure pprint]))


(def lista-de-clientes b.recupera-dados/lista-clientes)
(def lista-de-cartoes b.recupera-dados/lista-cartoes)
(def lista-de-compras b.recupera-dados/lista-compras)

(def lista-de-compras-vetorizada (vec lista-de-compras))
;(println lista-de-compras)

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

;(pprint (b.logic/compras-do-mes lista-de-compras "2022-02-01"))

;(pprint (b.logic/compras-por-estabelecimento lista-de-compras "Alura"))

;(println "Fatura do cartão no mês/ano:" (b.logic/total-gasto-no-mes lista-de-compras "6655 6655 6655 6655" "2022-03-01"))

;(println "Fatura do cartão no mês/ano:" (b.logic/total-gasto-no-mes lista-de-compras "6655 6655 6655 6655" "-03-"))

;(pprint (b.logic/categorias-agrupadas lista-de-compras))

;(pprint (b.logic/recupera-compra-por-valor lista-de-compras-vetorizada 300 400))