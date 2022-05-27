(ns yes-she-codes.semana3.compras_test
  (:require [clojure.test :refer :all]
            [schema.core :as s]
            [yes-she-codes.semana3.compras :refer :all])
  (:import (clojure.lang ExceptionInfo)))



(deftest compra-schema-test
  (testing "Testa criar compra com valores especificos"
    (let [compra-test {:data "2022-05-01",
                       :valor 100M,
                       :estabelecimento "Amazon",
                       :categoria "Casa",
                       :cartao 1000}]
      (is (= (s/validate CompraSchema compra-test)
             compra-test))
      ))




  (testing "Testando compra com data invalida"
    (let [compra-test-invalido {:data "2022-05",
                                :valor 100M,
                                :estabelecimento "Amazon",
                                :categoria "Casa",
                                :cartao 1000}]
      (is (thrown? ExceptionInfo
                   (s/validate CompraSchema compra-test-invalido)
                   compra-test-invalido))
      ))




  (testing "Testando compra com valor negativo"
    (let [compra-test-invalido {:data "2022-05-02",
                                :valor -100M,
                                :estabelecimento "Amazon",
                                :categoria "Casa",
                                :cartao 1000}]
      (is (thrown? ExceptionInfo
                   (s/validate CompraSchema compra-test-invalido)
                   compra-test-invalido))
      ))



  (testing "Testando compra com estabelecimento com string vazia"
    (let [compra-test-invalido {:data "2022-05-02",
                                :valor 100M,
                                :estabelecimento "",
                                :categoria "Casa",
                                :cartao 1000}]
      (is (thrown? ExceptionInfo
                   (s/validate CompraSchema compra-test-invalido)
                   compra-test-invalido))
      ))



  (testing "Testando compra com categoria invalida (inexistente)"
    (let [compra-test-invalido {:data "2022-05-02",
                                :valor 100M,
                                :estabelecimento "Amazon",
                                :categoria "Nucleo",
                                :cartao 1000}]
      (is (thrown? ExceptionInfo
                   (s/validate CompraSchema compra-test-invalido)
                   compra-test-invalido))
      ))




  (testing "Testando compra com cartao invalido"
    (let [compra-test-invalido {:data "2022-05-02",
                                :valor 100M,
                                :estabelecimento "Amazon",
                                :categoria "Casa",
                                :cartao 0}]
      (is (thrown? ExceptionInfo
                   (s/validate CompraSchema compra-test-invalido)
                   compra-test-invalido))
      ))


  )




