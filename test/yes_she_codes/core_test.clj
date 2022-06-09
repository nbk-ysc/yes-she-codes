(ns yes-she-codes.core-test
  (:require [clojure.test :refer :all]
            [yes-she-codes.semana3.core :refer :all]
            [schema.core :as s]))

;CLIENTE
(deftest testa-cliente
         (testing "Deve aceitar cliente válidos"

                  (let [cliente-valida {:nome "Viúva Negra"
                                       :cpf "333.444.555-66"
                                       :email "viuva.casca.grossa@vingadoras.com.br"}]

                       (is (= cliente-valida (s/validate ClienteSchema cliente-valida)))))

         (testing "Nào deve aceitar cliente com campos inválidos"

                  (let [cliente-valida {:nome "Viúva Negra"
                                        :cpf "333.444.555-66"
                                        :email "viuva.casca.grossavingadoras.com.br"}]

                       (is (= cliente-valida (s/validate ClienteSchema cliente-valida)))))
         )

;CARTAO
(deftest testa-cartao
         (testing "Deve aceitar cartao válidos"

                  (let [cartao-valida {:numero (4321432143214321)
                                       :cvv 222
                                       :validade "2024-02"
                                       :limite 2.000M
                                       :cliente-cpf "333.444.555-66"}]

                       (is (= cartao-valida (s/validate CartaoSchema cartao-valida)))))
         (testing "Não deve aceitar cartao com campo inválidos"

                  (let [cartao-valida {:numero (4321432143214321)
                                       :cvv "222"
                                       :validade "2024-02"
                                       :limite 2.000M
                                       :cliente-cpf "333.444.555-66"}]

                       (is (= cartao-valida (s/validate CartaoSchema cartao-valida))))))


;Compra
(deftest testa-compra
         (testing "Deve aceitar compra válidos"

                  (let [compra-valida {:data            (t/local-date "2022-05-09")
                                       :valor           100M
                                       :estabelecimento "Amazon"
                                       :categoria       "Casa"
                                       :cartao          4321432143214321}]

                       (is (= compra-valida (s/validate CompraSchema compra-valida)))))

         (testing "Deve aceitar compra com campos obrigatórios válidos"

                  (let [compra-valida {:data            (t/local-date "2022-05-09")
                                       :valor           100M
                                       :estabelecimento "Amazon"
                                       :categoria       "Casa"
                                       :cartao          4321432143214321}]

                       (is (= compra-valida (s/validate CompraSchema compra-valida)))))
         )