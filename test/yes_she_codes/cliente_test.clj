(ns yes-she-codes.cliente-test
  (:require [yes-she-codes.dominio.cliente :refer :all]
            [schema.core :as s]
            [clojure.test :refer :all]))

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
                            :cpf   "333.444.555-66",
                            :email "viuva.casca.grossa#vingadoras.com.br"}]
      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate ClienteSchema cliente-invalido)))
      ))
  )

