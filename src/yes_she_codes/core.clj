(ns yes-she-codes.core
  (:use clojure.pprint)
  (:require [yes-she-codes.persistencia.cliente :as cliente]
            [yes-she-codes.persistencia.cartao :as cartao]
            [yes-she-codes.persistencia.compra :as compra]
            [yes-she-codes.persistencia.datomic :as datomic]))


;SEMANA 1
(pprint (cartao/lista-cartoes))

(pprint "-------------------------------------------------------------")
(pprint (compra/lista-compras))
(pprint (compra/total-gasto (compra/lista-compras)))
(pprint (compra/total-compras-por-cartao (compra/lista-compras)))
(pprint (compra/total-gasto-mes "01" 1234123412341234 (compra/lista-compras)))
(pprint (compra/buscar-por-mes "01" (compra/lista-compras)))
(pprint (compra/agrupar-por-categoria (compra/lista-compras)))
(pprint (compra/filtrar-intervalo-compras  129.9 400.0 (compra/lista-compras)))

(pprint "-----------------------------------------------------------")
(pprint (cliente/agregate-clients))

(pprint "-----------------------------------------------------------")

;SEMANA 2 - ATOM
(pprint (compra/limpa-atom))
(pprint (compra/lista-compra!))
(pprint (compra/carrega-compra))

(pprint  "LISTA COMPRA")
(pprint (compra/lista-compra!))

(pprint "FILTRA POR ID")
(pprint (compra/pesquisa-compra-por-id! 3 (compra/lista-compra!)))

(pprint "UPDATE POR ID")
(pprint (compra/update-compra 6 :valor 200.0))

(pprint "EXCLUI POR ID")
(pprint (compra/exclui-compra! 3))

;SEMANA 4 - DATOMIC
(datomic/carrega-banco-de-dados!)
