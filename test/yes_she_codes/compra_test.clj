(ns yes-she-codes.compra-test
  (:require [yes-she-codes.dominio.compra-schema :refer :all]
            [schema.core :as s]
            [clojure.test :refer :all]
            [java-time :as time]))


(deftest compraSchema-test

  (testing "Validação simples da compra"
    (let [compra-valida {:data            (time/local-date 2022 06 9)
                         :valor           100M
                         :estabelecimento "Amazon"
                         :categoria       "Casa"
                         :cartao          1234123412341234}]
      (is (= compra-valida (s/validate CompraSchema compra-valida))))
    )

  (testing "Não aceita compra com data inválida"
    (let [compra-invalida {:data            nil
                           :valor           100M
                           :estabelecimento "Amazon"
                           :categoria       "Casa"
                           :cartao          1234123412341234}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CompraSchema compra-invalida))))
    )

  (testing "Não aceita valor negativo"
    (let [compra-invalida {:data            (time/local-date 2022 06 9)
                           :valor           -1
                           :estabelecimento "Amazon"
                           :categoria       "Casa"
                           :cartao          1234123412341234}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CompraSchema compra-invalida))))
    )

  (testing "Não aceita estabelecimento com string vazia"
    (let [compra-invalida {:data            (time/local-date 2022 06 9)
                           :valor           100M
                           :estabelecimento " "
                           :categoria       "Casa"
                           :cartao          1234123412341234}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CompraSchema compra-invalida))))
    )

  (testing "Não aceita categoria inválida"
    (let [compra-invalida {:data            (time/local-date 2022 06 9)
                           :valor           100M
                           :estabelecimento "Amazon"
                           :categoria       "Digital"
                           :cartao          1234123412341234}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CompraSchema compra-invalida))))
    )

  (testing "Não aceita cartão com número inválido"
    (let [compra-invalida {:data            (time/local-date 2022 06 9)
                           :valor           100M
                           :estabelecimento "Amazon"
                           :categoria       "Casa"
                           :cartao          12341234123412346}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CompraSchema compra-invalida))))
    )
  )



