(ns yes-she-codes.week1-testes
  (:require [yes-she-codes.week1-db :as db]
            [yes-she-codes.week1-logic :refer :all])
  (:use clojure.pprint))


(println "Novo cliente manual:" (novo-cliente "Feiticeira Escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"))
(println "Novo cliente a partir do 1º db:" (novo-cliente (map (db/exemplos-clientes 0) [0 1 2])))

(println "Novo cartão manual:" (novo-cartao 1234123412341234	111 "2023-01" 1.000 "000.111.222-33")) ;remover espaços número
(println "Novo cartão a partir do 1º db:" (novo-cartao (map (db/exemplos-cartoes 0) [0 1 2 3 4])))

(println "Nova compra manual:" (nova-compra "2022-01-01" 129.90 "Outback" "Alimentação" 1234123412341234)) ;remover espaços número
(println "Nova compra a partir do 1º db:" (nova-compra (map (db/exemplos-compras 0) [0 1 2 3 4])))

(println "Lista de clientes db:" (lista-clientes db/exemplos-clientes))
(println "Lista de cartões db:" (lista-cartoes db/exemplos-cartoes))

(def compras-processadas (lista-compras db/exemplos-compras))
(println "Lista de compras db:" compras-processadas)

(println "Lista de clientes csv:" (lista-clientes))
(println "Lista de cartões csv:" (lista-cartoes))
(println "Lista de compras csv:" (lista-compras))

(println "Soma de compras:" (total-gasto [{:data "2022-01-01", :valor 129.9, :estabelecimento "Outback", :categoria "Alimentação", :cartao 1234123412341234}
                                                {:data "2022-01-02", :valor 260.00, :estabelecimento "Dentista", :categoria "Saúde", :cartao 1234123412341234}]))

(println  "Lista de compras de um mês:" (compras-daquele-mes 3 compras-processadas))

(println  "Lista de compras de um estabelecimento:" (compras-daquele-estabelecimento "Alura" compras-processadas))

(println  "Total gasto em um mês:" (total-gasto-no-mes 4 compras-processadas))

(println "Compras dentro do intervalo de preço:" (intervalo-valor 100 300 compras-processadas))

(pprint (total-categoria compras-processadas))
