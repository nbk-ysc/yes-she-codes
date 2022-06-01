(ns yes-she-codes.week4-testes
  (:require [yes-she-codes.week4 :refer [apaga-banco cria-conexao
                                         schema-datomic nova-compra salva-compra!
                                         carrega-compras-no-banco!
                                         lista-compras! lista-compras-por-cartao!]]))

(apaga-banco)
(def conn (cria-conexao))
(schema-datomic conn)

(def exemplo-compra (nova-compra "2022-01-01" 129.90M, "Outback",
                                 "Alimentação", 1234123412341234))
(println exemplo-compra)

(salva-compra! conn {:compra/data "data gwqkgd"})

(carrega-compras-no-banco!)

(lista-compras! conn)

;(lista-compras-por-cartao! conn 3939393939393939)