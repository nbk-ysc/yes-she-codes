(ns yes-she-codes.core-test
  (:require [clojure.test :refer :all]
            [schema.core :as s]
            [yes-she-codes.week3 :refer :all]))


(deftest ClienteSchema-test
  (testing "se o esquema aceita um cliente válido"
    (let [cliente-valido {:nome "Feiticeira Escarlate",
                          :cpf "000.111.222-33"
                          :email "feiticeira.poderosa@vingadoras.com.br"}]
           (is (= cliente-valido)
               (s/validate ClienteSchema cliente-valido))))

  (testing "se cliente com :nome nil é validado"
    (let [cliente-invalido {:nome nil,
                            :cpf "000.111.222-33"
                            :email "feiticeira.poderosa@vingadoras.com.br"}]

      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate ClienteSchema cliente-invalido)))))

  (testing "se cliente com :cpf fora do padrão é validado"
    (let [cliente-invalido {:nome "Feiticeira Escarlate",
                            :cpf "00011122233"
                            :email "feiticeira.poderosa@vingadoras.com.br"}]

      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate ClienteSchema cliente-invalido)))))

  (testing "se cliente com email fora do padrão é validado"
    (let [cliente-invalido {:nome "Feiticeira Escarlate",
                            :cpf "000.111.222-33"
                            :email "feiticeira.poderosa vingadoras.com.br"}]

      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate ClienteSchema cliente-invalido))))))



(deftest CartaoSchema-test
  (testing "se o esquema aceita um cartão válido"
    (let [cartao-valido {:numero   1234123412341234,
                         :cvv      111,
                         :validade "2023-01",
                         :limite   1.000M,
                         :cliente  "000.111.222-33"}]
      (is (= cartao-valido
             (s/validate CartaoSchema cartao-valido)))))

  (testing "se o esquema aceita um cartão com número negativo"
    (let [cartao-invalido {:numero   -1234123412341234,
                           :cvv      111,
                           :validade "2023-01",
                           :limite   1.000M,
                           :cliente  "000.111.222-33"}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CartaoSchema cartao-invalido)))))

  (testing "se o esquema aceita um cartão com cvv maior que 999"
    (let [cartao-invalido {:numero   1234123412341234,
                           :cvv      1000,
                           :validade "2023-01",
                           :limite   1.000M,
                           :cliente  "000.111.222-33"}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CartaoSchema cartao-invalido)))))

  (testing "se o esquema aceita um cartão com data inválida"
    (let [cartao-invalido {:numero   1234123412341234,
                           :cvv      999,
                           :validade "2023/01",
                           :limite   1.000M,
                           :cliente  "000.111.222-33"}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CartaoSchema cartao-invalido)))))

  (testing "se o esquema aceita um cartão com limite negativo"
    (let [cartao-invalido {:numero   1234123412341234,
                           :cvv      999,
                           :validade "2023-01",
                           :limite   -1.000M,
                           :cliente  "000.111.222-33"}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CartaoSchema cartao-invalido)))))

  (testing "se o esquema aceita um cartão com :cpf inválido"
    (let [cartao-invalido {:numero   1234123412341234,
                           :cvv      999,
                           :validade "2023-01",
                           :limite   1.000M,
                           :cliente  "aaa.111.222-33"}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CartaoSchema cartao-invalido))))))


(deftest CompraSchema-test
  (testing "se o esquema aceita uma compra válida"
    (let [compra-valida {:data "2022-01-01",
                         :valor 129.90M,
                         :estabelecimento "Outback",
                         :categoria "Alimentação",
                         :cartao 1234123412341234}]
      (is (= compra-valida
             (s/validate CompraSchema compra-valida)))))

  (testing "se o esquema aceita uma compra com data inválida"
    (let [compra-invalida {:data "2e22-01-01",
                         :valor 129.90M,
                         :estabelecimento "Outback",
                         :categoria "Alimentação",
                         :cartao 1234123412341234}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CompraSchema compra-invalida)))))

  (testing "se o esquema aceita uma compra com valor negativo"
    (let [compra-invalida {:data "2022-01-01",
                           :valor -129.90M,
                           :estabelecimento "Outback",
                           :categoria "Alimentação",
                           :cartao 1234123412341234}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CompraSchema compra-invalida)))))

  (testing "se o esquema aceita uma compra sem estabelecimento"
    (let [compra-invalida {:data "2022-01-01",
                           :valor 129.90M,
                           :estabelecimento "",
                           :categoria "Alimentação",
                           :cartao 1234123412341234}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CompraSchema compra-invalida)))))

  (testing "se o esquema aceita uma compra com categoria inválida"
    (let [compra-invalida {:data "2022-01-01",
                           :valor 129.90M,
                           :estabelecimento "Outback",
                           :categoria "Alimentacão",
                           :cartao 1234123412341234}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CompraSchema compra-invalida)))))

  (testing "se o esquema aceita uma compra com número inválido"
    (let [compra-invalida {:data "2022-01-01",
                           :valor 129.90M,
                           :estabelecimento "Outback",
                           :categoria "Alimentação",
                           :cartao 124123412346746461234}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CompraSchema compra-invalida))))))
