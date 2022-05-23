(ns yes-she-codes.week2-testes
  (:use clojure.pprint)
  (:require [yes-she-codes.week2 :refer :all]))

(println "\n * Exemplos compras * \n")

(reset! repositorio-de-compras [])
(println "Atom:" repositorio-de-compras)
(println "Atom deref inicial:" @repositorio-de-compras)

(def compra1 (map->Compra {:data "2022-01-01",:valor 129.90,
                                 :estabelecimento "Outback", :categoria"Alimentação",
                                 :cliente 1234123412341234}))
(def compra2 (map->Compra {:id 67 :data "2022-04-10",:valor 130.00,
                                 :estabelecimento "Drogaria", :categoria "Saúde",
                                 :cliente 6655665566556655}))

(println "Insere 1ª compra:" (insere-no-vetor [] compra1 ))
(println "Insere outras compras:"(insere-no-vetor [{:id 5} {:id 3} compra2] compra1))

(println "1ª compra atom:" (insere! repositorio-de-compras compra1))
(println "Outras compras atom:" (insere! repositorio-de-compras compra2))

(lista! repositorio-de-compras)

(println "Compra por id:" (pesquisar-por-id 67 [{:id 5} {:id 3} compra2]))
(println "Compra por id no atom:" (pesquisar-por-id! 3 repositorio-de-compras))

(println "Exclui por id:" (exclui-item-do-vetor [{:id 5} {:id 3} compra2] 5))
(println "Exclui por id no atom:" (exclui-item! repositorio-de-compras 2))

(println "Atom deref final:" @repositorio-de-compras)


(println "\n \n * Exemplos clientes * \n")


(reset! repositorio-de-clientes [])


(def cliente1 (map->Cliente {:nome "Feiticeira Escarlate", :cpf "000.111.222-33", :email "feiticeira.poderosa@vingadoras.com.br"}))
(def cliente2 (map->Cliente {:nome "Viúva Negra", :cpf "333.444.555-66", :email "viuva.casca.grossa@vingadoras.com.br"}))

(println "Primeira cliente atom:" (insere! repositorio-de-clientes cliente1))
(println "Outras clientes atom:" (insere! repositorio-de-clientes cliente2))

(lista! repositorio-de-clientes)

(println "Cliente por id:" (pesquisar-por-id 5 [{:id 5} {:id 3} cliente1]))
(println "Compra por id no atom:" (pesquisar-por-id! 2 repositorio-de-clientes))

(println "Exclui por id:" (exclui-item-do-vetor [{:id 5} {:id 3} compra2] 5))
(println "Exclui por id no atom:" (exclui-item! repositorio-de-clientes 1))

(println "Atom deref final:" @repositorio-de-clientes)



(println "\n \n * Exemplos cartões * \n")


(reset! repositorio-de-cartoes [])


(def cartao1 (map->Cartao {:numero 1234123412341234, :cvv 111, :validade "2023-01", :limite 1.000, :cliente "000.111.222-33"}))
(def cartao2 (map->Cartao {:numero 4321432143214321, :cvv 222, :validade "2024-02", :limite 2.000, :cliente "333.444.555-66"}))

(println "1º cartão atom:" (insere! repositorio-de-cartoes cartao1))
(println "Outros cartões atom:" (insere! repositorio-de-cartoes cartao2))

(lista! repositorio-de-cartoes)

(println "Cartão por id:" (pesquisar-por-id 5 [{:id 5} {:id 3} cartao1]))
(println "Cartão por id no atom:" (pesquisar-por-id! 2 repositorio-de-cartoes))

(println "Exclui por id:" (exclui-item-do-vetor [{:id 5} {:id 3} cartao2] 5))
(println "Exclui por id no atom:" (exclui-item! repositorio-de-cartoes 1))

(println "Atom deref final:" @repositorio-de-cartoes)