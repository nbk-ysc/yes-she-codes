(ns yes-she-codes.semana4.core
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [yes-she-codes.semana4.db :as y.db]))

(let [conn (y.db/cria-conexao!)]
  (println "Criando o banco she-codes...")
  (y.db/cria-schema conn y.db/schema)
  (println "Banco criado!")
  (println "Tentando inserir os dados das compras do arquivo no banco...")
  (y.db/carrega-compras-no-banco! conn)
  (println "Dados inseridos com sucesso!")
  (pprint (y.db/lista-compras! (d/db conn)))
  (println "\n")
  (println "Lista de compras do cartao 4321432143214321")
  (pprint (y.db/lista-compras-por-cartao! (d/db conn) 4321432143214321))
  (println "\n")
  (println "Lista de compras do cartao 4321432143214321 no mes 3")
  (pprint (y.db/lista-compras-por-cartao! (d/db conn) 4321432143214321 3))
  (println "\n")
  (println "Tentando apagar o banco she-codes")
  (y.db/apaga-conexao!)
  (println "Banco apagado!"))
