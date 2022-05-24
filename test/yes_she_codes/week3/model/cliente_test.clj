(ns yes-she-codes.week3.model.cliente-test
  (:require [clojure.test :as test]
            [schema.core :as s]
            [yes-she-codes.week3.model.cliente :as model.cliente]))


(test/deftest ClienteSchema-test
  (let [cliente {:id     1
                 :nome  "Fulano"
                 :cpf   "000.000.000-00"
                 :email "fulano@gmail.com"}]

    (test/testing "cliente que atende ao schema"
      (test/is (=
                 cliente
                 (s/validate model.cliente/ClienteSchema cliente))))

    (test/testing "nome invalido"
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.cliente/ClienteSchema (assoc cliente :nome "F"))))
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.cliente/ClienteSchema (assoc cliente :nome 999)))))

    (test/testing "cpf invalido"
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.cliente/ClienteSchema (assoc cliente :cpf "000.000.000/00"))))
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.cliente/ClienteSchema (assoc cliente :cpf 000000000000)))))

    (test/testing "email invalido"
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.cliente/ClienteSchema (assoc cliente :email "fulano@gmail"))))
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.cliente/ClienteSchema (assoc cliente :email "fulano gmail.com")))))))