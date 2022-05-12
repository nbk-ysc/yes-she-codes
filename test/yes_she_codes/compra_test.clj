(ns yes-she-codes.compra-test
  (:require [clojure.test :refer :all]
            [yes-she-codes.compra :refer :all]
            [yes-she-codes.cartao :refer :all]
            [yes-she-codes.cliente :refer :all]
            [java-time :as t]))

(deftest nova-compra-test
  (testing "Testando nova compra multiplas vezes"
    (are [parametro-compra esperado] (= esperado (nova-compra parametro-compra))
                                     ["2022-01-01"	129.90 "Outback" "Alimentação"
                                      (novo-cartao [1234123412341234	111	"2023-01" 1000
                                                    (novo-cliente ["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"])])]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-01-01") :valor 129.90 :estabelecimento "Outback" :categoria "Alimentação" :cartao 1234123412341234}
                                     ["2022-01-02"	260.00 "Dentista" "Saúde"
                                      (novo-cartao [1234123412341234	111	"2023-01" 1000
                                                    (novo-cliente ["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"])])]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-01-02") :valor 260.00 :estabelecimento "Dentista" :categoria "Saúde" :cartao 1234123412341234}
                                     ["2022-02-01"	20.00	 "Cinema" "Lazer"
                                      (novo-cartao [1234123412341234	111	"2023-01" 1000
                                                    (novo-cliente ["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"])])]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-02-01") :valor 20.00 :estabelecimento "Cinema" :categoria "Lazer" :cartao 1234123412341234}
                                     ["2022-01-10"	150.00 "Show" "Lazer"
                                      (novo-cartao [4321432143214321	222	"2024-02" 2000
                                                    (novo-cliente ["Viúva Negra" "333.444.555-66" "viuva.casca.grossa@vingadoras.com.br"])])]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-01-10") :valor 150.00 :estabelecimento "Show" :categoria "Lazer" :cartao 4321432143214321}
                                     ["2022-02-10"	289.99 "Posto de gasolina" "Automóvel"
                                      (novo-cartao [4321432143214321	222	"2024-02" 2000
                                                    (novo-cliente ["Viúva Negra" "333.444.555-66" "viuva.casca.grossa@vingadoras.com.br"])])]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-02-10") :valor 289.99 :estabelecimento "Posto de gasolina" :categoria "Automóvel" :cartao 4321432143214321}
                                     ["2022-02-20"	79.90	 "iFood" "Alimentação"
                                      (novo-cartao [4321432143214321	222	"2024-02" 2000
                                                    (novo-cliente ["Viúva Negra" "333.444.555-66" "viuva.casca.grossa@vingadoras.com.br"])])]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-02-20") :valor 	79.90	 :estabelecimento "iFood"  :categoria "Alimentação" :cartao 4321432143214321}
                                     ["2022-03-01"	85.00	 "Alura" "Educação"
                                      (novo-cartao [4321432143214321	222	"2024-02" 2000
                                                    (novo-cliente ["Viúva Negra" "333.444.555-66" "viuva.casca.grossa@vingadoras.com.br"])])]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-03-01") :valor 85.00 :estabelecimento "Alura" :categoria "Educação" :cartao 4321432143214321}
                                     ["2022-01-30"	85.00	 "Alura" "Educação"
                                      (novo-cartao [1598159815981598	333	"2021-03" 3000
                                                    (novo-cliente ["Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"])])]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-01-30") :valor 85.00 :estabelecimento "Alura" :categoria "Educação" :cartao 1598159815981598}
                                     ["2022-01-31"	350.00 "Tok&Stok" "Casa"
                                      (novo-cartao [1598159815981598	333	"2021-03" 3000
                                                    (novo-cliente ["Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"])])]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-01-31") :valor 350.00 :estabelecimento "Tok&Stok" :categoria "Casa" :cartao 1598159815981598}
                                     ["2022-02-01"	400.00 "Leroy Merlin" "Casa"
                                      (novo-cartao [1598159815981598	333	"2021-03" 3000
                                                    (novo-cliente ["Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"])])]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-02-01") :valor 400.00 :estabelecimento "Leroy Merlin" :categoria "Casa" :cartao 1598159815981598}
                                     ["2022-03-01"	50.00	 "Madero" "Alimentação"
                                      (novo-cartao [6655665566556655	444	"2025-04" 4000
                                                    (novo-cliente ["Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"])])]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-03-01") :valor 50.00	 :estabelecimento "Madero" :categoria "Alimentação" :cartao 6655665566556655}
                                     ["2022-03-01"	70.00	 "Teatro" "Lazer"
                                      (novo-cartao [6655665566556655	444	"2025-04" 4000
                                                    (novo-cliente ["Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"])])]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-03-01") :valor 70.00 :estabelecimento "Teatro" :categoria "Lazer" :cartao 6655665566556655}
                                     ["2022-03-04"	250.00 "Hospital" "Saúde"
                                      (novo-cartao [6655665566556655	444	"2025-04" 4000
                                                    (novo-cliente ["Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"])])]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-03-04") :valor 250.00  :estabelecimento "Hospital" :categoria "Saúde" :cartao 6655665566556655}
                                     ["2022-04-10"	130.00 "Drogaria"	"Saúde"
                                      (novo-cartao [6655665566556655	444	"2025-04" 4000
                                                    (novo-cliente ["Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"])])]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-04-10")	:valor 130.00 :estabelecimento "Drogaria" :categoria "Saúde" :cartao 6655665566556655}
                                     ["2022-03-10"	100.00 "Show de pagode" "Lazer"
                                      (novo-cartao [3939393939393939	555	"2026-05" 5000
                                                    (novo-cliente ["Daenerys Targaryen" "999.123.456-78" "mae.dos.dragoes@got.com"])])]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-03-10")	 :valor 100.00  :estabelecimento "Show de pagode" :categoria "Lazer" :cartao 3939393939393939}
                                     ["2022-03-11"	25.90	 "Dogão" "Alimentação"
                                      (novo-cartao [3939393939393939	555	"2026-05" 5000
                                                    (novo-cliente ["Daenerys Targaryen" "999.123.456-78" "mae.dos.dragoes@got.com"])])]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-03-11") :valor 25.90 :estabelecimento "Dogão":categoria "Alimentação":cartao 3939393939393939}
                                     ["2022-03-12"	215.87 "Praia" "Lazer"
                                      (novo-cartao [3939393939393939	555	"2026-05" 5000
                                                    (novo-cliente ["Daenerys Targaryen" "999.123.456-78" "mae.dos.dragoes@got.com"])])]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-03-12") :valor 215.87 :estabelecimento "Praia" :categoria "Lazer" :cartao 3939393939393939}
                                     ["2022-04-01"	976.88 "Oficina" "Automóvel"
                                      (novo-cartao [3939393939393939	555	"2026-05" 5000
                                                    (novo-cliente ["Daenerys Targaryen" "999.123.456-78" "mae.dos.dragoes@got.com"])])]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-04-01") :valor 976.88 :estabelecimento "Oficina" :categoria "Automóvel":cartao 3939393939393939}
                                     ["2022-04-10"	85.00	 "Alura" "Educação"
                                      (novo-cartao [3939393939393939	555	"2026-05" 5000
                                                    (novo-cliente ["Daenerys Targaryen" "999.123.456-78" "mae.dos.dragoes@got.com"])])]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-04-10") :valor 85.00 :estabelecimento  "Alura"  :categoria "Educação" :cartao 3939393939393939}))
  (testing "Testando com cartao invalido"
    (are [parametro-compra] (thrown? Exception (nova-compra parametro-compra))
                            ["2022-01-01"	129.90 "Outback" "Alimentação" 1234123412341234]
                            ["2022-01-02"	260.00 "Dentista" "Saúde" nil])))

(deftest lista-compras-test
  (testing "Testando lista de compras"
    (is (= (lista-compras [["2022-01-01"	129.90 "Outback" "Alimentação"
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
                                          (novo-cliente ["Daenerys Targaryen" "999.123.456-78" "mae.dos.dragoes@got.com"])])]])
           [{:data (t/local-date "yyyy-MM-dd" "2022-01-01")
             :valor 129.9
             :estabelecimento "Outback"
             :categoria "Alimentação"
             :cartao 1234123412341234}
            {:data (t/local-date "yyyy-MM-dd" "2022-01-02")
             :valor 260.0
             :estabelecimento "Dentista"
             :categoria "Saúde"
             :cartao 1234123412341234}
            {:data (t/local-date "yyyy-MM-dd" "2022-02-01")
             :valor 20.0
             :estabelecimento "Cinema"
             :categoria "Lazer"
             :cartao 1234123412341234}
            {:data (t/local-date "yyyy-MM-dd" "2022-01-10")
             :valor 150.0
             :estabelecimento "Show"
             :categoria "Lazer"
             :cartao 4321432143214321}
            {:data (t/local-date "yyyy-MM-dd" "2022-02-10")
             :valor 289.99
             :estabelecimento "Posto de gasolina"
             :categoria "Automóvel"
             :cartao 4321432143214321}
            {:data (t/local-date "yyyy-MM-dd" "2022-02-20")
             :valor 79.9
             :estabelecimento "iFood"
             :categoria "Alimentação"
             :cartao 4321432143214321}
            {:data (t/local-date "yyyy-MM-dd" "2022-03-01")
             :valor 85.0
             :estabelecimento "Alura"
             :categoria "Educação"
             :cartao 4321432143214321}
            {:data (t/local-date "yyyy-MM-dd" "2022-01-30")
             :valor 85.0
             :estabelecimento "Alura"
             :categoria "Educação"
             :cartao 1598159815981598}
            {:data (t/local-date "yyyy-MM-dd" "2022-01-31")
             :valor 350.0
             :estabelecimento "Tok&Stok"
             :categoria "Casa"
             :cartao 1598159815981598}
            {:data (t/local-date "yyyy-MM-dd" "2022-02-01")
             :valor 400.0
             :estabelecimento "Leroy Merlin"
             :categoria "Casa"
             :cartao 1598159815981598}
            {:data (t/local-date "yyyy-MM-dd" "2022-03-01")
             :valor 50.0
             :estabelecimento "Madero"
             :categoria "Alimentação"
             :cartao 6655665566556655}
            {:data (t/local-date "yyyy-MM-dd" "2022-03-01")
             :valor 70.0
             :estabelecimento "Teatro"
             :categoria "Lazer"
             :cartao 6655665566556655}
            {:data (t/local-date "yyyy-MM-dd" "2022-03-04")
             :valor 250.0
             :estabelecimento "Hospital"
             :categoria "Saúde"
             :cartao 6655665566556655}
            {:data (t/local-date "yyyy-MM-dd" "2022-04-10")
             :valor 130.0
             :estabelecimento "Drogaria"
             :categoria "Saúde"
             :cartao 6655665566556655}
            {:data (t/local-date "yyyy-MM-dd" "2022-03-10")
             :valor 100.0
             :estabelecimento "Show de pagode"
             :categoria "Lazer"
             :cartao 3939393939393939}
            {:data (t/local-date "yyyy-MM-dd" "2022-03-11")
             :valor 25.9
             :estabelecimento "Dogão"
             :categoria "Alimentação"
             :cartao 3939393939393939}
            {:data (t/local-date "yyyy-MM-dd" "2022-03-12")
             :valor 215.87
             :estabelecimento "Praia"
             :categoria "Lazer"
             :cartao 3939393939393939}
            {:data (t/local-date "yyyy-MM-dd" "2022-04-01")
             :valor 976.88
             :estabelecimento "Oficina"
             :categoria "Automóvel"
             :cartao 3939393939393939}
            {:data (t/local-date "yyyy-MM-dd" "2022-04-10")
             :valor 85.0
             :estabelecimento "Alura"
             :categoria "Educação"
             :cartao 3939393939393939}]))))