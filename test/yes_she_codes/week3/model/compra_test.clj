(ns yes-she-codes.week3.model.compra-test
  (:require [clojure.test :as test]
            [schema.core :as s]
            [yes-she-codes.week3.model.compra :as model.compra]
            [java-time :as time]))


(test/deftest CompraSchema-test
  (let [compra {:data            (time/local-date "2022-05-09")
                :valor           100M
                :estabelecimento "Amazon"
                :categoria       "Casa"
                :cartao          4321432143214321}]

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
                 (s/validate model.compra/CompraSchema (assoc compra :valor  -66.99M))))
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.compra/CompraSchema (assoc compra :valor 66.99)))))

    (test/testing "estabelecimento invalido"
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.compra/CompraSchema (assoc compra :estabelecimento "e"))))
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.compra/CompraSchema (assoc compra :estabelecimento "")))))

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
                 (s/validate model.compra/CompraSchema (assoc compra :cartao -9999999999999999)))))

    (test/testing "campos nulos"
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.compra/CompraSchema (assoc compra :data nil))))
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.compra/CompraSchema (assoc compra :valor nil))))
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.compra/CompraSchema (assoc compra :estabelecimento nil))))
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.compra/CompraSchema (assoc compra :categoria nil))))
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.compra/CompraSchema (assoc compra :cartao nil)))))))
