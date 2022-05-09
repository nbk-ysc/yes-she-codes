(ns yes-she-codes.cliente_test
  (:require [clojure.test :refer :all]
            [yes-she-codes.cliente :refer :all]))

(deftest novo-cliente-test
  (testing "Testando novo cliente multiplas vezes"
    (are [nome cpf email esperado] (= esperado (novo-cliente nome cpf email))
                                   "feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"
                                   {:nome "feiticeira escarlate"
                                    :cpf "000.111.222-33"
                                    :email "feiticeira.poderosa@vingadoras.com.br"}
                                   "Viúva Negra" "333.444.555-66" "viuva.casca.grossa@vingadoras.com.br"
                                   {:nome "Viúva Negra"
                                    :cpf "333.444.555-66"
                                    :email "viuva.casca.grossa@vingadoras.com.br"}
                                   "Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"
                                   {:nome "Hermione Granger"
                                    :cpf "666.777.888-99"
                                    :email "hermione.salvadora@hogwarts.com"}
                                   "Daenerys Targaryen" "999.123.456-78" "mae.dos.dragoes@got.com"
                                   {:nome "Daenerys Targaryen"
                                    :cpf "999.123.456-78"
                                    :email "mae.dos.dragoes@got.com"})))