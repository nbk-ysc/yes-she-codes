(ns yes-she-codes.semana3.model-test
  (:require [clojure.test :refer :all]
            [yes_she_codes.semana3.model :refer :all]
            [schema.core :as s]))

(deftest cliente-schema-test
  (testing "Que o schema de uma cliente eh valido"
    (is (= {:nome  "Viúva Negra"
            :cpf   "333.444.555-66"
            :email "viuva.casca.grossa@vingadoras.com.br"}
           (s/validate ClienteSchema
                       {:nome  "Viúva Negra"
                        :cpf   "333.444.555-66"
                        :email "viuva.casca.grossa@vingadoras.com.br"}))))

  (testing "Que o schema de um cliente nao aceita nome com menos de 2 caracteres"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate ClienteSchema
                             {:nome  "V"
                              :cpf   "333.444.555-66"
                              :email "viuva.casca.grossa@vingadoras.com.br"}))))

  (testing "Que o schema de um cliente nao aceita nome nil"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate ClienteSchema
                             {:nome  nil
                              :cpf   "333.444.555-66"
                              :email "viuva.casca.grossa@vingadoras.com.br"}))))

  (testing "Que o schema de um cliente nao aceita cpf em formato invalido"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate ClienteSchema
                             {:nome  "Viúva Negra"
                              :cpf   "33344455566"
                              :email "viuva.casca.grossa@vingadoras.com.br"}))))

  (testing "Que o schema de um cliente nao aceita email invalido"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate ClienteSchema
                             {:nome  "Viúva Negra"
                              :cpf   "333.444.555-66"
                              :email "viuva.casca.grossa@"})))))

(deftest cartao-schema-test
  (testing "Que o schema de um cartao eh valido"
    (is (= {:numero   4321432143214321
            :cvv      222
            :validade "2023-01"
            :limite   2000M
            :cliente  "333.444.555-66"}
           (s/validate CartaoSchema
                       {:numero   4321432143214321
                        :cvv      222
                        :validade "2023-01"
                        :limite   2000M
                        :cliente  "333.444.555-66"}))))

  (testing "Que o schema de um cartao nao eh valido para numero de cartao negativo"
    (is (thrown? clojure.lang.ExceptionInfo
           (s/validate CartaoSchema
                       {:numero   -4321432143214321
                        :cvv      222
                        :validade "2023-01"
                        :limite   2000M
                        :cliente  "333.444.555-66"}))))

  (testing "Que o schema de um cartao nao eh valido para numero de cartao nulo"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CartaoSchema
                             {:numero   nil
                              :cvv      222
                              :validade "2023-01"
                              :limite   2000M
                              :cliente  "333.444.555-66"}))))

  (testing "Que o schema de um cartao nao eh valido para cvv maior que 999"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CartaoSchema
                             {:numero   4321432143214321
                              :cvv      1000
                              :validade "2023-01"
                              :limite   2000M
                              :cliente  "333.444.555-66"}))))

  (testing "Que o schema de um cartao nao eh valido para cvv negativo"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CartaoSchema
                             {:numero   4321432143214321
                              :cvv      -100
                              :validade "2023-01"
                              :limite   2000M
                              :cliente  "333.444.555-66"}))))

  (testing "Que o schema de um cartao nao eh valido para data em formato invalido"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CartaoSchema
                             {:numero   4321432143214321
                              :cvv      1000
                              :validade "2023-01-10"
                              :limite   2000M
                              :cliente  "333.444.555-66"}))))

  (testing "Que o schema de um cartao nao eh valido para limite negativo"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CartaoSchema
                             {:numero   4321432143214321
                              :cvv      1000
                              :validade "2023-01-10"
                              :limite   -20M
                              :cliente  "333.444.555-66"}))))

  (testing "Que o schema de um cartao nao eh valido para cpf em formato invalido"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CartaoSchema
                             {:numero   4321432143214321
                              :cvv      1000
                              :validade "2023-01-10"
                              :limite   20M
                              :cliente  "333.444.55-66"})))))

(deftest compra-schema-test
  (testing "Que o schema de uma compra eh valido"
    (is (= {:data            #inst "2022-05-09T03:00:00.000-00:00"
            :valor           100M
            :estabelecimento "Amazon"
            :categoria       "Casa"
            :cartao          4321432143214321}
           (s/validate CompraSchema
                       {:data            #inst "2022-05-09T03:00:00.000-00:00"
                        :valor           100M
                        :estabelecimento "Amazon"
                        :categoria       "Casa"
                        :cartao          4321432143214321}))))

  (testing "Que o schema de uma compra nao eh valido uma data futura"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CartaoSchema
                             {:data            #inst "2023-05-09T03:00:00.000-00:00"
                              :valor           100M
                              :estabelecimento "Amazon"
                              :categoria       "Casa"
                              :cartao          4321432143214321}))))

  (testing "Que o schema de uma compra nao eh valido para um valor negativo"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CartaoSchema
                             {:data            #inst "2022-05-09T03:00:00.000-00:00"
                              :valor           -100M
                              :estabelecimento "Amazon"
                              :categoria       "Casa"
                              :cartao          4321432143214321}))))

  (testing "Que o schema de uma compra nao eh valido para um estabelecimento vazio"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CartaoSchema
                             {:data            #inst "2022-05-09T03:00:00.000-00:00"
                              :valor           100M
                              :estabelecimento ""
                              :categoria       "Casa"
                              :cartao          4321432143214321}))))

  (testing "Que o schema de uma compra nao eh valido para uma categoria inexistente"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CartaoSchema
                             {:data            #inst "2022-05-09T03:00:00.000-00:00"
                              :valor           100M
                              :estabelecimento "Amazon"
                              :categoria       "Cozinha"
                              :cartao          4321432143214321}))))

  (testing "Que o schema de uma compra nao eh valido para um cartao nulo"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CartaoSchema
                             {:data            #inst "2022-05-09T03:00:00.000-00:00"
                              :valor           100M
                              :estabelecimento "Amazon"
                              :categoria       "Casa"
                              :cartao          nil})))))