(ns yes-she-codes.cliente-test
  (:require [clojure.test :refer :all]
            [semana3.cliente :refer :all]
            [schema.core :as s
             :include-macros true                           ;; cljs only
             ]))

(deftest cliente-test
  (testing "Esquema aceita um cliente válido"
    (is (= {:nome  "Viúva Negra"
            :cpf   "333.444.555-66"
            :email "viuva.casca.grossa@vingadoras.com.br"}
           (s/validate ClienteSchema {:nome  "Viúva Negra"
                                      :cpf   "333.444.555-66"
                                      :email "viuva.casca.grossa@vingadoras.com.br"}))))

  (testing "Cliente com nome nil"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate ClienteSchema {:nome  nil
                                            :cpf   "333.444.555-66"
                                            :email "viuva.casca.grossa@vingadoras.com.br"}))))

  (testing "CPF com formato inválido"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate ClienteSchema {:nome  "Viúva Negra"
                                            :cpf   "333.444.55566"
                                            :email "viuva.casca.grossa@vingadoras.com.br"}))))

  ;(testing "CPF com formato inválido"
  ;  (is (try (s/validate ClienteSchema {:nome  "Viúva Negra"
  ;                                      :cpf   "333.444.55566"
  ;                                      :email "viuva.casca.grossa@vingadoras.com.br"})
  ;           (catch clojure.lang.ExceptionInfo e
  ;             (println (ex-data e))
  ;             (= :cpf-invalido (:tipo (ex-data e)))))))

  (testing "Email inválido"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate ClienteSchema {:nome  "Viúva Negra"
                                            :cpf   "333.444.555-66"
                                            :email "viuva"})))))

