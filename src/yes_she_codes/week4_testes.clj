(ns yes-she-codes.week4-testes
  (:require [datomic.api :as d]
            [yes-she-codes.week4 :refer [apaga-banco! cria-conexao
                                         schema-datomic nova-compra salva-compra!
                                         carrega-compras-no-banco!
                                         lista-compras! lista-compras-por-cartao!
                                         lista-compras-por-cartao-e-mes!
                                         carrega-cartoes-no-banco!
                                         lista-cartoes!]]))

;; para iniciar: bin/transactor -Ddatomic.printConnectionInfo=true config/dev-transactor-template.properties
;; console: bin/console -p 8080 dev datomic:dev://localhost:4343/


(apaga-banco!)
(def conn (cria-conexao))
(schema-datomic conn)
(println (d/db conn))  ;; :db-after

(def exemplo-compra (nova-compra "2022-01-01" 129.90M, "Outback",
                                 "Alimentação", 3939393939393939))
;;(println exemplo-compra)

(salva-compra! conn exemplo-compra)

(carrega-compras-no-banco! conn)  ;; csv
(carrega-cartoes-no-banco! conn)

(lista-compras! conn)
(lista-cartoes! conn)

(lista-compras-por-cartao! conn 3939393939393939)

(lista-compras-por-cartao-e-mes! conn 3939393939393939 "03")