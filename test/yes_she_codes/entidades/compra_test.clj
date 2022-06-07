(ns yes-she-codes.entidades.compra-test
  (:require [clojure.test :refer :all]
            [java-time :as time]
            [schema.core :as s]
            [yes-she-codes.entidades.compra :refer :all]))

(deftest testa-schema-de-compra

  (testing "Deve aceitar compra com campos obrigatórios válidos"
    (let [compra-valida {:data            (time/local-date "2022-05-09")
                         :valor           100M
                         :estabelecimento "Amazon"
                         :categoria       "Casa"
                         :cartao          4321432143214321}]

      (is (= compra-valida (s/validate CompraSchema compra-valida)))))


  (testing "Não deve aceitar data NIL"
    (let [compra-invalida {:data            nil
                           :valor           100M
                           :estabelecimento "Amazon"
                           :categoria       "Casa"
                           :cartao          4321432143214321}]

      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CompraSchema compra-invalida)))))


  (testing "Não deve aceitar valor negativo"
    (let [compra-invalida {:data            (time/local-date "2022-05-09")
                           :valor           -100M
                           :estabelecimento "Amazon"
                           :categoria       "Casa"
                           :cartao          4321432143214321}]

      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CompraSchema compra-invalida)))))


  (testing "Não deve aceitar estabelecimento com somente um caractere"
    (let [compra-invalida {:data            (time/local-date "2022-05-09")
                           :valor           100M
                           :estabelecimento "A"
                           :categoria       "Casa"
                           :cartao          4321432143214321}]

      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CompraSchema compra-invalida)))))


  (testing "Não deve aceitar categoria fora das elencadas"
    (let [compra-invalida {:data            (time/local-date "2022-05-09")
                           :valor           100M
                           :estabelecimento "Amazon"
                           :categoria       "INEXISTENTE"
                           :cartao          4321432143214321}]

      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CompraSchema compra-invalida)))))


  (testing "Não deve aceitar cartão inválido"
    (let [compra-invalida {:data            (time/local-date "2022-05-09")
                           :valor           100M
                           :estabelecimento "Amazon"
                           :categoria       "Casa"
                           :cartao          0}]

      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CompraSchema compra-invalida)))))

  )
