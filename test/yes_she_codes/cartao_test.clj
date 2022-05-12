(ns yes-she-codes.cartao-test
  (:require [clojure.test :refer :all]
            [yes-she-codes.cartao :refer :all]
            [yes-she-codes.cliente :refer :all]
            [java-time :as t]))

(deftest novo-cartao-test
  (testing "Testando novo cartao multiplas vezes"
    (are [parametro-cartao esperado] (= esperado (novo-cartao parametro-cartao))
                                     ["1234 1234 1234 1234" "111" "2023-01" "1.000" "000.111.222-33"]
                                     {:numero   1234123412341234
                                      :cvv      111
                                      :validade (t/year-month "2023-01")
                                      :limite   1000.0
                                      :cliente  "000.111.222-33"}
                                     ["4321 4321 4321 4321" "222" "2024-02" "2.000" "333.444.555-66"]
                                     {:numero   4321432143214321
                                      :cvv      222
                                      :validade (t/year-month "2024-02")
                                      :limite   2000.0
                                      :cliente  "333.444.555-66"}
                                     ["1598 1598 1598 1598" "333" "2021-03" "3.000" "666.777.888-99"]
                                     {:numero   1598159815981598
                                      :cvv      333
                                      :validade (t/year-month "2021-03")
                                      :limite   3000.0
                                      :cliente  "666.777.888-99"}
                                     ["6655 6655 6655 6655" "444" "2025-04" "4.000" "666.777.888-99"]
                                     {:numero   6655665566556655
                                      :cvv      444
                                      :validade (t/year-month "2025-04")
                                      :limite   4000.0
                                      :cliente  "666.777.888-99"}
                                     ["3939 3939 3939 3939" "555" "2026-05" "5.000" "999.123.456-78"]
                                     {:numero   3939393939393939
                                      :cvv      555
                                      :validade (t/year-month "2026-05")
                                      :limite   5000.0
                                      :cliente  "999.123.456-78"}) )
  (testing "Testando com cliente invalido"
    (are [parametro-cartao] (thrown? Exception (novo-cartao parametro-cartao))
                            nil
                            [4321432143214321 222 "2024-02" 2000 nil])))

(deftest lista-cartoes-test
  (testing "Testando lista de cartoes"
    (is (= (lista-cartoes "arquivos/cartoes.csv")
           [{
             :numero 1234123412341234
             :cvv 111,
             :validade (t/year-month "2023-01")
             :limite 1000.0
             :cliente "000.111.222-33"}
            {:numero 4321432143214321
             :cvv 222
             :validade (t/year-month "2024-02")
             :limite 2000.0
             :cliente "333.444.555-66"}
            {:numero 1598159815981598
             :cvv 333
             :validade (t/year-month "2021-03")
             :limite 3000.0
             :cliente "666.777.888-99"}
            {:numero 6655665566556655
             :cvv 444
             :validade (t/year-month "2025-04")
             :limite 4000.0
             :cliente "666.777.888-99"}
            {:numero 3939393939393939
             :cvv 555
             :validade (t/year-month "2026-05")
             :limite 5000.0
             :cliente "999.123.456-78"}]))))