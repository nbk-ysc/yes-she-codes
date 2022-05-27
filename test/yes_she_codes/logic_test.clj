(ns yes-she-codes.logic-test
  (:require [clojure.test :refer :all]
            [yes-she-codes.schema :refer :all]
            [schema.core :as s])
  (:import (clojure.lang ExceptionInfo)))

(s/set-fn-validation! true)


(deftest validate-cliente-test
  (testing "Teste para Adicionar adicionar um Cliente válido"
    (is (novo-cliente "Viúva Negra"
                      "333.444.555-66"
                      "viuva.casca.grossa@vingadoras.com.br")))

  (testing "Teste para Adicionar adicionar um Cliente não válido"
    (is (thrown? ExceptionInfo
                 (novo-cliente "Viúva Negra"
                               ""
                               "viuva.casca.grossa@vingadoras.com.br"))))

  (testing "Teste para Adicionar adicionar um Cliente com nome nill"
    (is (thrown? ExceptionInfo
                 (novo-cliente nil
                               "333.444.555-66"
                               "viuva.casca.grossa@vingadoras.com.br"))))

  (testing "Teste para Adicionar adicionar um Cliente com cpf inválido"
    (is (thrown? ExceptionInfo
                 (novo-cliente "Viúva Negra"
                               "66"
                               "viuva.casca.grossa@vingadoras.com.br"))))

  (testing "Teste para Adicionar adicionar um Cliente com e-mail invalido"
    (is (thrown? ExceptionInfo
                 "Excessão caso Cliente com e-mail invalido"
                 (novo-cliente "Viúva Negra"
                               "333.444.555-66"
                               "viuva")))))

(deftest validate-cartao-test
  (testing "Teste para Adicionar adicionar um Cartão válido"
    (is (s/validate CartaoSchema {:numero 4321432143214321
                                  :cvv 222
                                  :validade "2024-02"
                                  :limite 2000M
                                  :cliente "333.444.555-66"})))

  (testing "Teste para Adicionar adicionar um Cartão com número negativo"
    (is (thrown? ExceptionInfo
                 (s/validate CartaoSchema {:numero -4321432143214321
                                           :cvv      222
                                           :validade "2024-02"
                                           :limite   2000M
                                           :cliente  "333.444.555-66"}))))

  (testing "Teste para Adicionar adicionar um Cartão com CVV maior que 999"
    (is (thrown? ExceptionInfo
                 (s/validate CartaoSchema {:numero 4321432143214321
                                           :cvv      1000
                                           :validade "2024-02"
                                           :limite   2000M
                                           :cliente  "333.444.555-66"}))))

  (testing "Teste para Adicionar adicionar um Cartão com validade invalida"
    (is (thrown? ExceptionInfo
                 (s/validate CartaoSchema {:numero 4321432143214321
                                           :cvv      222
                                           :validade "aaaaaa"
                                           :limite   2000M
                                           :cliente  "333.444.555-66"}))))

  (testing "Teste para Adicionar adicionar um Cartão com limite negativo"
    (is (thrown? ExceptionInfo
                 (s/validate CartaoSchema {:numero 4321432143214321
                                           :cvv      222
                                           :validade "2024-02"
                                           :limite   -2000M
                                           :cliente  "333.444.555-66"}))))

  (testing "Teste para Adicionar adicionar um Cartão com cliente com cpf invalido"
    (is (thrown? ExceptionInfo
                 (s/validate CartaoSchema {:numero 4321432143214321
                                           :cvv      222
                                           :validade "2024-02"
                                           :limite   2000M
                                           :cliente  "33344455566"}))))
  )

(deftest validate-compra-test
  (testing "Teste para Adicionar adicionar uma Compra válida"
    (is (s/validate CompraSchema {:data-da-compra "2022-05-09"
                                  :valor 100M
                                  :estabelecimento "Amazon"
                                  :categoria "Casa"
                                  :cartao 4321432143214321})))

  (testing "Teste para Adicionar adicionar uma Compra com data inválida"
    (is (thrown? ExceptionInfo
                 (s/validate CompraSchema {:data-da-compra "aa"
                                  :valor 100M
                                  :estabelecimento "Amazon"
                                  :categoria "Casa"
                                  :cartao 4321432143214321}))))

  (testing "Teste para Adicionar adicionar uma Compra com valor negativo"
    (is (thrown? ExceptionInfo
                 (s/validate CompraSchema {:data-da-compra "2022-05-09"
                                           :valor -100M
                                           :estabelecimento "Amazon"
                                           :categoria "Casa"
                                           :cartao 4321432143214321}))))

  (testing "Teste para Adicionar adicionar uma Compra com estabelecimento vazio"
    (is (thrown? ExceptionInfo
                 (s/validate CompraSchema {:data-da-compra "2022-05-09"
                                           :valor 100M
                                           :estabelecimento ""
                                           :categoria "Casa"
                                           :cartao 4321432143214321}))))

  (testing "Teste para Adicionar adicionar uma Compra com categoria invalida"
    (is (thrown? ExceptionInfo
                 (s/validate CompraSchema {:data-da-compra "2022-05-09"
                                           :valor 100M
                                           :estabelecimento "Amazon"
                                           :categoria "Vestuário"
                                           :cartao 4321432143214321}))))

  (testing "Teste para Adicionar adicionar uma Compra com cartao com numero invalido"
    (is (thrown? ExceptionInfo
                 (s/validate CompraSchema {:data-da-compra "2022-05-09"
                                           :valor 100M
                                           :estabelecimento "Amazon"
                                           :categoria "Casa"
                                           :cartao -1}))))

  )
