(ns yes-she-codes.logic-test
  (:require [clojure.test :refer :all]
            [yes-she-codes.schema :refer :all]
            [schema.core :as s]))

(s/set-fn-validation! true)


(deftest validate-cliente-test
  (testing "Teste para Adicionar adicionar um Cliente válido"
    (is (s/validate
          ClienteSchema {:nome "Viúva Negra"
                                   :cpf  "333.444.555-66"
                                   :email "viuva.casca.grossa@vingadoras.com.br"})))

  (testing "Teste para Adicionar adicionar um Cliente não válido"
    (is (thrown? clojure.lang.ExceptionInfo
                 "Excessão caso cliente não seja válido"
                 (s/validate ClienteSchema {:nome "Viúva Negra"
                                            :cpf  "33344455566"
                                            :email "v"}))))

  (testing "Teste para Adicionar adicionar um Cliente com nome nill"
    (is (thrown? clojure.lang.ExceptionInfo
                 "Excessão caso Cliente com nome nill"
                 (s/validate ClienteSchema {:nome nil
                                   :cpf  "333.444.555-66"
                                   :email "viuva.casca.grossa@vingadoras.com.br"})))))

(deftest validate-cartao-test
  (testing "Teste para Adicionar adicionar um Cartão válido"
    (is (s/validate CartaoSchema {:numero 4321432143214321
                                  :cvv 222
                                  :validade "2024-02"
                                  :limite 2000M
                                  :cliente "333.444.555-66"}))))

(deftest validate-compra-test
  (testing "Teste para Adicionar adicionar uma Compra válida"
    (is (s/validate CompraSchema {:data-da-compra "2022-05-09"
                                  :valor 100M
                                  :estabelecimento "Amazon"
                                  :categoria "Casa"
                                  :cartao 4321432143214321}))))
