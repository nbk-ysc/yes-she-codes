(ns yes-she-codes.semana3.cartoes_test
    (:require [clojure.test :refer :all]
              [schema.core :as s]
              [yes-she-codes.semana3.cartoes :refer :all])
    (:import (clojure.lang ExceptionInfo))
    )



  (deftest cartao-schema-test
     (testing "Testa criar cartao com valores especificos"
       (let [cartao-test {:numero 4321432143214321
                          :cvv 222
                          :validade "2024-02"
                          :limite 2.000
                          :cliente "333.444.555-66"}]
         (is (= (s/validate CartaoSchema cartao-test)
                cartao-test))
         ))




     (testing "Cartao com numero negativo"
       (let [cartao-test-invalido {:numero -4321432143214321
                                   :cvv 222
                                   :validade "2024-02"
                                   :limite 2.000
                                   :cliente "333.444.555-66"}]
         (is (thrown? ExceptionInfo
                      (s/validate CartaoSchema cartao-test-invalido)
                      cartao-test-invalido))
         ))




     (testing "Cartao com cvv maior que 999"
       (let [cartao-test-invalido {:numero 4321432143214321
                                   :cvv 1000
                                   :validade "2024-02"
                                   :limite 2.000
                                   :cliente "333.444.555-66"}]
         (is (thrown? ExceptionInfo
                      (s/validate CartaoSchema cartao-test-invalido)
                      cartao-test-invalido))
         ))




     (testing "Cartao com limite negativo"
       (let [cartao-test-invalido {:numero 4321432143214321
                                   :cvv 999
                                   :validade "2024-02"
                                   :limite -2.000
                                   :cliente "333.444.555-66"}]
         (is (thrown? ExceptionInfo
                      (s/validate CartaoSchema cartao-test-invalido)
                      cartao-test-invalido))
         ))




     (testing "Cartao com cpf no formato invalido"
       (let [cartao-test-invalido {:numero 4321432143214321
                                   :cvv 999
                                   :validade "2024-02"
                                   :limite 2.000
                                   :cliente "33344455566"}]
         (is (thrown? ExceptionInfo
                      (s/validate CartaoSchema cartao-test-invalido)
                      cartao-test-invalido))
         ))

     )