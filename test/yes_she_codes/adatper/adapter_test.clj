(ns yes-she-codes.adatper.adapter-test
  (:require [clojure.test :as t]
            [yes-she-codes.input.hard-code :as hc]
            [yes-she-codes.input.csv-reader :as r]))


(t/deftest lista-clientes-test
  (let [lista-esperada
        [ {:nome "Feiticeira Escarlate" :cpf "000.111.222-33" :email "feiticeira.poderosa@vingadoras.com.br"}
         {:nome "Viúva Negra"  :cpf "333.444.555-66" :email "viuva.casca.grossa@vingadoras.com.br"}
         {:nome "Hermione Granger" :cpf "666.777.888-99" :email "hermione.salvadora@hogwarts.com"}
         {:nome "Daenerys Targaryen" :cpf "999.123.456-78" :email "mae.dos.dragoes@got.com"}]]
    (t/testing "retorno da lista esperada"
      (t/is (= (hc/lista-clientes) lista-esperada))
      (t/is (= (r/lista-clientes) lista-esperada))
      )))


(t/deftest lista-cartoes-test
  (let [lista-esperada
        [{:numero 1234123412341234, :cvv 111, :validade "2023-01", :limite 1000M, :cliente "000.111.222-33"}
         {:numero 4321432143214321, :cvv 222, :validade "2024-02", :limite 2000M, :cliente "333.444.555-66"}
         {:numero 1598159815981598, :cvv 333, :validade "2021-03", :limite 3000M, :cliente "666.777.888-99"}
         {:numero 6655665566556655, :cvv 444, :validade "2025-04", :limite 4000M, :cliente "666.777.888-99"}
         {:numero 3939393939393939, :cvv 555, :validade "2026-05", :limite 5000M, :cliente "999.123.456-78"}]]
    (t/testing "retorno da lista esperada"
      (t/is (= (hc/lista-cartoes) lista-esperada))
      (t/is (= (r/lista-cartoes) lista-esperada))
      )))


(t/deftest lista-compras-test
  (let [lista-esperada
        [{:data "2022-01-01", :valor 129.90M, :estabelecimento "Outback", :categoria "Alimentação", :cartão 1234123412341234}
         {:data "2022-01-02", :valor 260.00M, :estabelecimento "Dentista", :categoria "Saúde", :cartão 1234123412341234}
         {:data "2022-02-01", :valor 20.00M, :estabelecimento "Cinema", :categoria "Lazer", :cartão 1234123412341234}
         {:data "2022-01-10", :valor 150.00M, :estabelecimento "Show", :categoria "Lazer", :cartão 4321432143214321}
         {:data "2022-02-10", :valor 289.99M, :estabelecimento "Posto de gasolina", :categoria "Automóvel", :cartão 4321432143214321}
         {:data "2022-02-20", :valor 79.90M, :estabelecimento "iFood", :categoria "Alimentação", :cartão 4321432143214321}
         {:data "2022-03-01", :valor 85.00M, :estabelecimento "Alura", :categoria "Educação", :cartão 4321432143214321}
         {:data "2022-01-30", :valor 85.00M, :estabelecimento "Alura", :categoria "Educação", :cartão 1598159815981598}
         {:data "2022-01-31", :valor 350.00M, :estabelecimento "Tok&Stok", :categoria "Casa", :cartão 1598159815981598}
         {:data "2022-02-01", :valor 400.00M, :estabelecimento "Leroy Merlin", :categoria "Casa", :cartão 1598159815981598}
         {:data "2022-03-01", :valor 50.00M, :estabelecimento "Madero", :categoria "Alimentação", :cartão 6655665566556655}
         {:data "2022-03-01", :valor 70.00M, :estabelecimento "Teatro", :categoria "Lazer", :cartão 6655665566556655}
         {:data "2022-03-04", :valor 250.00M, :estabelecimento "Hospital", :categoria "Saúde", :cartão 6655665566556655}
         {:data "2022-04-10", :valor 130.00M, :estabelecimento "Drogaria", :categoria "Saúde", :cartão 6655665566556655}
         {:data "2022-03-10", :valor 100.00M, :estabelecimento "Show de pagode", :categoria "Lazer", :cartão 3939393939393939}
         {:data "2022-03-11", :valor 25.90M, :estabelecimento "Dogão", :categoria "Alimentação", :cartão 3939393939393939}
         {:data "2022-03-12", :valor 215.87M, :estabelecimento "Praia", :categoria "Lazer", :cartão 3939393939393939}
         {:data "2022-04-01", :valor 976.88M, :estabelecimento "Oficina", :categoria "Automóvel", :cartão 3939393939393939}
         {:data "2022-04-10", :valor 85.00M, :estabelecimento "Alura", :categoria "Educação", :cartão 3939393939393939}]]
    (t/testing "retorno da lista esperada"
      (t/is (= (hc/lista-compras) lista-esperada))
      (t/is (= (r/lista-compras) lista-esperada))
      )))


