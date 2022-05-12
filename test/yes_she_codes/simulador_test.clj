(ns yes-she-codes.simulador-test
  (:require [clojure.test :refer :all]
            [yes-she-codes.simulador :refer :all]
            [yes-she-codes.compra :refer :all]
            [yes-she-codes.cartao :refer :all]
            [yes-she-codes.cliente :refer :all]))

(deftest total-gasto-test
  (testing "Testando total gasto"
    (is (= (total-gasto (lista-compras [["2022-01-01"	129.90 "Outback" "Alimentação"
                                         (novo-cartao [1234123412341234	111	"2023-01" 1000
                                                       (novo-cliente ["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"])])]
                                        ["2022-01-02"	260.00 "Dentista" "Saúde"
                                         (novo-cartao [1234123412341234	111	"2023-01" 1000
                                                       (novo-cliente ["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"])])]
                                        ["2022-02-01"	20.00	 "Cinema" "Lazer"
                                         (novo-cartao [1234123412341234	111	"2023-01" 1000
                                                       (novo-cliente ["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"])])]] ))
           409.9))))

(deftest busca-compras-mes-test
  (testing "Testando buscar compras por mes"
    (is (= (busca-compras-mes  "2022-01" (lista-compras [["2022-01-01"	129.90 "Outback" "Alimentação"
                                                                        (novo-cartao [1234123412341234	111	"2023-01" 1000
                                                                                      (novo-cliente ["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"])])]
                                                                       ["2022-01-02"	260.00 "Dentista" "Saúde"
                                                                        (novo-cartao [1234123412341234	111	"2023-01" 1000
                                                                                      (novo-cliente ["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"])])]
                                                                       ["2022-02-01"	20.00	 "Cinema" "Lazer"
                                                                        (novo-cartao [1234123412341234	111	"2023-01" 1000
                                                                                      (novo-cliente ["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"])])]] ))

           (lista-compras [["2022-01-01"	129.90 "Outback" "Alimentação"
                            (novo-cartao [1234123412341234	111	"2023-01" 1000
                                          (novo-cliente ["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"])])]
                           ["2022-01-02"	260.00 "Dentista" "Saúde"
                            (novo-cartao [1234123412341234	111	"2023-01" 1000
                                          (novo-cliente ["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"])])]])))))

(deftest busca-compras-estabelecimento-test
  (testing "Testando buscar compras por estabelecimento"
    (is (= (busca-compras-estabelecimento  "outback" (lista-compras [["2022-01-01"	129.90 "Outback" "Alimentação"
                                                                    (novo-cartao [1234123412341234	111	"2023-01" 1000
                                                                                  (novo-cliente ["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"])])]
                                                                   ["2022-01-02"	260.00 "Dentista" "Saúde"
                                                                    (novo-cartao [1234123412341234	111	"2023-01" 1000
                                                                                  (novo-cliente ["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"])])]
                                                                   ["2022-02-01"	20.00	 "Cinema" "Lazer"
                                                                    (novo-cartao [1234123412341234	111	"2023-01" 1000
                                                                                  (novo-cliente ["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"])])]] ))

           [(nova-compra  ["2022-01-01"	129.90 "Outback" "Alimentação"
                           (novo-cartao [1234123412341234	111	"2023-01" 1000
                                         (novo-cliente ["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"])])])]))))

(deftest total-gasto-no-mes-test
  (testing "Testando total gasto no mes"
    (is (= (total-gasto-no-mes 1234123412341234 "2022-01" (lista-compras [["2022-01-01"	129.90 "Outback" "Alimentação"
                                                                           (novo-cartao [1234123412341234	111	"2023-01" 1000
                                                                                         (novo-cliente ["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"])])]
                                                                          ["2022-01-02"	260.00 "Dentista" "Saúde"
                                                                           (novo-cartao [1234123412341234	111	"2023-01" 1000
                                                                                         (novo-cliente ["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"])])]
                                                                          ["2022-02-01"	20.00	 "Cinema" "Lazer"
                                                                           (novo-cartao [1234123412341234	111	"2023-01" 1000
                                                                                         (novo-cliente ["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"])])]] ))
           389.9 ))))

(deftest intervalo-compras-test
  (testing "Testando intervalo de valor de compras"
    (is (= (intervalo-compras 100 300 (lista-compras [["2022-01-01"	129.90 "Outback" "Alimentação"
                                                       (novo-cartao [1234123412341234	111	"2023-01" 1000
                                                                     (novo-cliente ["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"])])]
                                                      ["2022-01-02"	260.00 "Dentista" "Saúde"
                                                       (novo-cartao [1234123412341234	111	"2023-01" 1000
                                                                     (novo-cliente ["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"])])]
                                                      ["2022-02-01"	20.00	 "Cinema" "Lazer"
                                                       (novo-cartao [1234123412341234	111	"2023-01" 1000
                                                                     (novo-cliente ["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"])])]] ))

           (lista-compras [["2022-01-01"	129.90 "Outback" "Alimentação"
                            (novo-cartao [1234123412341234	111	"2023-01" 1000
                                          (novo-cliente ["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"])])]
                           ["2022-01-02"	260.00 "Dentista" "Saúde"
                            (novo-cartao [1234123412341234	111	"2023-01" 1000
                                          (novo-cliente ["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"])])]] )))))

(deftest gasto-categoria-test
  (testing "Testando categoria por teste"
    (is (= (gasto-categoria (lista-compras [["2022-01-01"	129.90 "Outback" "Alimentação"
                                             (novo-cartao [1234123412341234	111	"2023-01" 1000
                                                           (novo-cliente ["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"])])]
                                            ["2022-01-02"	260.00 "Dentista" "Saúde"
                                             (novo-cartao [1234123412341234	111	"2023-01" 1000
                                                           (novo-cliente ["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"])])]
                                            ["2022-02-01"	20.00	 "Cinema" "Lazer"
                                             (novo-cartao [1234123412341234	111	"2023-01" 1000
                                                           (novo-cliente ["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"])])]
                                            ["2022-01-10"	150.00 "Show" "Lazer"
                                             (novo-cartao [4321432143214321	222	"2024-02" 2000
                                                           (novo-cliente ["Viúva Negra" "333.444.555-66" "viuva.casca.grossa@vingadoras.com.br"])])]
                                            ["2022-02-10"	289.99 "Posto de gasolina" "Automóvel"
                                             (novo-cartao [4321432143214321	222	"2024-02" 2000
                                                           (novo-cliente ["Viúva Negra" "333.444.555-66" "viuva.casca.grossa@vingadoras.com.br"])])]
                                            ["2022-02-20"	79.90	 "iFood" "Alimentação"
                                             (novo-cartao [4321432143214321	222	"2024-02" 2000
                                                           (novo-cliente ["Viúva Negra" "333.444.555-66" "viuva.casca.grossa@vingadoras.com.br"])])]
                                            ["2022-03-01"	85.00	 "Alura" "Educação"
                                             (novo-cartao [4321432143214321	222	"2024-02" 2000
                                                           (novo-cliente ["Viúva Negra" "333.444.555-66" "viuva.casca.grossa@vingadoras.com.br"])])]
                                            ["2022-01-30"	85.00	 "Alura" "Educação"
                                             (novo-cartao [1598159815981598	333	"2021-03" 3000
                                                           (novo-cliente ["Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"])])]
                                            ["2022-01-31"	350.00 "Tok&Stok" "Casa"
                                             (novo-cartao [1598159815981598	333	"2021-03" 3000
                                                           (novo-cliente ["Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"])])]
                                            ["2022-02-01"	400.00 "Leroy Merlin" "Casa"
                                             (novo-cartao [1598159815981598	333	"2021-03" 3000
                                                           (novo-cliente ["Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"])])]
                                            ["2022-03-01"	50.00	 "Madero" "Alimentação"
                                             (novo-cartao [6655665566556655	444	"2025-04" 4000
                                                           (novo-cliente ["Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"])])]
                                            ["2022-03-01"	70.00	 "Teatro" "Lazer"
                                             (novo-cartao [6655665566556655	444	"2025-04" 4000
                                                           (novo-cliente ["Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"])])]
                                            ["2022-03-04"	250.00 "Hospital" "Saúde"
                                             (novo-cartao [6655665566556655	444	"2025-04" 4000
                                                           (novo-cliente ["Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"])])]
                                            ["2022-04-10"	130.00 "Drogaria"	"Saúde"
                                             (novo-cartao [6655665566556655	444	"2025-04" 4000
                                                           (novo-cliente ["Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"])])]
                                            ["2022-03-10"	100.00 "Show de pagode" "Lazer"
                                             (novo-cartao [3939393939393939	555	"2026-05" 5000
                                                           (novo-cliente ["Daenerys Targaryen" "999.123.456-78" "mae.dos.dragoes@got.com"])])]
                                            ["2022-03-11"	25.90	 "Dogão" "Alimentação"
                                             (novo-cartao [3939393939393939	555	"2026-05" 5000
                                                           (novo-cliente ["Daenerys Targaryen" "999.123.456-78" "mae.dos.dragoes@got.com"])])]
                                            ["2022-03-12"	215.87 "Praia" "Lazer"
                                             (novo-cartao [3939393939393939	555	"2026-05" 5000
                                                           (novo-cliente ["Daenerys Targaryen" "999.123.456-78" "mae.dos.dragoes@got.com"])])]

                                            ["2022-04-01"	976.88 "Oficina" "Automóvel"
                                             (novo-cartao [3939393939393939	555	"2026-05" 5000
                                                           (novo-cliente ["Daenerys Targaryen" "999.123.456-78" "mae.dos.dragoes@got.com"])])]

                                            ["2022-04-10"	85.00	 "Alura" "Educação"
                                             (novo-cartao [3939393939393939	555	"2026-05" 5000
                                                           (novo-cliente ["Daenerys Targaryen" "999.123.456-78" "mae.dos.dragoes@got.com"])])]]))
           [{:categoria "Alimentação"
             :total 285.7}
            {:categoria "Saúde"
             :total 640.0}
            {:categoria "Lazer"
             :total 555.87}
            {:categoria "Automóvel"
             :total 1266.87}
            {:categoria "Educação"
             :total 255.0}
            {:categoria "Casa"
             :total 750.0}]
           ))))