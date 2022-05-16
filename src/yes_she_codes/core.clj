(ns yes-she-codes.core
  (:require [yes-she-codes.week1-db :as db]
            [yes-she-codes.week1-logic :as logic])
  (:use clojure.pprint))


(println "Novo cliente manual:" (logic/novo-cliente "Feiticeira Escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"))
(println "Novo cliente a partir do 1º db:" (logic/novo-cliente (map (db/exemplos-clientes 0) [0 1 2])))

(println "Novo cartão manual:" (logic/novo-cartao 1234123412341234	111 "2023-01" 1.000 "000.111.222-33")) ;remover espaços número
(println "Novo cartão a partir do 1º db:" (logic/novo-cartao (map (db/exemplos-cartoes 0) [0 1 2 3 4])))

(println "Nova compra manual:" (logic/nova-compra "2022-01-01" 129.90 "Outback" "Alimentação" 1234123412341234)) ;remover espaços número
(println "Nova compra a partir do 1º db:" (logic/nova-compra (map (db/exemplos-compras 0) [0 1 2 3 4])))

(println "Lista de clientes db:" (logic/lista-clientes db/exemplos-clientes))
(println "Lista de cartões db:" (logic/lista-cartoes db/exemplos-cartoes))

(def compras-processadas (logic/lista-compras db/exemplos-compras))
(println "Lista de compras db:" compras-processadas)

(println "Lista de clientes csv:" (logic/lista-clientes))
(println "Lista de cartões csv:" (logic/lista-cartoes))
(println "Lista de compras csv:" (logic/lista-compras))

(println "Soma de compras:" (logic/total-gasto [{:data "2022-01-01", :valor 129.9, :estabelecimento "Outback", :categoria "Alimentação", :cartao 1234123412341234}
                                                {:data "2022-01-02", :valor 260.00, :estabelecimento "Dentista", :categoria "Saúde", :cartao 1234123412341234}]))

(println  "Lista de compras de um mês:" (logic/compras-daquele-mes 3 compras-processadas))

(println  "Lista de compras de um estabelecimento:" (logic/compras-daquele-estabelecimento "Alura" compras-processadas))

(println  "Total gasto em um mês:" (logic/total-gasto-no-mes 4 compras-processadas))

(println "Compras dentro do intervalo de preço:" (logic/intervalo-valor 100 300 compras-processadas))

(pprint (logic/total-categoria compras-processadas))
