(ns yes-she-codes.study.week1.week1
  (:require [clojure.test :as t]
            [yes-she-codes.study.week1.model.model-test :as m]
            [yes-she-codes.study.week1.adapter.adapter-test :as a]
            [yes-she-codes.study.week1.logic.logic-test :as l]
            [yes-she-codes.study.week1.logic.util-test :as u]))

; runs all tests in all namespaces
; prints results.
(t/deftest eg-tests (t/is (= 1 1)))
(t/test-vars [#'m/novo-cliente-test])
(t/test-vars [#'m/novo-cartao-test])
(t/test-vars [#'m/nova-compra-test])
(t/test-vars [#'a/criar-cliente-test])
(t/test-vars [#'a/criar-cartao-test])
(t/test-vars [#'a/criar-compra-test])
(t/test-vars [#'a/dado-bruto->model-test])
(t/test-vars [#'l/total-gasto-test])
(t/test-vars [#'l/lista-de-compras-do-mes-test])
(t/test-vars [#'l/lista-de-compras-do-estabelecimento-test])
(t/test-vars [#'l/total-gasto-no-mes-test])
(t/test-vars [#'l/lista-de-compras-por-intervalo-de-valores-test])
(t/test-vars [#'l/gasto-por-categoria-test])
(t/test-vars [#'u/string-sem-espacos-test])
