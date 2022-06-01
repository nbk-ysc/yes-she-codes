(ns yes-she-codes.core
  (:require [yes-she-codes.logic.cliente :as l.cliente]
            [yes-she-codes.logic.cartao :as l.cartao]
            [yes-she-codes.logic.compra :as l.compra]
            [yes-she-codes.models.compra :as m.compra])
  (:use [clojure pprint]))

;;;;; Semana 1
;;;;; Listar clientes, cartões e compras
(println l.cliente/clientes)
(println l.cartao/cartoes)
(println l.compra/compras)

;;;;; Calcular o total da fatura de um mês
(l.compra/total-gasto-no-mes l.compra/compras 1)

;;;;; Buscar compras por estabelecimento
(l.compra/compras-por-estabelecimento l.compra/compras "Alura")

;;;;; Buscar compras por mês
(l.compra/compras-por-mes l.compra/compras 2)

;;;;; Calcular o total gasto em compras de um cartão
(l.compra/total-gasto (l.cartao/compras-de-um-cartao l.compra/compras 1234123412341234))

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;

;;;;; Semana 2
(def repositorio-de-compras (atom []))
;;;;; Inserir compra
(l.compra/insere-compra repositorio-de-compras (m.compra/->Compra nil, "2022-01-01", 129.90, "Outback", "Alimentação", 1234123412341234))

;;;;; Inserir compra no átomo
(l.compra/insere-compra! repositorio-de-compras (m.compra/->Compra nil, "2022-01-01", 129.90, "Outback", "Alimentação", 1234123412341234))

;;;;; Listar compras do átomo
(l.compra/lista-compras! repositorio-de-compras)

;;;;; Pesquisar compra por id
(l.compra/pesquisa-compra-por-id @repositorio-de-compras 1)

;;;;; Pesquisar compra no átomo
(l.compra/pesquisa-compra-por-id! repositorio-de-compras 1)

;;;;; Excluir compra do átomo
(l.compra/exclui-compra! @repositorio-de-compras 1)