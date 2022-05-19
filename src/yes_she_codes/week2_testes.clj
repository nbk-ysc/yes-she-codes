(ns yes-she-codes.week2-testes
  (:use clojure.pprint)
  (:require [yes-she-codes.week2 :as week2]))

(println "\n * Exemplos compras * \n")

(reset! week2/repositorio-de-compras [])
(println "Atom:" week2/repositorio-de-compras)
(println "Atom deref inicial:" @week2/repositorio-de-compras)

(def compra1 (week2/map->Compra {:data "2022-01-01",:valor 129.90,
                                 :estabelecimento "Outback", :categoria"Alimentação",
                                 :cliente 1234123412341234}))
(def compra2 (week2/map->Compra {:id 67 :data "2022-04-10",:valor 130.00,
                                 :estabelecimento "Drogaria", :categoria "Saúde",
                                 :cliente 6655665566556655}))


(println "Insere 1ª compra:" (week2/insere-no-vetor [] compra1 ))
(println "Insere outras compras:"(week2/insere-no-vetor [{:id 5} {:id 3} compra2] compra1))

(println "1ª compra atom:" (week2/insere! week2/repositorio-de-compras compra1))
(println "Outras compras atom:" (week2/insere! week2/repositorio-de-compras compra2))

(week2/lista! week2/repositorio-de-compras)

(println "Compra por id:" (week2/pesquisar-por-id 67 [{:id 5} {:id 3} compra2]))
(println "Compra por id no atom:" (week2/pesquisar-por-id! 3 week2/repositorio-de-compras))

(println "Exclui por id:" (week2/exclui-item-do-vetor [{:id 5} {:id 3} compra2] 5))
(println "Exclui por id no atom:" (week2/exclui-item! week2/repositorio-de-compras 1))

(println "Atom deref final:" @week2/repositorio-de-compras)


(println "\n \n * Exemplos clientes * \n")


(reset! week2/repositorio-de-clientes [])


(def cliente1 (week2/map->Cliente {:nome "Feiticeira Escarlate", :cpf "000.111.222-33", :email "feiticeira.poderosa@vingadoras.com.br"}))
(def cliente2 (week2/map->Cliente {:nome "Viúva Negra", :cpf "333.444.555-66", :email "viuva.casca.grossa@vingadoras.com.br"}))

(println "Primeira cliente atom:" (week2/insere! week2/repositorio-de-clientes cliente1))
(println "Outras clientes atom:" (week2/insere! week2/repositorio-de-clientes cliente2))

(week2/lista! week2/repositorio-de-clientes)

(println "Cliente por id:" (week2/pesquisar-por-id 5 [{:id 5} {:id 3} cliente1]))
(println "Compra por id no atom:" (week2/pesquisar-por-id! 2 week2/repositorio-de-clientes))

(println "Exclui por id:" (week2/exclui-item-do-vetor [{:id 5} {:id 3} compra2] 5))
(println "Exclui por id no atom:" (week2/exclui-item! week2/repositorio-de-clientes 1))

(println "Atom deref final:" @week2/repositorio-de-clientes)



(println "\n \n * Exemplos cartões * \n")


(reset! week2/repositorio-de-cartoes [])


(def cartao1 (week2/map->Cartao {:numero 1234123412341234, :cvv 111, :validade "2023-01", :limite 1.000, :cliente "000.111.222-33"}))
(def cartao2 (week2/map->Cartao {:numero 4321432143214321, :cvv 222, :validade "2024-02", :limite 2.000, :cliente "333.444.555-66"}))

(println "1º cartão atom:" (week2/insere! week2/repositorio-de-cartoes cartao1))
(println "Outros cartões atom:" (week2/insere! week2/repositorio-de-cartoes cartao2))

(week2/lista! week2/repositorio-de-cartoes)

(println "Cartão por id:" (week2/pesquisar-por-id 5 [{:id 5} {:id 3} cartao1]))
(println "Cartão por id no atom:" (week2/pesquisar-por-id! 2 week2/repositorio-de-cartoes))

(println "Exclui por id:" (week2/exclui-item-do-vetor [{:id 5} {:id 3} cartao2] 5))
(println "Exclui por id no atom:" (week2/exclui-item! week2/repositorio-de-cartoes 1))

(println "Atom deref final:" @week2/repositorio-de-cartoes)