(ns yes-she-codes.dominio.cliente_test
  (:require [yes-she-codes.dominio.cliente :refer :all]
            [clojure.test :refer :all]
            [schema.core :as s]))

(deftest testando-cliente-schema
  (testing "Esquema deve aceitar campos obrigatórios válidos"
    (let [cliente-valido {:nome  "Viúva Negra"
                          :cpf   "333.444.555-66"
                          :email "viuva.casca.grossa@vingadoras.com.br"}]
      (is (= (s/validate ClienteSchema cliente-valido)
             cliente-valido))))
  (testing "Esquema deve aceitar clientes com :id preenchido"
    (let [cliente-valido {:id    1
                          :nome  "Viúva Negra"
                          :cpf   "333.444.555-66"
                          :email "viuva.casca.grossa@vingadoras.com.br"}]
      (is (= (s/validate ClienteSchema cliente-valido)
             cliente-valido))))
  (testing "Esquema deve aceitar clientes com :id NIL"
    (let [cliente-valido {:id    nil
                          :nome  "Viúva Negra"
                          :cpf   "333.444.555-66"
                          :email "viuva.casca.grossa@vingadoras.com.br"}]
      (is (= (s/validate ClienteSchema cliente-valido)
             cliente-valido))))
  (testing "Deve lançar erro se o CPF estiver com formato inválido"
    (let [cliente-com-cpf-invalido {:id    1
                                    :nome  "Viúva Negra"
                                    :cpf   "333"
                                    :email "viuva.casca.grossa@vingadoras.com.br"}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate ClienteSchema cliente-com-cpf-invalido)))))
  (testing "Deve lançar erro se nome estiver NIL"
    (let [cliente-com-cpf-invalido {:id    1
                                    :nome  nil
                                    :cpf   "333.444.555-66"
                                    :email "viuva.casca.grossa@vingadoras.com.br"}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate ClienteSchema cliente-com-cpf-invalido)))))
  (testing "Deve lançar erro se email estiver inválido"
    (let [cliente-com-cpf-invalido {:id    1
                                    :nome  "Viúva Negra"
                                    :cpf   "333.444.555-66"
                                    :email "viuva.casca.grossa"}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate ClienteSchema cliente-com-cpf-invalido))))))
