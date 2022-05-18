(ns yes_she_codes.semana2.core
  (:require [yes_she_codes.semana2.db :as y.db]
            [yes_she_codes.semana2.logic :as y.logic]))

(let [todas-as-compras (y.db/lista-compras)
      atomo-compra y.db/repositorio-de-compras]

  (println "Adicionando todas as compras do arquivo csv no atomo e fazendo a listagem das compras:")
  (mapv #(y.logic/insere-compra! atomo-compra %) todas-as-compras)
  (y.logic/lista-compras! atomo-compra)

  (println "Pesquisando nas compras pelo id 3: ") (y.logic/pesquisa-compra-por-id! atomo-compra 3)

  (println "Pesquisando nas compras pelo id 0: ") (y.logic/pesquisa-compra-por-id! atomo-compra 0)

  (println "Tentando exluir o id 2 das compras: ") (y.logic/exclui-compra! atomo-compra 2)
  (println "Tentando exluir o id 0 das compras: ") (y.logic/exclui-compra! atomo-compra 0))


