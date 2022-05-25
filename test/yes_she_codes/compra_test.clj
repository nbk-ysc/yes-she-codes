(ns yes-she-codes.compra-test
  (:require [clojure.test :refer :all]
            [semana3.compra :refer :all]
            [schema.core :as s
             :include-macros true                           ;; cljs only
             ]
            [java-time :as t]))

(deftest compra-test
  (testing "Esquema aceita um compra válido"
    (is (= {:data            (t/local-date "2022-05-09")
            :valor           100M
            :estabelecimento "Amazon"
            :categoria       "Casa"
            :cartao          4321432143214321}
           (s/validate CompraSchema {:data            (t/local-date "2022-05-09")
                                     :valor           100M
                                     :estabelecimento "Amazon"
                                     :categoria       "Casa"
                                     :cartao          4321432143214321}))))

  (testing "Compra com data inválida"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CompraSchema {:data            nil
                                           :valor           100M
                                           :estabelecimento "Amazon"
                                           :categoria       "Casa"
                                           :cartao          4321432143214321}))))

  (testing "Valor negativo"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CompraSchema {:data            (t/local-date "2022-05-09")
                                           :valor           -100M
                                           :estabelecimento "Amazon"
                                           :categoria       "Casa"
                                           :cartao          4321432143214321}))))

  (testing "Estabelecimento com String vazia"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CompraSchema {:data            (t/local-date "2022-05-09")
                                           :valor           100M
                                           :estabelecimento ""
                                           :categoria       "Casa"
                                           :cartao          4321432143214321}))))

  (testing "Categoria inválida(inexistente)"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CompraSchema {:data            (t/local-date "2022-05-09")
                                           :valor           100M
                                           :estabelecimento "Amazon"
                                           :categoria       "Inexistente"
                                           :cartao          4321432143214321}))))

  (testing "Cartão com número inválido"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CompraSchema {:data            (t/local-date "2022-05-09")
                                           :valor           100M
                                           :estabelecimento "Amazon"
                                           :categoria       "Casa"
                                           :cartao          -4321432143214321})))))
