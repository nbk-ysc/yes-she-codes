(ns yes-she-codes.week3.model.cartao-test
  (:require [clojure.test :as test]
            [schema.core :as s]
            [yes-she-codes.week3.model.cartao :as model.cartao]
            [java-time :as time]))


(test/deftest CartaoSchema-test
  (let [cartao {:id       1
                :numero   11111111111
                :cvv      999
                :validade (time/year-month)
                :limite   500.00
                :cliente  "000.000.000-00"}]

    (test/testing "cartao que atende ao schema"
      (test/is (=
                 cartao
                 (s/validate model.cartao/CartaoSchema cartao))))

    (test/testing "numero invalido"
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.cartao/CartaoSchema (assoc cartao :numero "11111111"))))
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.cartao/CartaoSchema (assoc cartao :numero 11111.11)))))

    (test/testing "cvv invalido"
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.cartao/CartaoSchema (assoc cartao :cvv "999"))))
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.cartao/CartaoSchema (assoc cartao :cvv 1000)))))

    (test/testing "limite invalido"
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.cartao/CartaoSchema (assoc cartao :limite "500.00"))))
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.cartao/CartaoSchema (assoc cartao :limite 500)))))

    (test/testing "cliente invalido"
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.cartao/CartaoSchema (assoc cartao :cliente "000000000-00"))))
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.cartao/CartaoSchema (assoc cartao :cliente 00000000000)))))))
