(ns yes-she-codes.core
  (:require [yes-she-codes.logic.clientes :as y.logic.clientes]
            [yes-she-codes.logic.cartoes :as y.logic.cartoes]
            [yes-she-codes.logic.compras :as y.logic.compras]
            [yes-she-codes.logic.utils :as y.logic.utils]
            [yes-she-codes.db.datomic-db :as y.datomic-db]
            [datomic.api :as d])
  (:use clojure.pprint))

(println "\n=================================")
(println "LISTA DE CLIENTES")
(println (y.logic.clientes/lista-clientes))

(println "\n=================================")
(println "LISTA DE CARTÕES")
(println (y.logic.cartoes/lista-cartoes))

(println "\n=================================")
(println "LISTA DE COMPRAS")
(println (y.logic.compras/lista-compras))

(println "\n=================================")
(println "TOTAL GASTO =" (y.logic.utils/total-gasto (y.logic.compras/lista-compras)))

(println "\n=================================")
(println "TOTAL GASTO NO MÊS 01 =" (y.logic.utils/total-gasto-no-mes "01" (y.logic.compras/lista-compras)))
(println "TOTAL GASTO NO MÊS 02 =" (y.logic.utils/total-gasto-no-mes "02" (y.logic.compras/lista-compras)))
(println "TOTAL GASTO NO MÊS 03 =" (y.logic.utils/total-gasto-no-mes "03" (y.logic.compras/lista-compras)))
(println "TOTAL GASTO NO MÊS 04 =" (y.logic.utils/total-gasto-no-mes "04" (y.logic.compras/lista-compras)))

(println "\n=================================")
(println "COMPRAS NO MÊS 01")
(println (y.logic.utils/compras-no-mes "01" (y.logic.compras/lista-compras)))

(println "\n=================================")
(println "COMPRAS NO MÊS 02")
(println (y.logic.utils/compras-no-mes "02" (y.logic.compras/lista-compras)))

(println "\n=================================")
(println "COMPRAS NO MÊS 03")
(println (y.logic.utils/compras-no-mes "03" (y.logic.compras/lista-compras)))

(println "\n=================================")
(println "COMPRAS NO MÊS 04")
(println (y.logic.utils/compras-no-mes "04" (y.logic.compras/lista-compras)))

(println "\n=================================")
(println "COMPRAS NO ESTABELECIMENTO ALURA")
(println (y.logic.utils/compras-no-estabelecimento "Alura" (y.logic.compras/lista-compras)))

(println "\n=================================")
(println "COMPRAS NO ESTABELECIMENTO IFOOD")
(println (y.logic.utils/compras-no-estabelecimento "iFood" (y.logic.compras/lista-compras)))

(println "\n=================================")
(println "COMPRAS ENTRE 250 E 400 REAIS")
(println (y.logic.utils/compras-entre-min-e-max 250 400 (y.logic.compras/lista-compras)))

(println "\n=================================")
(println "COMPRAS ENTRE 100 E 300 REAIS")
(println (y.logic.utils/compras-entre-min-e-max 100 300 (y.logic.compras/lista-compras)))

(println "\n=================================")
(println "TOTAL GASTO POR CATEGORIA")
(println (y.logic.utils/total-gasto-por-categoria (y.logic.compras/lista-compras)))

; ======================================================
; ==================== USANDO ATOM =====================
; ======================================================

(println "\n=================================")
(println "LISTA DE COMPRAS USANDO ATOM")
(y.logic.compras/carrega-compras!)
(pprint (y.logic.compras/lista-compras!))

(println "\n=================================")
(println "COMPRA COM ID 0")
(pprint (y.logic.compras/pesquisa-compra-por-id! 0))

(println "\n=================================")
(println "COMPRA COM ID 18")
(pprint (y.logic.compras/pesquisa-compra-por-id! 18))

(println "\n=================================")
(println "COMPRA COM ID 19")
(pprint (y.logic.compras/pesquisa-compra-por-id! 19))

(println "\n=================================")
(println "EXCLUINDO COMPRA COM ID 0")
(pprint (y.logic.compras/exclui-compra! 0))

(println "\n=================================")
(println "EXCLUINDO COMPRA COM ID 10")
(pprint (y.logic.compras/exclui-compra! 10))

(println "\n=================================")
(println "EXCLUINDO COMPRA COM ID 18")
(pprint (y.logic.compras/exclui-compra! 18))

(println "\n=================================")
(println "EXCLUINDO COMPRA COM ID 19")
(pprint (y.logic.compras/exclui-compra! 19))

(println "\n=================================")
(println "LISTA DE COMPRAS APÓS EXCLUSÕES")
(pprint (y.logic.compras/lista-compras!))

; ======================================================
; =================== USANDO DATOMIC ===================
; ======================================================

(y.datomic-db/apaga-banco!)
(def conn (y.datomic-db/cria-conexao))
(y.datomic-db/cria-schema! conn)
(y.datomic-db/carrega-compras-no-banco! conn)

(println "\n=================================")
(println "LISTA DE COMPRAS USANDO DATOMIC")
(y.logic.compras/carrega-compras!)
(pprint (y.datomic-db/lista-compras! (d/db conn)))

(println "\n=================================")
(println "COMPRAS NO CARTÃO" 3939393939393939)
(pprint (y.datomic-db/lista-compras-por-cartao! (d/db conn) 3939393939393939))

(println "\n=================================")
(println "COMPRAS NO CARTÃO 3939393939393939 NO MÊS 04")
(pprint (y.datomic-db/lista-compras-por-cartao! (d/db conn) 3939393939393939 "04"))
