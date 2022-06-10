(ns yes-she-codes.clientes-test
  (:require [clojure.test :refer :all]
            [schema.core :as s]
            [yes-she-codes.models.clientes :refer :all]))

(deftest cliente-schema-test
  (let [cliente-test {:nome  "Viúva Negra"
                      :cpf   "333.444.555-66"
                      :email "viuva.casca.grossa@vingadoras.com.br"}]
    (testing "Que o schema de cliente aceita um cliente válido"
      (is cliente-test (s/validate ClienteSchema cliente-test)))))
