(ns yes-she-codes.cliente-test
  (:require [yes-she-codes.dominio.cliente :refer :all]
            [yes-she-codes.dominio.cartao :refer :all]
            [yes-she-codes.dominio.compra-schema :refer :all]
            [schema.core :as s]
            [clojure.test :refer :all]
            [java-time :as time]))

(deftest clienteSchema-test

  (testing "Validação simples de cliente"
    (let [cliente-valido {:nome  "Viúva Negra",
                          :cpf   "333.444.555-66",
                          :email "viuva.casca.grossa@vingadoras.com.br"}]
      (is (= cliente-valido (s/validate ClienteSchema cliente-valido)))))

  (testing "Não aceita nome nil"
    (let [cliente-invalido {:nome  nil,
                          :cpf   "333.444.555-66",
                          :email "viuva.casca.grossa@vingadoras.com.br"}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate ClienteSchema cliente-invalido)))
      ))

  (testing "Não aceita formato invalido para o CPF"
    (let [cliente-invalido {:nome  "Viúva Negra",
                            :cpf   "33344455566",
                            :email "viuva.casca.grossa@vingadoras.com.br"}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate ClienteSchema cliente-invalido)))
      ))

  (testing "Não aceita formato invalido para o e-mail"
    (let [cliente-invalido {:nome  "Viúva Negra",
                            :cpf   "33344455566",
                            :email "viuva.casca.grossa#vingadoras.com.br"}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate ClienteSchema cliente-invalido)))
      ))
  )

(deftest cataoSchema-test
  (testing "Validação simples do cartão"
    (let [cartao-valido {:numero   4321432143214321,
                         :cvv      222M,
                         :validade (time/local-date 2024 02),
                         :limite   2000M, :cliente "333.444.555-66"}]
      (is (= cartao-valido (s/validate CartaoSchema cartao-valido))))
    )

  (testing "Não aceita cartão com numero negativo"
    (let [cartao-invalido {:numero   -1,
                         :cvv      222M,
                         :validade (time/local-date 2024 02),
                         :limite   2000M, :cliente "333.444.555-66"}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CartaoSchema cartao-invalido))))
    )

  (testing "Não aceita cartão CVV maior que 999"
    (let [cartao-invalido {:numero   4321432143214321,
                           :cvv      1000M,
                           :validade (time/local-date 2024 02),
                           :limite   2000M, :cliente "333.444.555-66"}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CartaoSchema cartao-invalido))))
    )

  (testing "Não aceita formato invalido para a validade"
    (let [cartao-invalido {:numero   4321432143214321,
                           :cvv      222M,
                           :validade nil,
                           :limite   2000M, :cliente "333.444.555-66"}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CartaoSchema cartao-invalido))))
    )
  )

(deftest compraSchema-test

  (testing "Validação simples da compra"
    (let [compra-valida {:data            (time/local-date 2022 06 9)
                         :valor           100M
                         :estabelecimento "Amazon"
                         :categoria       "Casa"
                         :cartao          1234123412341234}]
      (is (= compra-valida (s/validate CompraSchema compra-valida))))
    )
  )



