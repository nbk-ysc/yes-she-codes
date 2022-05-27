(ns yes-she-codes.logic_test
  (:use clojure.pprint)
  (:require [clojure.test :refer :all]
            [schema.core :as s]
            [yes-she-codes.semana3.schemas :as y.schemas]))

(s/set-fn-validation! true)

(deftest cliente-schema-test
  (testing "Que o schema do cliente está aceitando um cliente válido"
    (let [cliente {:id 1
                   :nome "Viúva Negra"
                   :cpf "333.444.555-66"
                   :email "viuva.casca.grossa@vingadoras.com.br"}]
      (is (= (s/validate y.schemas/ClienteSchema cliente) cliente))))

  (testing "Que o schema do cliente não aceita cliente com o nome nulo"
    (let [cliente {:nome nil
                   :cpf "333.444.555-66"
                   :email "viuva.casca.grossa@vingadoras.com.br"}]
      (is (thrown? clojure.lang.ExceptionInfo (s/validate y.schemas/ClienteSchema cliente)))))


  (testing "Que o schema do cliente não aceita cliente com cpf em formato inválido"
    (let [cliente {:nome "Viúva Negra"
                   :cpf "33344455566"
                   :email "viuva.casca.grossa@vingadoras.com.br"}]
      (is (thrown? clojure.lang.ExceptionInfo (s/validate y.schemas/ClienteSchema cliente)))))

  (testing "Que o schema do cliente não aceita cliente com email em formato inválido"
    (let [cliente {:nome "Viúva Negra"
                   :cpf "333.444.555-66"
                   :email "viuva.casca.grossa.com.br"}]
      (is (thrown? clojure.lang.ExceptionInfo (s/validate y.schemas/ClienteSchema cliente))))))


(deftest cartao-schema-test
  (testing "Que o schema do cartao está aceitando um cartao válido"
    (let [cartao {:numero 4321432143214321
                  :cvv 222
                  :validade "2024-02"
                  :limite 2000M
                  :cliente "333.444.555-66"}]
      (is (= (s/validate y.schemas/CartaoSchema cartao) cartao))))

  (testing "Que o schema do cartao não aceita cartão com número negativo"
    (let [cartao {:numero -4321432143214321
                  :cvv 222
                  :validade "2024-02"
                  :limite 2000M
                  :cliente "333.444.555-66"}]
    (is (thrown? clojure.lang.ExceptionInfo (s/validate y.schemas/CartaoSchema cartao)))))

  (testing "Que o schema do cartao não aceita cartão com cvv maior que 999"
    (let [cartao {:numero 4321432143214321
                  :cvv 2005
                  :validade "2024-02"
                  :limite 2000M
                  :cliente "333.444.555-66"}]
      (is (thrown? clojure.lang.ExceptionInfo (s/validate y.schemas/CartaoSchema cartao)))))

  (testing "Que o schema do cartao não aceita cartão com validade em formato incorreto"
    (let [cartao {:numero 4321432143214321
                  :cvv 222
                  :validade "02-2024"
                  :limite 2000M
                  :cliente "333.444.555-66"}]
      (is (thrown? clojure.lang.ExceptionInfo (s/validate y.schemas/CartaoSchema cartao)))))

  (testing "Que o schema do cartao não aceita cartão com limite negativo"
    (let [cartao {:numero 4321432143214321
                  :cvv 222
                  :validade "2024-02"
                  :limite -2000M
                  :cliente "333.444.555-66"}]
      (is (thrown? clojure.lang.ExceptionInfo (s/validate y.schemas/CartaoSchema cartao)))))

  (testing "Que o schema do cartao não aceita cartão com cpf em formato inválido"
    (let [cartao {:numero 4321432143214321
                  :cvv 222
                  :validade "2024-02"
                  :limite 2000M
                  :cliente "33344455566"}]
      (is (thrown? clojure.lang.ExceptionInfo (s/validate y.schemas/CartaoSchema cartao))))))



(deftest compra-schema-test
  (testing "Que o schema da compra está aceitando uma compra válida"
    (let [compra {:data "2022-05-09"
                  :valor 100M
                  :estabelecimento "Amazon"
                  :categoria "Casa"
                  :cartao 4321432143214321}]
      (is (= (s/validate y.schemas/CompraSchema compra) compra))))

  (testing "Que o schema da compra não aceita compra com formato inválido"
    (let [compra {:data "05-09-2022"
                  :valor 100M
                  :estabelecimento "Amazon"
                  :categoria "Casa"
                  :cartao 4321432143214321}]
      (is (thrown? clojure.lang.ExceptionInfo (s/validate y.schemas/CompraSchema compra)))))

  (testing "Que o schema da compra não aceita compra com valor negativo"
    (let [compra {:data "2022-05-09"
                  :valor -100M
                  :estabelecimento "Amazon"
                  :categoria "Casa"
                  :cartao 4321432143214321}]
      (is (thrown? clojure.lang.ExceptionInfo (s/validate y.schemas/CompraSchema compra)))))

  (testing "Que o schema da compra não aceita compra com estabelecimento com string vazia"
    (let [compra {:data "2022-05-09"
                  :valor 100M
                  :estabelecimento ""
                  :categoria "Casa"
                  :cartao 4321432143214321}]
      (is (thrown? clojure.lang.ExceptionInfo (s/validate y.schemas/CompraSchema compra)))))

  (testing "Que o schema da compra não aceita compra com categoria inválida"
    (let [compra {:data "2022-05-09"
                  :valor 100M
                  :estabelecimento "Amazon"
                  :categoria "Teste"
                  :cartao 4321432143214321}]
      (is (thrown? clojure.lang.ExceptionInfo (s/validate y.schemas/CompraSchema compra)))))

  (testing "Que o schema da compra não aceita compra com cartão com número inválido"
    (let [compra {:data "2022-05-09"
                  :valor 100M
                  :estabelecimento "Amazon"
                  :categoria "Casa"
                  :cartao -4321432143214321}]
      (is (thrown? clojure.lang.ExceptionInfo (s/validate y.schemas/CompraSchema compra))))))

















