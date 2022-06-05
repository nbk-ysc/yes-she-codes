(ns yes-she-codes.study.week1.input.hard-code
  (:require [yes-she-codes.study.week1.adapter.adapter :as a]))


(let [massa-clientes [["Feiticeira Escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"]
                      ["Viúva Negra" "333.444.555-66" "viuva.casca.grossa@vingadoras.com.br"]
                      ["Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"]
                      ["Daenerys Targaryen" "999.123.456-78" "mae.dos.dragoes@got.com"]]
      massa-cartoes [[1234123412341234 111 "2023-01" 1000M "000.111.222-33"]
                     [4321432143214321 222 "2024-02" 2000M "333.444.555-66"]
                     [1598159815981598 333 "2021-03" 3000M "666.777.888-99"]
                     [6655665566556655 444 "2025-04" 4000M "666.777.888-99"]
                     [3939393939393939 555 "2026-05" 5000M "999.123.456-78"]]
      massa-compras [["2022-01-01" 129.90M "Outback" "Alimentação" 1234123412341234]
                     ["2022-01-02" 260.00M "Dentista" "Saúde" 1234123412341234]
                     ["2022-02-01" 20.00M "Cinema" "Lazer" 1234123412341234]
                     ["2022-01-10" 150.00M "Show" "Lazer" 4321432143214321]
                     ["2022-02-10" 289.99M "Posto de gasolina" "Automóvel" 4321432143214321]
                     ["2022-02-20" 79.90M "iFood" "Alimentação" 4321432143214321]
                     ["2022-03-01" 85.00M "Alura" "Educação" 4321432143214321]
                     ["2022-01-30" 85.00M "Alura" "Educação" 1598159815981598]
                     ["2022-01-31" 350.00M "Tok&Stok" "Casa" 1598159815981598]
                     ["2022-02-01" 400.00M "Leroy Merlin" "Casa" 1598159815981598]
                     ["2022-03-01" 50.00M "Madero" "Alimentação" 6655665566556655]
                     ["2022-03-01" 70.00M "Teatro" "Lazer" 6655665566556655]
                     ["2022-03-04" 250.00M "Hospital" "Saúde" 6655665566556655]
                     ["2022-04-10" 130.00M "Drogaria" "Saúde" 6655665566556655]
                     ["2022-03-10" 100.00M "Show de pagode" "Lazer" 3939393939393939]
                     ["2022-03-11" 25.90M "Dogão" "Alimentação" 3939393939393939]
                     ["2022-03-12" 215.87M "Praia" "Lazer" 3939393939393939]
                     ["2022-04-01" 976.88M "Oficina" "Automóvel" 3939393939393939]
                     ["2022-04-10" 85.00M "Alura" "Educação" 3939393939393939]]]


  (defn lista-clientes []
    (mapv a/criar-cliente massa-clientes))

  (defn lista-cartoes []
    (mapv a/criar-cartao massa-cartoes))

  (defn lista-compras []
    (mapv a/criar-compra massa-compras )))