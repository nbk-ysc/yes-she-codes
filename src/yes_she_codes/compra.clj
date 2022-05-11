(ns yes-she-codes.compra)

(defn nova-compra [parametro-compra]
  (if-let [[data valor	estabelecimento	categoria	cartao] parametro-compra]
    (if-let [numero (:numero cartao)]
      {:data data
       :valor valor
       :estabelecimento estabelecimento
       :categoria categoria
       :cartao numero
       }
      (throw (ex-info "Cartao invalido" {:cartao cartao})))
    (throw (ex-info "Compra invalida" {:compra parametro-compra}))))

(defn lista-compras [parametros-compras]
  (map nova-compra parametros-compras))

(println (lista-compras [["2022-01-01"	129.90 "Outback" "Alimentação"	{:numero 1234123412341234
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
                                                                     :cliente "999.123.456-78"}]] ))