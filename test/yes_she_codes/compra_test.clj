(ns yes-she-codes.compra-test
  (:require [clojure.test :refer :all]
            [yes-she-codes.compra :refer :all]))

(deftest nova-compra-test
  (testing "Testando nova compra multiplas vezes"
    (are [parametro-compra esperado] (= esperado (nova-compra parametro-compra))
                                                                   ["2022-01-01"	129.90 "Outback" "Alimentação"	{:numero 1234123412341234
                                                                                                                  :cvv 111
                                                                                                                  :validade "2023-01"
                                                                                                                  :limite 1000
                                                                                                                  :cliente "000.111.222-33"}]
                                                                   {:data "2022-01-01" :valor 129.90 :estabelecimento "Outback" :categoria "Alimentação" :cartao 1234123412341234}
                                                                   ["2022-01-02"	260.00 "Dentista" "Saúde"	{:numero 1234123412341234
                                                                                                             :cvv 111
                                                                                                             :validade "2023-01"
                                                                                                             :limite 1000
                                                                                                             :cliente "000.111.222-33"}]
                                                                   {:data "2022-01-02" :valor 260.00 :estabelecimento "Dentista" :categoria "Saúde" :cartao 1234123412341234}
                                                                   ["2022-02-01"	20.00	 "Cinema" "Lazer"	{:numero 1234123412341234
                                                                                                            :cvv 111
                                                                                                            :validade "2023-01"
                                                                                                            :limite 1000
                                                                                                            :cliente "000.111.222-33"}]
                                                                   {:data "2022-02-01" :valor 20.00 :estabelecimento "Cinema" :categoria "Lazer" :cartao 1234123412341234}
                                                                   ["2022-01-10"	150.00 "Show" "Lazer"	{:numero 4321432143214321
                                                                                                         :cvv 222
                                                                                                         :validade "2024-02"
                                                                                                         :limite 2000
                                                                                                         :cliente "333.444.555-66"}]
                                                                   {:data "2022-01-10" :valor 150.00 :estabelecimento "Show" :categoria "Lazer" :cartao 4321432143214321}
                                                                   ["2022-02-10"	289.99 "Posto de gasolina" "Automóvel"	{:numero 4321432143214321
                                                                                                                          :cvv 222
                                                                                                                          :validade "2024-02"
                                                                                                                          :limite 2000
                                                                                                                          :cliente "333.444.555-66"}]
                                                                   {:data "2022-02-10" :valor 289.99 :estabelecimento "Posto de gasolina" :categoria "Automóvel" :cartao 4321432143214321}
                                                                   ["2022-02-20"	79.90	 "iFood" "Alimentação"	{:numero 4321432143214321
                                                                                                                 :cvv 222
                                                                                                                 :validade "2024-02"
                                                                                                                 :limite 2000
                                                                                                                 :cliente "333.444.555-66"}]
                                                                   {:data "2022-02-20" :valor 	79.90	 :estabelecimento "iFood"  :categoria "Alimentação" :cartao 4321432143214321}
                                                                   ["2022-03-01"	85.00	 "Alura" "Educação"	{:numero 4321432143214321
                                                                                                              :cvv 222
                                                                                                              :validade "2024-02"
                                                                                                              :limite 2000
                                                                                                              :cliente "333.444.555-66"}]
                                                                   {:data "2022-03-01" :valor 85.00 :estabelecimento "Alura" :categoria "Educação" :cartao 4321432143214321}
                                                                   ["2022-01-30"	85.00	 "Alura" "Educação"	{:numero 1598159815981598
                                                                                                              :cvv 333
                                                                                                              :validade "2021-03"
                                                                                                              :limite 3000
                                                                                                              :cliente "666.777.888-99"}]
                                                                   {:data "2022-01-30" :valor 85.00 :estabelecimento "Alura" :categoria "Educação" :cartao 1598159815981598}
                                                                   ["2022-01-31"	350.00 "Tok&Stok" "Casa"	{:numero 1598159815981598
                                                                                                            :cvv 333
                                                                                                            :validade "2021-03"
                                                                                                            :limite 3000
                                                                                                            :cliente "666.777.888-99"}]
                                                                   {:data "2022-01-31" :valor 350.00 :estabelecimento "Tok&Stok" :categoria "Casa" :cartao 1598159815981598}
                                                                   ["2022-02-01"	400.00 "Leroy Merlin" "Casa"	{:numero 1598159815981598
                                                                                                                :cvv 333
                                                                                                                :validade "2021-03"
                                                                                                                :limite 3000
                                                                                                                :cliente "666.777.888-99"}]
                                                                   {:data "2022-02-01" :valor 400.00 :estabelecimento "Leroy Merlin" :categoria "Casa" :cartao 1598159815981598}
                                                                   ["2022-03-01"	50.00	 "Madero" "Alimentação"	{:numero 6655665566556655
                                                                                                                  :cvv 444
                                                                                                                  :validade "2025-04"
                                                                                                                  :limite 4000
                                                                                                                  :cliente"666.777.888-99"}]
                                                                   {:data "2022-03-01" :valor 50.00	 :estabelecimento "Madero" :categoria "Alimentação" :cartao 6655665566556655}
                                                                   ["2022-03-01"	70.00	 "Teatro" "Lazer"	{:numero 6655665566556655
                                                                                                            :cvv 444
                                                                                                            :validade "2025-04"
                                                                                                            :limite 4000
                                                                                                            :cliente"666.777.888-99"}]
                                                                   {:data "2022-03-01" :valor 70.00 :estabelecimento "Teatro" :categoria "Lazer" :cartao 6655665566556655}
                                                                   ["2022-03-04"	250.00 "Hospital" "Saúde"	{:numero 6655665566556655
                                                                                                             :cvv 444
                                                                                                             :validade "2025-04"
                                                                                                             :limite 4000
                                                                                                             :cliente"666.777.888-99"}]
                                                                   {:data "2022-03-04" :valor 250.00  :estabelecimento "Hospital" :categoria "Saúde" :cartao 6655665566556655}
                                                                   ["2022-04-10"	130.00 "Drogaria"	"Saúde"	{:numero 6655665566556655
                                                                                                              :cvv 444
                                                                                                              :validade "2025-04"
                                                                                                              :limite 4000
                                                                                                              :cliente"666.777.888-99"}]
                                                                   {:data "2022-04-10"	:valor 130.00 :estabelecimento "Drogaria" :categoria "Saúde" :cartao 6655665566556655}
                                                                   ["2022-03-10"	100.00 "Show de pagode" "Lazer"	{:numero 3939393939393939
                                                                                                                   :cvv 555
                                                                                                                   :validade "2026-05"
                                                                                                                   :limite 5000
                                                                                                                   :cliente "999.123.456-78"}]
                                                                   {:data  "2022-03-10"	 :valor 100.00  :estabelecimento "Show de pagode" :categoria "Lazer" :cartao 3939393939393939}
                                                                   ["2022-03-11"	25.90	 "Dogão" "Alimentação"	{:numero 3939393939393939
                                                                                                                 :cvv 555
                                                                                                                 :validade "2026-05"
                                                                                                                 :limite 5000
                                                                                                                 :cliente "999.123.456-78"}]
                                                                   {:data "2022-03-11" :valor 25.90 :estabelecimento "Dogão":categoria "Alimentação":cartao 3939393939393939}
                                                                   ["2022-03-12"	215.87 "Praia" "Lazer"	{:numero 3939393939393939
                                                                                                          :cvv 555
                                                                                                          :validade "2026-05"
                                                                                                          :limite 5000
                                                                                                          :cliente "999.123.456-78"}]
                                                                   {:data "2022-03-12" :valor 215.87 :estabelecimento "Praia" :categoria "Lazer" :cartao 3939393939393939}
                                                                   ["2022-04-01"	976.88 "Oficina" "Automóvel"	{:numero 3939393939393939
                                                                                                                :cvv 555
                                                                                                                :validade "2026-05"
                                                                                                                :limite 5000
                                                                                                                :cliente "999.123.456-78"}]
                                                                   {:data "2022-04-01" :valor 976.88 :estabelecimento "Oficina" :categoria "Automóvel":cartao 3939393939393939}
                                                                   ["2022-04-10"	85.00	 "Alura" "Educação"	{:numero 3939393939393939
                                                                                                              :cvv 555
                                                                                                              :validade "2026-05"
                                                                                                              :limite 5000
                                                                                                              :cliente "999.123.456-78"}]
                                                                   {:data "2022-04-10" :valor 85.00 :estabelecimento  "Alura"  :categoria "Educação" :cartao 3939393939393939}))
  (testing "Testando com cartao invalido"
    (are [parametro-compra] (thrown? Exception (nova-compra parametro-compra))
                                                         ["2022-01-01"	129.90 "Outback" "Alimentação" 1234123412341234]
                                                         ["2022-01-02"	260.00 "Dentista" "Saúde" nil])))

(deftest lista-compras-test
  (testing "Testando lista de compras"
    (is (= (lista-compras [["2022-01-01"	129.90 "Outback" "Alimentação"	{:numero 1234123412341234
                                                                           :cvv 111
                                                                           :validade "2023-01"
                                                                           :limite 1000
                                                                           :cliente "000.111.222-33"}]
                           ["2022-01-02"	260.00 "Dentista" "Saúde"	{:numero 1234123412341234
                                                                      :cvv 111
                                                                      :validade "2023-01"
                                                                      :limite 1000
                                                                      :cliente "000.111.222-33"}]
                           ["2022-02-01"	20.00	 "Cinema" "Lazer"	{:numero 1234123412341234
                                                                     :cvv 111
                                                                     :validade "2023-01"
                                                                     :limite 1000
                                                                     :cliente "000.111.222-33"}]
                           ["2022-01-10"	150.00 "Show" "Lazer"	{:numero 4321432143214321
                                                                  :cvv 222
                                                                  :validade "2024-02"
                                                                  :limite 2000
                                                                  :cliente "333.444.555-66"}]
                           ["2022-02-10"	289.99 "Posto de gasolina" "Automóvel"	{:numero 4321432143214321
                                                                                   :cvv 222
                                                                                   :validade "2024-02"
                                                                                   :limite 2000
                                                                                   :cliente "333.444.555-66"}]
                           ["2022-02-20"	79.90	 "iFood" "Alimentação"	{:numero 4321432143214321
                                                                          :cvv 222
                                                                          :validade "2024-02"
                                                                          :limite 2000
                                                                          :cliente "333.444.555-66"}]
                           ["2022-03-01"	85.00	 "Alura" "Educação"	{:numero 4321432143214321
                                                                       :cvv 222
                                                                       :validade "2024-02"
                                                                       :limite 2000
                                                                       :cliente "333.444.555-66"}]
                           ["2022-01-30"	85.00	 "Alura" "Educação"	{:numero 1598159815981598
                                                                       :cvv 333
                                                                       :validade "2021-03"
                                                                       :limite 3000
                                                                       :cliente "666.777.888-99"}]
                           ["2022-01-31"	350.00 "Tok&Stok" "Casa"	{:numero 1598159815981598
                                                                     :cvv 333
                                                                     :validade "2021-03"
                                                                     :limite 3000
                                                                     :cliente "666.777.888-99"}]
                           ["2022-02-01"	400.00 "Leroy Merlin" "Casa"	{:numero 1598159815981598
                                                                         :cvv 333
                                                                         :validade "2021-03"
                                                                         :limite 3000
                                                                         :cliente "666.777.888-99"}]
                           ["2022-03-01"	50.00	 "Madero" "Alimentação"	{:numero 6655665566556655
                                                                           :cvv 444
                                                                           :validade "2025-04"
                                                                           :limite 4000
                                                                           :cliente"666.777.888-99"}]
                           [ "2022-03-01"	70.00	 "Teatro" "Lazer"	{:numero 6655665566556655
                                                                      :cvv 444
                                                                      :validade "2025-04"
                                                                      :limite 4000
                                                                      :cliente"666.777.888-99"}]
                           ["2022-03-04"	250.00 "Hospital" "Saúde"	{:numero 6655665566556655
                                                                      :cvv 444
                                                                      :validade "2025-04"
                                                                      :limite 4000
                                                                      :cliente"666.777.888-99"}]
                           ["2022-04-10"	130.00 "Drogaria"	"Saúde"	{:numero 6655665566556655
                                                                       :cvv 444
                                                                       :validade "2025-04"
                                                                       :limite 4000
                                                                       :cliente"666.777.888-99"}]
                           ["2022-03-10"	100.00 "Show de pagode" "Lazer"	{:numero 3939393939393939
                                                                            :cvv 555
                                                                            :validade "2026-05"
                                                                            :limite 5000
                                                                            :cliente "999.123.456-78"}]
                           ["2022-03-11"	25.90	 "Dogão" "Alimentação"	{:numero 3939393939393939
                                                                          :cvv 555
                                                                          :validade "2026-05"
                                                                          :limite 5000
                                                                          :cliente "999.123.456-78"}]
                           ["2022-03-12"	215.87 "Praia" "Lazer"	{:numero 3939393939393939
                                                                   :cvv 555
                                                                   :validade "2026-05"
                                                                   :limite 5000
                                                                   :cliente "999.123.456-78"}]
                           ["2022-04-01"	976.88 "Oficina" "Automóvel"	{:numero 3939393939393939
                                                                         :cvv 555
                                                                         :validade "2026-05"
                                                                         :limite 5000
                                                                         :cliente "999.123.456-78"}]
                           ["2022-04-10"	85.00	 "Alura" "Educação"	{:numero 3939393939393939
                                                                       :cvv 555
                                                                       :validade "2026-05"
                                                                       :limite 5000
                                                                       :cliente "999.123.456-78"}]])
                          [{:data "2022-01-01"
                            :valor 129.9
                            :estabelecimento "Outback"
                            :categoria "Alimentação"
                            :cartao 1234123412341234}
                           {:data "2022-01-02"
                            :valor 260.0
                            :estabelecimento "Dentista"
                            :categoria "Saúde"
                            :cartao 1234123412341234}
                           {
                            :data "2022-02-01"
                            :valor 20.0
                            :estabelecimento "Cinema"
                            :categoria "Lazer"
                            :cartao 1234123412341234}
                           {
                            :data "2022-01-10"
                            :valor 150.0
                            :estabelecimento "Show"
                            :categoria "Lazer"
                            :cartao 4321432143214321}
                           {
                            :data "2022-02-10"
                            :valor 289.99
                            :estabelecimento "Posto de gasolina"
                            :categoria "Automóvel"
                            :cartao 4321432143214321}
                           {
                            :data "2022-02-20"
                            :valor 79.9
                            :estabelecimento "iFood"
                            :categoria "Alimentação"
                            :cartao 4321432143214321}
                           {
                            :data "2022-03-01"
                            :valor 85.0
                            :estabelecimento "Alura"
                            :categoria "Educação"
                            :cartao 4321432143214321}
                           {:data "2022-01-30"
                            :valor 85.0
                            :estabelecimento "Alura"
                            :categoria "Educação"
                            :cartao 1598159815981598}
                           {
                            :data "2022-01-31"
                            :valor 350.0
                            :estabelecimento "Tok&Stok"
                            :categoria "Casa"
                            :cartao 1598159815981598}
                           {:data "2022-02-01"
                            :valor 400.0
                            :estabelecimento "Leroy Merlin"
                            :categoria "Casa"
                            :cartao 1598159815981598}
                           {:data "2022-03-01"
                            :valor 50.0
                            :estabelecimento "Madero"
                            :categoria "Alimentação"
                            :cartao 6655665566556655}
                           {:data "2022-03-01"
                            :valor 70.0
                            :estabelecimento "Teatro"
                            :categoria "Lazer"
                            :cartao 6655665566556655}
                           {:data "2022-03-04"
                            :valor 250.0
                            :estabelecimento "Hospital"
                            :categoria "Saúde"
                            :cartao 6655665566556655}
                           {:data "2022-04-10"
                            :valor 130.0
                            :estabelecimento "Drogaria"
                            :categoria "Saúde"
                            :cartao 6655665566556655}
                           {:data "2022-03-10"
                            :valor 100.0
                            :estabelecimento "Show de pagode"
                            :categoria "Lazer"
                            :cartao 3939393939393939}
                           {:data "2022-03-11"
                            :valor 25.9
                            :estabelecimento "Dogão"
                            :categoria "Alimentação"
                            :cartao 3939393939393939}
                           {:data "2022-03-12"
                            :valor 215.87
                            :estabelecimento "Praia"
                            :categoria "Lazer"
                            :cartao 3939393939393939}
                           {:data "2022-04-01"
                            :valor 976.88
                            :estabelecimento "Oficina"
                            :categoria "Automóvel"
                            :cartao 3939393939393939}
                           {:data "2022-04-10"
                            :valor 85.0
                            :estabelecimento "Alura"
                            :categoria "Educação"
                            :cartao 3939393939393939}]))))