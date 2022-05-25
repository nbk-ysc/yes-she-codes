(ns yes-she-codes.cartao-test
  (:require [clojure.test :refer :all]
            [semana3.cartao :refer :all]
            [schema.core :as s
             :include-macros true                           ;; cljs only
             ]
            [java-time :as t]))

(deftest cartao-test
  (testing "Esquema aceita um cliente válido"
    (is (= {:numero   4321432143214321
            :cvv      222
            :validade (t/year-month "2024-02")
            :limite   2000M
            :cliente  "333.444.555-66"}
           (s/validate CartaoSchema {:numero   4321432143214321
                                     :cvv      222
                                     :validade (t/year-month "2024-02")
                                     :limite   2000M
                                     :cliente  "333.444.555-66"}))))

  (testing "Cartão com número negativo"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CartaoSchema {:numero   -4321432143214321
                                           :cvv      222
                                           :validade (t/year-month "2024-02")
                                           :limite   2000M
                                           :cliente  "333.444.555-66"}))))

  (testing "CVV maior do que 999"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CartaoSchema {:numero   4321432143214321
                                           :cvv      1000
                                           :validade (t/year-month "2024-02")
                                           :limite   2000M
                                           :cliente  "333.444.555-66"}))))

  (testing "Validade inválida"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CartaoSchema {:numero   4321432143214321
                                           :cvv      222
                                           :validade nil
                                           :limite   2000M
                                           :cliente  "333.444.555-66"}))))

  (testing "Limite negativo"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CartaoSchema {:numero   4321432143214321
                                           :cvv      222
                                           :validade (t/year-month "2024-02")
                                           :limite   -2000M
                                           :cliente  "333.444.555-66"}))))

  (testing "Cliente com cpf em formato inválido"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CartaoSchema {:numero   4321432143214321
                                           :cvv      222
                                           :validade (t/year-month "2024-02")
                                           :limite   2000M
                                           :cliente  "333444.555-66"})))))
