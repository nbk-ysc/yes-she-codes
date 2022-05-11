(ns yes-she-codes.core-test
  (:require [clojure.test :as t]
            [yes-she-codes.model.model-test :as m]
            [yes-she-codes.adatper.adapter-test :as a]
            [yes-she-codes.logic.logic-test :as l]
            [yes-she-codes.logic.util-test :as u]))

; runs all tests in all namespaces
; prints results.
(t/deftest eg-tests (t/is (= 1 1)))
(t/test-vars [#'m/novo-cliente-test])
(t/test-vars [#'m/novo-cartao-test])
(t/test-vars [#'m/nova-compra-test])
(t/test-vars [#'a/lista-clientes-test])
(t/test-vars [#'a/lista-cartoes-test])
(t/test-vars [#'a/lista-compras-test])
(t/test-vars [#'l/total-gasto-test])
(t/test-vars [#'l/lista-de-compras-do-mes-test])
(t/test-vars [#'l/lista-de-compras-do-estabelecimento-test])
(t/test-vars [#'l/total-gasto-no-mes-test])
(t/test-vars [#'l/lista-de-compras-por-intervalo-test])
(t/test-vars [#'l/gasto-por-categoria-test])
(t/test-vars [#'u/str->local-date-test])
(t/test-vars [#'u/str->year-month-test])
(t/test-vars [#'u/qual-mes?-test])

