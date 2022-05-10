(ns yes-she-codes.db)

; Clientes

(def cliente1 {:nome  "Feiticeira Escarlate"
               :cpf   "000.111.222-33"
               :email "feiticeira.poderosa@vingadoras.com.br"})

(def cliente2 {:nome "Viúva Negra"
               :cpf "333.444.555-66"
               :email "viuva.casca.grossa@vingadoras.com.br"})

(def cliente3 {:nome "Hermione Granger"
               :cpf "666.777.888-99"
               :email "hermione.salvadora@hogwarts.com"})

(def cliente4 {:nome "Daenerys Targaryen"
               :cpf "999.123.456-78"
               :email "mae.dos.dragoes@got.com"})


; Cartões

(def cartao1 {:numero   1234123412341234
              :cvv      111
              :validade "2023-01"
              :limite   (new BigDecimal 1000)
              :cliente  "000.111.222-33"})

(def cartao2 {:numero   4321432143214321
              :cvv      222
              :validade "2024-02"
              :limite   (new BigDecimal 2000)
              :cliente  "333.444.555-66"})

(def cartao3 {:numero   1598159815981598
              :cvv      333
              :validade "2021-03"
              :limite   (new BigDecimal 3000)
              :cliente  "666.777.888-99"})

(def cartao4 {:numero   6655665566556655
              :cvv      444
              :validade "2025-04"
              :limite   (new BigDecimal 4000)
              :cliente  "666.777.888-99"})

(def cartao5 {:numero   3939393939393939
              :cvv      555
              :validade "2026-05"
              :limite   (new BigDecimal 5000)
              :cliente  "999.123.456-78"})


; Compras

(def compra1 {:data            "2022-01-01"
              :valor           (new BigDecimal 129.90)
              :estabelecimento "Outback"
              :categoria       "Alimentação"
              :cartao          1234123412341234})

(def compra2 {:data            "2022-01-02"
              :valor           (new BigDecimal 260.00)
              :estabelecimento "Dentista"
              :categoria       "Saúde"
              :cartao          1234123412341234})

(def compra3 {:data            "2022-02-01"
              :valor           (new BigDecimal 20.00)
              :estabelecimento "Cinema"
              :categoria       "Lazer"
              :cartao          1234123412341234})

(def compra4 {:data            "2022-01-10"
              :valor           (new BigDecimal 150.00)
              :estabelecimento "Show"
              :categoria       "Lazer"
              :cartao          4321432143214321})

(def compra5 {:data            "2022-02-10"
              :valor           (new BigDecimal 289.99)
              :estabelecimento "Posto de gasolina"
              :categoria       "Automóvel"
              :cartao          4321432143214321})

(def compra6 {:data            "2022-02-20"
              :valor           (new BigDecimal 79.90)
              :estabelecimento "iFood"
              :categoria       "Alimentação"
              :cartao          4321432143214321})

(def compra7 {:data            "2022-03-01"
              :valor           (new BigDecimal 85.00)
              :estabelecimento "Alura"
              :categoria       "Educação"
              :cartao          4321432143214321})

(def compra8 {:data            "2022-01-30"
              :valor           (new BigDecimal 85.00)
              :estabelecimento "Alura"
              :categoria       "Educação"
              :cartao          1598159815981598})

(def compra9 {:data            "2022-01-31"
              :valor           (new BigDecimal 350.00)
              :estabelecimento "Tok&Stok"
              :categoria       "Casa"
              :cartao          1598159815981598})

(def compra10 {:data           "2022-02-01"
              :valor           (new BigDecimal 400.00)
              :estabelecimento "Leroy Merlin"
              :categoria       "Casa"
              :cartao          1598159815981598})

(def compra11 {:data           "2022-03-01"
               :valor           (new BigDecimal 50.00)
               :estabelecimento "Madero"
               :categoria       "Alimentação"
               :cartao          6655665566556655})

(def compra12 {:data           "2022-03-01"
               :valor           (new BigDecimal 70.00)
               :estabelecimento "Teatro"
               :categoria       "Lazer"
               :cartao          6655665566556655})

(def compra13 {:data           "2022-03-04"
               :valor           (new BigDecimal 250.00)
               :estabelecimento "Hospital"
               :categoria       "Saúde"
               :cartao          6655665566556655})

(def compra14 {:data           "2022-04-10"
               :valor           (new BigDecimal 130.00)
               :estabelecimento "Drogaria"
               :categoria       "Saúde"
               :cartao          6655665566556655})

(def compra15 {:data           "2022-03-10"
               :valor           (new BigDecimal 100.00)
               :estabelecimento "Show de pagode"
               :categoria       "Lazer"
               :cartao          3939393939393939})

(def compra16 {:data           "2022-03-11"
               :valor           (new BigDecimal 25.90)
               :estabelecimento "Dogão"
               :categoria       "Alimentação"
               :cartao          3939393939393939})

(def compra17 {:data           "2022-03-12"
               :valor           (new BigDecimal 215.87)
               :estabelecimento "Praia"
               :categoria       "Lazer"
               :cartao          3939393939393939})

(def compra18 {:data           "2022-04-01"
               :valor           (new BigDecimal 976.88)
               :estabelecimento "Oficina"
               :categoria       "Automóvel"
               :cartao          3939393939393939})

(def compra19 {:data           "2022-04-10"
               :valor           (new BigDecimal 85.00)
               :estabelecimento "Alura"
               :categoria       "Educação"
               :cartao          3939393939393939})






(defn lista-clientes []
  [cliente1, cliente2, cliente3, cliente4])

(defn lista-cartoes []
  [cartao1, cartao2, cartao3, cartao4, cartao5])

(defn lista-compras []
  [compra1, compra2, compra3, compra4, compra5,
   compra6, compra7, compra8, compra9, compra10,
   compra11, compra12, compra13, compra14, compra15,
   compra16, compra17, compra18, compra19])










