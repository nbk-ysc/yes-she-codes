(ns yes_she_codes.semana2.core
  (:require [yes_she_codes.semana2.db :as y.db]
            [yes_she_codes.semana2.logic :as y.logic]))

(let [todas-as-compras (y.db/lista-compras)
      todos-os-clientes (y.db/lista-clientes)
      todos-os-cartoes (y.db/lista-cartoes)
      atomo-compra y.db/repositorio-de-compras
      atomo-cliente y.db/repositorio-de-clientes
      atomo-cartao y.db/repositorio-de-cartoes]

  (println "Adicionando todas as compras do arquivo csv no atomo e realizando a listagem das compras:")
  (mapv #(y.logic/insere-compra! atomo-compra %) todas-as-compras)
  (y.logic/lista-compras! atomo-compra)

  (println "Pesquisando compras com id 3:") (y.logic/pesquisa-compra-por-id! atomo-compra 3)
  (println "Pesquisando compras com id 0:") (y.logic/pesquisa-compra-por-id! atomo-compra 0)

  (println "Tentando exluir o id 2 das compras:") (y.logic/exclui-compra! atomo-compra 2)
  (println "Tentando exluir o id 0 das compras:") (y.logic/exclui-compra! atomo-compra 0)

  (println "\n")

  (println "Adicionando todos os clientes do arquivo csv no atomo e realizando a listagem dos clientes:")
  (mapv #(y.logic/insere-cliente! atomo-cliente %) todos-os-clientes)
  (y.logic/lista-clientes! atomo-cliente)

  (println "Pesquisando cliente com id 3:") (y.logic/pesquisa-cliente-por-id! atomo-cliente 3)
  (println "Pesquisando cliente com id 0:") (y.logic/pesquisa-cliente-por-id! atomo-cliente 0)

  (println "Tentando exluir o id 2 de clientes:") (y.logic/exclui-cliente! atomo-cliente 2)
  (println "Tentando exluir o id 0 de clientes:") (y.logic/exclui-cliente! atomo-cliente 0)

  (println "\n")

  (println "Adicionando todos os cartoes do arquivo csv no atomo e realizando a listagem dos cartoes:")
  (mapv #(y.logic/insere-cartao! atomo-cartao %) todos-os-cartoes)
  (y.logic/lista-cartoes! atomo-cartao)

  (println "Pesquisando cartao com id 3:") (y.logic/pesquisa-cartao-por-id! atomo-cartao 3)
  (println "Pesquisando cartao com id 0:") (y.logic/pesquisa-cartao-por-id! atomo-cartao 0)

  (println "Tentando exluir o id 2 de cartoes:") (y.logic/exclui-cartao! atomo-cartao 2)
  (println "Tentando exluir o id 0 de cartoes:") (y.logic/exclui-cartao! atomo-cartao 0))