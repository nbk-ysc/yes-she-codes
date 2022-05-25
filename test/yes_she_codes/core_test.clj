(ns yes-she-codes.core-test
  (:require [clojure.test :refer :all]
            [schema.core :as s]
            [yes-she-codes.week3 :refer :all]))

(deftest ClienteSchema-test
  (testing "se o esquema aceita um cliente válido"
    (let [cliente-valido {:nome "Feiticeira Escarlate",
                          :cpf "000.111.222-33"
                          :email "feiticeira.poderosa@vingadoras.com.br"}]
           (is (= (s/validate ClienteSchema cliente-valido)
                 cliente-valido))))

  (testing "se cliente com :nome nil é validado"
    (let [cliente-invalido {:nome nil,
                            :cpf "000.111.222-33"
                            :email "feiticeira.poderosa@vingadoras.com.br"}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate ClienteSchema cliente-invalido)))))
  )


(deftest CartaoSchema-test
  (testing "se o esquema aceita um cartão válido"
    (let [cartao-valido {:numero   1234123412341234,
                         :cvv      111,
                         :validade "2023-01",
                         :limite   1.000M,
                         :cliente  "000.111.222-33"}]
      (is (= (s/validate CartaoSchema cartao-valido)
             cartao-valido)))))


(deftest CompraSchema-test
  (testing "se o esquema aceita uma compra válida"
    (let [compra-valida {:data "2022-01-01",
                         :valor 129.90M,
                         :estabelecimento "Outback",
                         :categoria "Alimentação",
                         :cartao 1234123412341234}]
      (is (= (s/validate CompraSchema compra-valida)
             compra-valida)))))