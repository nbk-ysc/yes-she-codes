(ns yes-she-codes.estudos.week3.week3
  (:require [clojure.test :as t]
            [yes-she-codes.estudos.week3.model.cliente-test :as model.cliente]
            [yes-she-codes.estudos.week3.model.cartao-test :as model.cartao]
            [yes-she-codes.estudos.week3.model.compra-test :as model.compra]))

(t/deftest eg-tests (t/is (= 1 1)))
(t/test-vars [#'model.cliente/ClienteSchema-test])
(t/test-vars [#'model.compra/CompraSchema-test])
(t/test-vars [#'model.cartao/CartaoSchema-test])

