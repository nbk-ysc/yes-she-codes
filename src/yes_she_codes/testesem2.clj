(ns yes-she-codes.testesem2
  (:require [yes-she-codes.comprasatom :refer :all]
            [yes-she-codes.model :as y.model])
  (:use [clojure.pprint])
  )

(def vetor-de-compras (y.model/lista-compras))

(def compra1 (map->Compras {:data            "2022-05-10",
                            :valor           100.00M,
                            :estabelecimento "Alura",
                            :categoria       "Educação",
                            :cartao          3939393939393939}))
(def compra2 (map->Compras {:id              15
                            :data            "2022-03-10",
                            :valor           50.00M,
                            :estabelecimento "Drograria",
                            :categoria       "Saúde",
                            :cartao          1234123412341234}))
(def compra3 (map->Compras {:id              6
                            :data            "2022-05-10",
                            :valor           300.00M,
                            :estabelecimento "Cinema",
                            :categoria       "Lazer",
                            :cartao          3939393939393939}))

;;;;;;;testes;;;;;;;;;;;
;teste 3
;(println "Teste3: ") (pprint (insere-compra vetor-de-compras compra1))
;teste 4
;(reset! repositorio-de-compras  [])
;(pprint (insere! repositorio-de-compras compra3))
;teste 5
;(lista-compras! repositorio-de-compras)
;teste 6
;(pprint (pesquisa-compra-por-id 1 [{:id 1 :data "2022-04-10" :valor 150.00M} {:id 7} compra2]))
;teste 7
;(println "\n Pesquisa")(pprint (pesquisa-compra-por-id! 4  repositorio-de-compras))
;teste 8
;(println "\n Vetor:") (pprint vetor-de-compras)
;(println "\n Pesquisa para excluir id:") (pprint (exlui-compra 2 vetor-de-compras))
;teste 9
(println "\n Exclui compra desde id:") (pprint (exlui-compra! 4 repositorio-de-compras))