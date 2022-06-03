(ns yes-she-codes.semana3.model_test
  (:require [clojure.test :refer :all]
            [schema.core :as s]
            [yes_she_codes.semana3.model :refer :all]
            [java-time :as time])
  (:import (clojure.lang ExceptionInfo)))

(deftest ClienteSchema-test
  (testing "Que o schema aceita cliente válido"
    (is (= {:nome "Viúva Negra" :cpf "333.444.555-66" :email "viuva.casca.grossa@vingadoras.com.br"}
           (s/validate ClienteSchema {:nome "Viúva Negra" :cpf "333.444.555-66" :email "viuva.casca.grossa@vingadoras.com.br"}))))

  (testing "Que o schema não aceita cliente inválido"
    (is (thrown? ExceptionInfo
                 (s/validate ClienteSchema {:nome nil :cpf "333.444.555-66" :email "viuva.casca.grossa@vingadoras.com.br"})))
    (is (thrown? ExceptionInfo
                 (s/validate ClienteSchema {:nome "Viúva Negra" :cpf "33344455566" :email "viuva.casca.grossa@vingadoras.com.br"})))
    (is (thrown? ExceptionInfo
                 (s/validate ClienteSchema {:nome "Viúva Negra" :cpf "333.444.555-66" :email "viuva.casca.grossaingadoras.com.br"})))))

(deftest CartaoSchema-test
  (testing "Que o schema aceita cartao válido"
    (is (= {:numero 4321432143214321
            :cvv 222
            :validade (time/year-month "yyyy-MM" "2024-02")
            :limite 2000M
            :cliente "333.444.555-66"}
           (s/validate CartaoSchema {:numero 4321432143214321
                                     :cvv 222
                                     :validade (time/year-month "yyyy-MM" "2024-02")
                                     :limite 2000M
                                     :cliente "333.444.555-66"}))))

  (testing "Que o schema não aceita cartao inválido"
    (is (thrown? ExceptionInfo
                 (s/validate CartaoSchema {:numero -4321432143214321
                                           :cvv 222
                                           :validade (time/year-month "yyyy-MM" "2024-02")
                                           :limite 2000M
                                           :cliente "333.444.555-66"})))
    (is (thrown? ExceptionInfo
                 (s/validate CartaoSchema {:numero 4321432143214321
                                           :cvv 1000
                                           :validade (time/year-month "yyyy-MM" "2024-02")
                                           :limite 2000M
                                           :cliente "333.444.555-66"})))
    (is (thrown? ExceptionInfo
                 (s/validate CartaoSchema {:numero 4321432143214321
                                           :cvv 222
                                           :validade nil
                                           :limite 2000M
                                           :cliente "333.444.555-66"})))
    (is (thrown? ExceptionInfo
                 (s/validate CartaoSchema {:numero 4321432143214321
                                           :cvv 222
                                           :validade (time/year-month "yyyy-MM" "2024-02")
                                           :limite -2000M
                                           :cliente "333.444.555-66"})))
    (is (thrown? ExceptionInfo
                 (s/validate CartaoSchema {:numero 4321432143214321
                                           :cvv 222
                                           :validade (time/year-month "yyyy-MM" "2024-02")
                                           :limite 2000M
                                           :cliente "333444.555-66"})))))

(deftest CompraSchema-test
  (testing "Que o schema aceita compra válida"
    (is (= {:data            (time/local-date "yyyy-MM-dd" "2022-05-09")
            :valor           100M
            :estabelecimento "Amazon"
            :categoria       "Casa"
            :cartao          4321432143214321}
           (s/validate CompraSchema {:data            (time/local-date "yyyy-MM-dd" "2022-05-09")
                                     :valor           100M
                                     :estabelecimento "Amazon"
                                     :categoria       "Casa"
                                     :cartao          4321432143214321}))))

  (testing "Que o schema não aceita cartao inválido"
    (is (thrown? ExceptionInfo
                 (s/validate CompraSchema {:data            (time/local-date "yyyy-MM-dd" "2022-07-09")
                                           :valor           100M
                                           :estabelecimento "Amazon"
                                           :categoria       "Casa"
                                           :cartao          4321432143214321})))
    (is (thrown? ExceptionInfo
                 (s/validate CompraSchema {:data            (time/local-date "yyyy-MM-dd" "2022-05-09")
                                           :valor           -100M
                                           :estabelecimento "Amazon"
                                           :categoria       "Casa"
                                           :cartao          4321432143214321})))
    (is (thrown? ExceptionInfo
                 (s/validate CompraSchema {:data            (time/local-date "yyyy-MM-dd" "2022-05-09")
                                           :valor           100M
                                           :estabelecimento ""
                                           :categoria       "Casa"
                                           :cartao          4321432143214321})))
    (is (thrown? ExceptionInfo
                 (s/validate CompraSchema {:data            (time/local-date "yyyy-MM-dd" "2022-05-09")
                                           :valor           100M
                                           :estabelecimento "Amazon"
                                           :categoria       "Celular"
                                           :cartao          4321432143214321})))
    (is (thrown? ExceptionInfo
                 (s/validate CompraSchema {:data            (time/local-date "yyyy-MM-dd" "2022-05-09")
                                           :valor           100M
                                           :estabelecimento "Amazon"
                                           :categoria       "Casa"
                                           :cartao          -4321432143214321})))))