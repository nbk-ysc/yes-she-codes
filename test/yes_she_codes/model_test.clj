(ns yes-she-codes.model-test
  (:require [clojure.test :refer :all]
            [yes-she-codes.semana3.model :refer :all]
            [schema.core :as s]
            [java-time :as time]))

(s/set-fn-validation! true)

(deftest cliente-schema-test
  (testing "Que o esquema aceita um cliente válido."
    (is (s/validate ClienteSchema {:nome  "Viúva Negra",
                                   :cpf   "333.444.555-66",
                                   :email "viuva.casca.grossa@vingadoras.com.br"})))
  (testing "Que o esquema não aceita um cliente com nome nulo."
    (is (s/validate ClienteSchema {:nome  nil,
                                   :cpf   "333.444.555-66",
                                   :email "viuva.casca.grossa@vingadoras.com.br"})))
  (testing "Que o esquema não aceita um cliente com CPF no formato inválido."
    (is (s/validate ClienteSchema {:nome  "Viúva Negra",
                                   :cpf   "33.444.555-66",
                                   :email "viuva.casca.grossa@vingadoras.com.br"})))
  (testing "Que o esquema não aceita um cliente com email no formato inválido."
    (is (s/validate ClienteSchema {:nome  "Viúva Negra",
                                   :cpf   "333.444.555-66",
                                   :email "viuva.casca.grossavingadoras.com.br"}))))

(deftest cartao-schema-test
  (testing "Que o esquema aceita um cartão válido."
    (is (s/validate CartaoSchema {:numero   4321432143214321
                                  :cvv      222
                                  :validade (time/local-date 2024 02)
                                  :limite   2000M
                                  :cliente  "333.444.555-66"})))
  (testing "Que o esquema não aceita um cartão com número com menos de 16 dígitos."
    (is (s/validate CartaoSchema {:numero   432143214321432
                                  :cvv      222
                                  :validade (time/local-date 2024 02)
                                  :limite   2000M
                                  :cliente  "333.444.555-66"})))
  (testing "Que o esquema não aceita um cartão com número com mais de 16 dígitos."
    (is (s/validate CartaoSchema {:numero   43214321432143218
                                  :cvv      222
                                  :validade (time/local-date 2024 02)
                                  :limite   2000M
                                  :cliente  "333.444.555-66"})))
  (testing "Que o esquema não aceita um cartão com CVV com menos de 3 dígitos."
    (is (s/validate CartaoSchema {:numero   4321432143214321
                                  :cvv      22
                                  :validade (time/local-date 2024 02)
                                  :limite   2000M
                                  :cliente  "333.444.555-66"})))
  (testing "Que o esquema não aceita um cartão com CVV com mais de 3 dígitos."
    (is (s/validate CartaoSchema {:numero   4321432143214321
                                  :cvv      2222
                                  :validade (time/local-date 2024 02)
                                  :limite   2000M
                                  :cliente  "333.444.555-66"})))
  (testing "Que o esquema não aceita um cartão com validade nula."
    (is (s/validate CartaoSchema {:numero   4321432143214321
                                  :cvv      222
                                  :validade nil
                                  :limite   2000M
                                  :cliente  "333.444.555-66"})))
  (testing "Que o esquema não aceita um cartão com limite negativo."
    (is (s/validate CartaoSchema {:numero   4321432143214321
                                  :cvv      222
                                  :validade (time/local-date 2024 02)
                                  :limite   -2000M
                                  :cliente  "333.444.555-66"})))
  (testing "Que o esquema não aceita um cartão com cliente com CPF no formato inválido."
    (is (s/validate CartaoSchema {:numero   4321432143214321
                                  :cvv      222
                                  :validade (time/local-date 2024 02)
                                  :limite   2000M
                                  :cliente  "333.444.555-6"}))))

(deftest compra-schema-test
  (testing "Que o esquema aceita uma compra válida."
    (is (s/validate CompraSchema {:data            (time/local-date 2022 05 9)
                                  :valor           100M
                                  :estabelecimento "Amazon"
                                  :categoria       "Casa"
                                  :cartao          4321432143214321})))
  (testing "Que o esquema não aceita uma compra com data nula."
    (is (s/validate CompraSchema {:data            nil
                                  :valor           100M
                                  :estabelecimento "Amazon"
                                  :categoria       "Casa"
                                  :cartao          4321432143214321})))
  (testing "Que o esquema não aceita uma compra com data futura."
    (is (s/validate CompraSchema {:data            (time/plus (time/local-date) (time/days 1))
                                  :valor           100M
                                  :estabelecimento "Amazon"
                                  :categoria       "Casa"
                                  :cartao          4321432143214321})))
  (testing "Que o esquema não aceita uma compra com valor negativo."
    (is (s/validate CompraSchema {:data            (time/local-date 2022 05 9)
                                  :valor           -100M
                                  :estabelecimento "Amazon"
                                  :categoria       "Casa"
                                  :cartao          4321432143214321})))
  (testing "Que o esquema não aceita uma compra com estabelecimento inválido."
    (is (s/validate CompraSchema {:data            (time/local-date 2022 05 9)
                                  :valor           100M
                                  :estabelecimento ""
                                  :categoria       "Casa"
                                  :cartao          4321432143214321})))
  (testing "Que o esquema não aceita uma compra com categoria inválida."
    (is (s/validate CompraSchema {:data            (time/local-date 2022 05 9)
                                  :valor           100M
                                  :estabelecimento "Amazon"
                                  :categoria       "Jogos"
                                  :cartao          4321432143214321})))
  (testing "Que o esquema não aceita uma compra com número do cartão inválido."
    (is (s/validate CompraSchema {:data            (time/local-date 2022 05 9)
                                  :valor           100M
                                  :estabelecimento "Amazon"
                                  :categoria       "Casa"
                                  :cartao          432143214321431}))))