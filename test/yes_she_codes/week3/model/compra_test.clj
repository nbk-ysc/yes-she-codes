(ns yes-she-codes.week3.model.compra-test
  (:require [clojure.test :as test]
            [schema.core :as s]
            [yes-she-codes.week3.model.compra :as model.compra]
            [java-time :as time]))


(test/deftest CompraSchema-test
  (let [compra {:id              1
                :data            (time/local-date "2022-01-01")
                :valor           66.99
                :estabelecimento "estabelecimento"
                :categoria       "Alimentação"
                :cartao          100000000000}]

    (test/testing "compra que atende ao schema"
      (test/is (=
                 compra
                 (s/validate model.compra/CompraSchema compra))))

    (test/testing "data invalida"
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.compra/CompraSchema (assoc compra :data (time/local-date "2222-01-01")))))
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.compra/CompraSchema (assoc compra :data "2022-01-01")))))

    (test/testing "valor invalido"
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.compra/CompraSchema (assoc compra :valor "66.99"))))
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.compra/CompraSchema (assoc compra :valor 6699)))))

    (test/testing "estabelecimento invalido"
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.compra/CompraSchema (assoc compra :estabelecimento "e"))))
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.compra/CompraSchema (assoc compra :estabelecimento nil)))))

    (test/testing "categoria invalida"
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.compra/CompraSchema (assoc compra :categoria "Alimentação."))))
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.compra/CompraSchema (assoc compra :categoria "Alimentacao")))))

    (test/testing "cartao invalido"
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.compra/CompraSchema (assoc compra :cartao 999999999999999999999999999999))))
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.compra/CompraSchema (assoc compra :cartao "1000000")))))

    ))
