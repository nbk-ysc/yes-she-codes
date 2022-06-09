(ns yes-she-codes.cliente-test
  (:require [clojure.test :refer :all]
            [semana3.cliente :refer :all]
            [schema.core :as s]))

(deftest testa-schema-cliente

  (testing "Deve aceitar o cliente valido"
    (let [cliente-valido {:nome "Viúva Negra",
                          :cpf "333.444.555-66",
                          :email "viuva.casca.grossa@vingadoras.com.br"
                          }]
      (is (= cliente-valido (s/validate ClienteSchema cliente-valido)))))

  (testing "1 != 2"
    (is (not (= 1 2))))
  )