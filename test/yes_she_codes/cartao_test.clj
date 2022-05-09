(ns yes-she-codes.cartao-test
  (:require [clojure.test :refer :all]
            [yes-she-codes.cartao :refer :all]))

(deftest novo-cartao-test
  (testing "Testando novo cartao multiplas vezes"
    (are [numero cvv validade limite cliente esperado] (= esperado (novo-cartao numero cvv validade limite cliente))
                                                   1234123412341234	111	"2023-01" 1000
                                                   {:nome "feiticeira escarlate"
                                                    :cpf "000.111.222-33"
                                                    :email "feiticeira.poderosa@vingadoras.com.br"}
                                                   {:numero 1234123412341234
                                                    :cvv 111
                                                    :validade "2023-01"
                                                    :limite 1000
                                                    :cliente "000.111.222-33"}
                                                   4321432143214321	222	"2024-02" 2000
                                                   {:nome "Viúva Negra"
                                                    :cpf "333.444.555-66"
                                                    :email "viuva.casca.grossa@vingadoras.com.br"}
                                                   {:numero 4321432143214321
                                                    :cvv 222
                                                    :validade "2024-02"
                                                    :limite 2000
                                                    :cliente "333.444.555-66"}
                                                   1598159815981598	333	"2021-03" 3000
                                                   {:nome "Hermione Granger"
                                                    :cpf "666.777.888-99"
                                                    :email "hermione.salvadora@hogwarts.com"}
                                                   {:numero 1598159815981598
                                                    :cvv 333
                                                    :validade "2021-03"
                                                    :limite 3000
                                                    :cliente "666.777.888-99"}
                                                   6655665566556655	444	"2025-04" 4000
                                                   {:nome "Hermione Granger"
                                                    :cpf "666.777.888-99"
                                                    :email "hermione.salvadora@hogwarts.com"}
                                                   {:numero 6655665566556655
                                                    :cvv 444
                                                    :validade "2025-04"
                                                    :limite 4000
                                                    :cliente"666.777.888-99"}
                                                   3939393939393939	555	"2026-05" 5000
                                                   {:nome "Daenerys Targaryen"
                                                    :cpf "999.123.456-78"
                                                    :email "mae.dos.dragoes@got.com"}
                                                   {:numero 3939393939393939
                                                    :cvv 555
                                                    :validade "2026-05"
                                                    :limite 5000
                                                    :cliente "999.123.456-78"}
                                                   ))
  (testing "Testando com cliente invalido"
    (are [numero cvv validade limite cliente] (thrown? Exception (novo-cartao numero cvv validade limite cliente))
                                          1234123412341234	111	"2023-01" 1000 "000.111.222-33"
                                          4321432143214321	222	"2024-02" 2000 nil
                                          )))