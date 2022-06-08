(ns yes-she-codes.cartao-test
  (:require [yes-she-codes.dominio.cartao :refer :all]
            [schema.core :as s]
            [clojure.test :refer :all]
            [java-time :as time]))


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

  (testing "Não aceita límite negativo"
    (let [cartao-invalido {:numero   4321432143214321,
                           :cvv      222M,
                           :validade (time/local-date 2024 02),
                           :limite   -1, :cliente "333.444.555-66"}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CartaoSchema cartao-invalido))))
    )

  (testing "Não aceita cliente com CPF no formato invalido"
    (let [cartao-invalido {:numero   4321432143214321,
                           :cvv      222M,
                           :validade (time/local-date 2024 02),
                           :limite   -1, :cliente "33344455566"}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CartaoSchema cartao-invalido))))
    )
  )

