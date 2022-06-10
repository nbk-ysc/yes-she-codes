(ns yes-she-codes.domain.client_test
  (:require [yes-she-codes.domain.client :refer :all]
            [clojure.test :refer :all]
            [schema.core :as s]))


(deftest testing-client-schema

  (testing "Schema should accept valid mandatory fields"

    (let [valid-client {:name  "Viúva Negra"
                          :cpf   "333.444.555-66"
                          :email "viuva.casca.grossa@vingadoras.com.br"}]

      (is (= (s/validate ClientSchema valid-client)
             valid-client))))

  (testing "Schema should accept clients with :id filled"

    (let [valid-client {:id    1
                          :name  "Viúva Negra"
                          :cpf   "333.444.555-66"
                          :email "viuva.casca.grossa@vingadoras.com.br"}]

      (is (= (s/validate ClientSchema valid-client)
             valid-client))))


  (testing "Schema should accept clients com :id NIL"

    (let [valid-client {:id    nil
                          :name  "Viúva Negra"
                          :cpf   "333.444.555-66"
                          :email "viuva.casca.grossa@vingadoras.com.br"}]

      (is (= (s/validate ClientSchema valid-client)
             valid-client))))


  (testing "Should not accept invalid CPF"
    (let [invalid-client {:id    1
                                    :name  "Viúva Negra"
                                    :cpf   "333"
                                    :email "viuva.casca.grossa@vingadoras.com.br"}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate ClientSchema invalid-client)))))


  (testing "Should not accept invalid name"
    (let [invalid-client {:id    1
                                    :name  nil
                                    :cpf   "333.444.555-66"
                                    :email "viuva.casca.grossa@vingadoras.com.br"}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate ClientSchema invalid-client)))))


  (testing "Should not accept invalid email"
    (let [invalid-client {:id    1
                                    :name  "Viúva Negra"
                                    :cpf   "333.444.555-66"
                                    :email "viuva.casca.grossa"}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate ClientSchema invalid-client)))))

  )
