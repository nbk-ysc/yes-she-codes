(ns yes-she-codes.cartao-test
  (:require [clojure.test :refer :all]
            [yes-she-codes.cartao :refer :all]
            [yes-she-codes.cliente :refer :all]
            [java-time :as t]))

(deftest novo-cartao-test
  (testing "Testando novo cartao multiplas vezes"
    (are [parametro-cartao esperado] (= esperado (novo-cartao parametro-cartao))
                                     [1234123412341234	111	"2023-01" 1000 {:nome "feiticeira escarlate"
                                                                             :cpf "000.111.222-33"
                                                                             :email "feiticeira.poderosa@vingadoras.com.br"}]
                                     {:numero 1234123412341234
                                      :cvv 111
                                      :validade (t/year-month "2023-01")
                                      :limite 1000
                                      :cliente "000.111.222-33"}
                                     [4321432143214321	222	"2024-02" 2000 {:nome "Viúva Negra"
                                                                             :cpf "333.444.555-66"
                                                                             :email "viuva.casca.grossa@vingadoras.com.br"}]
                                     {:numero 4321432143214321
                                      :cvv 222
                                      :validade (t/year-month "2024-02")
                                      :limite 2000
                                      :cliente "333.444.555-66"}
                                     [1598159815981598	333	"2021-03" 3000 {:nome "Hermione Granger"
                                                                             :cpf "666.777.888-99"
                                                                             :email "hermione.salvadora@hogwarts.com"}]
                                     {:numero 1598159815981598
                                      :cvv 333
                                      :validade (t/year-month "2021-03")
                                      :limite 3000
                                      :cliente "666.777.888-99"}
                                     [6655665566556655	444	"2025-04" 4000 {:nome "Hermione Granger"
                                                                             :cpf "666.777.888-99"
                                                                             :email "hermione.salvadora@hogwarts.com"}]
                                     {:numero 6655665566556655
                                      :cvv 444
                                      :validade (t/year-month "2025-04")
                                      :limite 4000
                                      :cliente"666.777.888-99"}
                                     [3939393939393939	555	"2026-05" 5000 {:nome "Daenerys Targaryen"
                                                                             :cpf "999.123.456-78"
                                                                             :email "mae.dos.dragoes@got.com"}]
                                     {:numero 3939393939393939
                                      :cvv 555
                                      :validade (t/year-month "2026-05")
                                      :limite 5000
                                      :cliente "999.123.456-78"}
                                     ))
  (testing "Testando com cliente invalido"
    (are [parametro-cartao] (thrown? Exception (novo-cartao parametro-cartao))
                                              nil
                                              [4321432143214321	222	"2024-02" 2000 nil])))

(deftest lista-cartoes-test
  (testing "Testando lista de cartoes"
    (is (= (lista-cartoes [
                           [1234123412341234	111	"2023-01" 1000 (novo-cliente ["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"])]
                           [4321432143214321	222	"2024-02" 2000 (novo-cliente ["Viúva Negra" "333.444.555-66" "viuva.casca.grossa@vingadoras.com.br"])]
                           [1598159815981598	333	"2021-03" 3000 (novo-cliente ["Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"])]
                           [6655665566556655	444	"2025-04" 4000 (novo-cliente ["Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"])]
                           [3939393939393939	555	"2026-05" 5000 (novo-cliente ["Daenerys Targaryen" "999.123.456-78" "mae.dos.dragoes@got.com"])]])
           [{
             :numero 1234123412341234
             :cvv 111,
             :validade (t/year-month "2023-01")
             :limite 1000
             :cliente "000.111.222-33"}
            {:numero 4321432143214321
             :cvv 222
             :validade (t/year-month "2024-02")
             :limite 2000
             :cliente "333.444.555-66"}
            {:numero 1598159815981598
             :cvv 333
             :validade (t/year-month "2021-03")
             :limite 3000
             :cliente "666.777.888-99"}
            {:numero 6655665566556655
             :cvv 444
             :validade (t/year-month "2025-04")
             :limite 4000
             :cliente "666.777.888-99"}
            {:numero 3939393939393939
             :cvv 555
             :validade (t/year-month "2026-05")
             :limite 5000
             :cliente "999.123.456-78"}]))))