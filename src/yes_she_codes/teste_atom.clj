(ns yes-she-codes.teste-atom
  (:require [yes-she-codes.dados.bd :as y.bd]
            [yes-she-codes.entidades.cliente :as y.cliente]
            [yes-she-codes.entidades.cartao :as y.cartao]
            [yes-she-codes.entidades.compra :as y.compra]
            [java-time :as time])
  (:use [clojure pprint]))

; carrega o csv
(y.bd/carrega-banco-de-dados!)

; clientes
(println "========= LISTAGEM DE CLIENTES ==========")
(pprint (y.bd/lista-clientes!))

(println "\n========= INSERE CLIENTE DE ID 5 ==========")
(y.bd/insere-cliente! (y.cliente/->Cliente nil
                                           "Mulher Maravilha"
                                           "321.321.321-32"
                                           "maisfortedaliga@ligadajustica.dc.br"))

(println "\n========= PESQUISA CLIENTE DE ID 5 ==========")
(pprint (y.bd/pesquisa-cliente-por-id! 5))

(println "\n========= EXCLUIR CLIENTE DE ID 5 ==========")
(y.bd/exclui-cliente! 5)


; cartões
(println "\n========= LISTAGEM DE CARTÕES ==========")
(pprint (y.bd/lista-cartoes!))

(println "\n========= INSERE CARTÃO DE ID 6 ==========")
(y.bd/insere-cartao! (y.cartao/->Cartao nil
                                        1111222233334444
                                        999
                                        (time/year-month "2025-05")
                                        1500M
                                        "000.111.222-33"))

(println "\n========= PESQUISA CARTÃO DE ID 6 ==========")
(pprint (y.bd/pesquisa-cartao-por-id! 6))


(println "\n========= EXCLUIR CLIENTE DE ID 5 ==========")
(y.bd/exclui-cartao! 6)


; compras
(println "\n========= LISTAGEM DE COMPRAS ==========")
(pprint (y.bd/lista-compras!))

(println "\n========= INSERE COMPRA DE ID 20 ==========")
(y.bd/insere-compra! (y.compra/->Compra nil
                                        (time/local-date "2022-05-20")
                                        123.99M
                                        "Carrefour"
                                        "Alimentação"
                                        3939393939393939))

(println "\n========= PESQUISA COMPRA DE ID 20 ==========")
(pprint (y.bd/pesquisa-compra-por-id! 20))

(println "\n========= EXCLUIR COMPRA DE ID 20 ==========")
(y.bd/exclui-compra! 20)

(println "\n========= INSPECIONANDO COMPRAS DA Viúva Negra (cartão 4321432143214321)")
(def compras-da-viuva (y.compra/lista-compras-por-cartao 4321432143214321
                                                         (y.bd/lista-compras!)))

(println "\n========= TOTAL GASTO EM TODAS AS COMPRAS =========")
(pprint (y.compra/total-gasto compras-da-viuva))

(println "\n========= TOTAL GASTO EM FEVEREIRO =========")
(pprint (y.compra/total-gasto-por-mes (time/local-date "2022-02-05") compras-da-viuva))

(println "\n========= COMPRAS AGRUPADAS POR CATEGORIA =========")
(pprint (y.compra/agrupa-gastos-por-categoria compras-da-viuva))


(println "\n========= TENTA INSERIR COMPRA INVÁLIDA ==========")
(try
  (y.bd/insere-compra! (y.compra/->Compra nil
                                          (time/local-date "2022-05-20")
                                          -100M
                                          "Carrefour"
                                          "Alimentação"
                                          3939393939393939))
  (catch Exception e
    (println "Validação deu certo")))