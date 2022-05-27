(ns yes-she-codes.semana3.clientes_test
  (:require [clojure.test :refer :all]
            [schema.core :as s]
            [yes-she-codes.semana3.clientes :refer :all])
  (:import (clojure.lang ExceptionInfo)))


(deftest cliente-schema-test





  (testing "Testa criar cliente com valores especificos"
    (let [cliente-test {:nome "Viúva Negra",
                        :cpf "333.444.555-66",
                        :email"viuva.casca.grossa@vingadoras.com.br"}]
      (is (= (s/validate ClienteSchema cliente-test)
             cliente-test))
      ))




  (testing "Testando cliente com nome nil"
    (let [cliente-test-invalido {:nome nil,
                        :cpf "333.444.555-66",
                        :email"viuva.casca.grossa@vingadoras.com.br"}]
      (is (thrown? ExceptionInfo
                   (s/validate ClienteSchema cliente-test-invalido)
                   cliente-test-invalido))
      ))




  (testing "Testando cliente com cpf com formato invalido"
    (let [cliente-test-invalido {:nome "Viúva Negra",
                                 :cpf "33344455566",
                                 :email"viuva.casca.grossa@vingadoras.com.br"}]
      (is (thrown? ExceptionInfo
                   (s/validate ClienteSchema cliente-test-invalido)
                   cliente-test-invalido))
      ))




  (testing "Testando cliente com email invalido"
    (let [cliente-test-invalido {:nome "Viúva Negra",
                                 :cpf "333.444.555-66",
                                 :email"viuva.casca.grossavingadoras.com.br"}]
      (is (thrown? ExceptionInfo
                   (s/validate ClienteSchema cliente-test-invalido)
                   cliente-test-invalido))
      ))


  )





