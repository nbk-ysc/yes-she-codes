(ns yes-she-codes.core
  (:require [yes_she_codes.clientes :as y.clientes]
            [yes_she_codes.cartoes :as y.cartoes]
            [yes_she_codes.compras :as y.compras]
            [yes_she_codes.logic :as y.logic])
  (:use clojure.pprint))

(println "\n=================================")
(println "LISTA DE CLIENTES")
(println (y.clientes/lista-clientes))

(println "\n=================================")
(println "LISTA DE CARTÕES")
(println (y.cartoes/lista-cartoes))

(println "\n=================================")
(println "LISTA DE COMPRAS")
(println (y.compras/lista-compras))

(println "\n=================================")
(println "TOTAL GASTO =" (y.logic/total-gasto (y.compras/lista-compras)))

(println "\n=================================")
(println "TOTAL GASTO NO MÊS 01 =" (y.logic/total-gasto-no-mes "01" (y.compras/lista-compras)))
(println "TOTAL GASTO NO MÊS 02 =" (y.logic/total-gasto-no-mes "02" (y.compras/lista-compras)))
(println "TOTAL GASTO NO MÊS 03 =" (y.logic/total-gasto-no-mes "03" (y.compras/lista-compras)))
(println "TOTAL GASTO NO MÊS 04 =" (y.logic/total-gasto-no-mes "04" (y.compras/lista-compras)))

(println "\n=================================")
(println "COMPRAS NO MÊS 01")
(println (y.logic/compras-no-mes "01" (y.compras/lista-compras)))

(println "\n=================================")
(println "COMPRAS NO MÊS 02")
(println (y.logic/compras-no-mes "02" (y.compras/lista-compras)))

(println "\n=================================")
(println "COMPRAS NO MÊS 03")
(println (y.logic/compras-no-mes "03" (y.compras/lista-compras)))

(println "\n=================================")
(println "COMPRAS NO MÊS 04")
(println (y.logic/compras-no-mes "04" (y.compras/lista-compras)))

(println "\n=================================")
(println "COMPRAS NO ESTABELECIMENTO ALURA")
(println (y.logic/compras-no-estabelecimento "Alura" (y.compras/lista-compras)))

(println "\n=================================")
(println "COMPRAS NO ESTABELECIMENTO IFOOD")
(println (y.logic/compras-no-estabelecimento "iFood" (y.compras/lista-compras)))

(println "\n=================================")
(println "COMPRAS ENTRE 250 E 400 REAIS")
(println (y.logic/compras-entre-min-e-max 250 400 (y.compras/lista-compras)))

(println "\n=================================")
(println "COMPRAS ENTRE 100 E 300 REAIS")
(println (y.logic/compras-entre-min-e-max 100 300 (y.compras/lista-compras)))

(println "\n=================================")
(println "TOTAL GASTO POR CATEGORIA")
(println (y.logic/total-gasto-por-categoria (y.compras/lista-compras)))

; ======================================================
; ==================== USANDO ATOM =====================
; ======================================================

(println "\n=================================")
(println "LISTA DE COMPRAS USANDO ATOM")
(y.compras/carrega-compras!)
(pprint (y.compras/lista-compras!))

(println "\n=================================")
(println "COMPRA COM ID 0")
(pprint (y.compras/pesquisa-compra-por-id! 0))

(println "\n=================================")
(println "COMPRA COM ID 18")
(pprint (y.compras/pesquisa-compra-por-id! 18))

(println "\n=================================")
(println "COMPRA COM ID 19")
(pprint (y.compras/pesquisa-compra-por-id! 19))

(println "\n=================================")
(println "EXCLUINDO COMPRA COM ID 0")
(pprint (y.compras/exclui-compra! 0))

(println "\n=================================")
(println "EXCLUINDO COMPRA COM ID 10")
(pprint (y.compras/exclui-compra! 10))

(println "\n=================================")
(println "EXCLUINDO COMPRA COM ID 18")
(pprint (y.compras/exclui-compra! 18))

(println "\n=================================")
(println "EXCLUINDO COMPRA COM ID 19")
(pprint (y.compras/exclui-compra! 19))

(println "\n=================================")
(println "LISTA DE COMPRAS APÓS EXCLUSÕES")
(pprint (y.compras/lista-compras!))
