(ns yes-she-codes.study.week3.model.cartao-test
  (:require [clojure.test :as test]
            [schema.core :as s]
            [yes-she-codes.study.week3.model.cartao :as model.cartao]
            [java-time :as time]))

(test/deftest CartaoSchema-test
  (let [cartao {:numero   4321432143214321
                :cvv      222
                :validade (time/year-month "2024-02")
                :limite   2000.00M
                :cliente  "333.444.555-66"}]

    (test/testing "cartao que atende ao schema"
      (test/is (=
                 cartao
                 (s/validate model.cartao/CartaoSchema cartao))))

    (test/testing "numero invalido"
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.cartao/CartaoSchema (assoc cartao :numero -11111111))))
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

    (test/testing "validade invalida"
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.cartao/CartaoSchema (assoc cartao :validade "2030-03"))))
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.cartao/CartaoSchema (assoc cartao :validade (time/local-date))))))

    (test/testing "limite invalido"
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.cartao/CartaoSchema (assoc cartao :limite  -500.00M))))
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.cartao/CartaoSchema (assoc cartao :limite 500.00)))))

    (test/testing "cliente invalido"
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.cartao/CartaoSchema (assoc cartao :cliente "000000000-00"))))
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.cartao/CartaoSchema (assoc cartao :cliente 00000000000)))))

    (test/testing "campos nulos"
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.cartao/CartaoSchema (assoc cartao :numero nil))))
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.cartao/CartaoSchema (assoc cartao :cvv nil))))
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.cartao/CartaoSchema (assoc cartao :validade nil))))
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.cartao/CartaoSchema (assoc cartao :limite nil))))
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.cartao/CartaoSchema (assoc cartao :cliente nil)))))
    ))
