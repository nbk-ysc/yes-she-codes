(ns yes-she-codes.week3.model.cliente-test
  (:require [clojure.test :as test]
            [schema.core :as s]
            [yes-she-codes.week3.model.cliente :as model.cliente]))


(test/deftest ClienteSchema-test
  (let [cliente {:nome "Viúva Negra"
                 :cpf "333.444.555-66"
                 :email "viuva.casca.grossa@vingadoras.com.br"}]

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
                 (s/validate model.cliente/ClienteSchema (assoc cliente :email "fulano gmail.com")))))

    (test/testing "campos nulos"
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.cliente/ClienteSchema (assoc cliente :nome nil))))
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.cliente/ClienteSchema (assoc cliente :cpf nil))))
      (test/is (thrown?
                 clojure.lang.ExceptionInfo
                 (s/validate model.cliente/ClienteSchema (assoc cliente :email nil)))))))