(ns yes-she-codes.dominio.cartao-test
  (:require [clojure.test :refer :all]
            [schema.core :as s]
            [java-time :as t]
            [yes-she-codes.dominio.cartao :refer :all]))

(deftest testa-schema-de-cartao
  (testing "Deve aceitar cartao com campos obrigatórios válidos"
    (let [cartao-valido {:numero   4321432143214321
                         :cvv      222
                         :validade (t/year-month "2024-02")
                         :limite   2000M
                         :cliente  "333.444.555-00"}]
      (is (= cartao-valido (s/validate CartaoSchema cartao-valido)))))
  (testing "Não deve aceitar número de cartão negativo"
    (let [cartao-invalido {:numero   -1
                           :cvv      222
                           :validade (t/year-month "2024-02")
                           :limite   2000M
                           :cliente  "333.444.555-00"}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CartaoSchema cartao-invalido)))))
  (testing "Não deve aceitar cvv maior que 999"
    (let [cartao-invalido {:numero   4321432143214321
                           :cvv      1000
                           :validade (t/year-month "2024-02")
                           :limite   2000M
                           :cliente  "333.444.555-00"}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CartaoSchema cartao-invalido)))))
  (testing "Não deve aceitar validade nula"
    (let [cartao-invalido {:numero   4321432143214321
                           :cvv      999
                           :validade nil
                           :limite   2000M
                           :cliente  "333.444.555-00"}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CartaoSchema cartao-invalido)))))
  (testing "Não deve aceitar limite negativo"
    (let [cartao-invalido {:numero   4321432143214321
                           :cvv      999
                           :validade (t/year-month "2024-02")
                           :limite   -2000M
                           :cliente  "333.444.555-00"}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CartaoSchema cartao-invalido)))))
  (testing "Não deve aceitar cliente com cpf errado"
    (let [cartao-invalido {:numero   4321432143214321
                           :cvv      999
                           :validade (t/year-month "2024-02")
                           :limite   2000M
                           :cliente  "33344455500"}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CartaoSchema cartao-invalido))))))
