(ns yes-she-codes.estudos.week2.week2
  (:require [clojure.test :as t]
            [yes-she-codes.estudos.week2.logic.common.common-test :as logic.common]
            [yes-she-codes.estudos.week2.logic.cliente-test :as logic.cliente]
            [yes-she-codes.estudos.week2.logic.cartao-test :as logic.cartao]
            [yes-she-codes.estudos.week2.logic.compra-test :as logic.compra]))

; runs all tests in all namespaces
; prints results.
(t/deftest eg-tests (t/is (= 1 1)))
(t/test-vars [#'logic.common/insere-record-test])
(t/test-vars [#'logic.common/pesquisa-record-por-id])
(t/test-vars [#'logic.common/exclui-record-test])
(t/test-vars [#'logic.cliente/insere-cliente!-test])
(t/test-vars [#'logic.cliente/exclui-cliente!-test])
(t/test-vars [#'logic.compra/insere-compra!-test])
(t/test-vars [#'logic.compra/exclui-compra!-test])
(t/test-vars [#'logic.cartao/insere-cartao!-test])
(t/test-vars [#'logic.cartao/exclui-cartao!-test])

