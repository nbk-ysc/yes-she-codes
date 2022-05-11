(ns yes_she_codes.db)

(defn novo-cliente
  [nome cpf email]
  {:nome nome
   :cpf cpf
   :email email})

(defn novo-cartao
  [numero cvv validade limite cliente]
  {:numero numero
   :cvv cvv
   :valdiade validade
   :limite limite
   :cliente cliente})

(defn nova-compra
  [data valor estabelecimento categoria cartao]
  {:data data
   :valor valor
   :estabelecimento estabelecimento
   :categoria categoria
   :cartao cartao})

(defn string-to-java-date-completa
  [data]
  (let [format (java.text.SimpleDateFormat. "yyyy-MM-dd")]
    (.parse format data)))

(defn string-to-java-date-parcial
  [data]
  (let [format (java.text.SimpleDateFormat. "yyyy-MM")]
    (.format (java.text.SimpleDateFormat. "yyyy-MM") (.parse format data))))

(defn lista-clientes
  []
  [(novo-cliente "Feiticeira Escarlate" "00011122233" "feiticeira.poderosa@vingadoras.com.br")
   (novo-cliente "Viúva Negra" "33344455566" "viuva.casca.grossa@vingadoras.com.br")
   (novo-cliente "Hermione Granger" "66677788899" "hermione.salvadora@hogwarts.com")
   (novo-cliente "Daenerys Targaryen" "99912345678" "mae.dos.dragoes@got.com")])

(defn lista-cartoes
  []
  [(novo-cartao 1234123412341234 111 (string-to-java-date-parcial "2023-01") 1.000 "00011122233")
   (novo-cartao 4321432143214321 222 (string-to-java-date-parcial "2024-02") 2.000 "33344455566")
   (novo-cartao 1598159815981598 333 (string-to-java-date-parcial "2021-03") 3.000 "66677788899")
   (novo-cartao 6655665566556655 444 (string-to-java-date-parcial "2025-04") 4.000 "66677788899")
   (novo-cartao 3939393939393939 555 (string-to-java-date-parcial "2026-05") 5.000 "99912345678")])

(defn lista-compras
  []
  [(nova-compra (string-to-java-date-completa "2022-01-01") 129.90 "Outback" "Alimentação" 1234123412341234)
   (nova-compra (string-to-java-date-completa "2022-01-02") 260.00 "Dentista" "Saúde" 1234123412341234)
   (nova-compra (string-to-java-date-completa "2022-02-01") 20.00 "Cinema" "Lazer" 1234123412341234)
   (nova-compra (string-to-java-date-completa "2022-01-10") 150.00 "Show" "Lazer" 4321432143214321)
   (nova-compra (string-to-java-date-completa "2022-02-10") 289.99 "Posto de gasolina" "Automóvel" 4321432143214321)
   (nova-compra (string-to-java-date-completa "2022-02-20") 79.90 "iFood" "Alimentação" 4321432143214321)
   (nova-compra (string-to-java-date-completa "2022-03-01") 85.00 "Alura" "Educação" 4321432143214321)
   (nova-compra (string-to-java-date-completa "2022-01-30") 85.00 "Alura" "Educação" 1598159815981598)
   (nova-compra (string-to-java-date-completa "2022-01-31") 350.00 "Tok&Stok" "Casa" 1598159815981598)
   (nova-compra (string-to-java-date-completa "2022-02-01") 400.00 "Leroy Merlin" "Casa" 1598159815981598)
   (nova-compra (string-to-java-date-completa "2022-03-01") 50.00 "Madero" "Alimentação" 6655665566556655)
   (nova-compra (string-to-java-date-completa "2022-03-01") 70.00 "Teatro" "Lazer" 6655665566556655)
   (nova-compra (string-to-java-date-completa "2022-03-04") 250.00 "Hospital" "Saúde" 6655665566556655)
   (nova-compra (string-to-java-date-completa "2022-04-10") 130.00 "Drogaria" "Saúde" 6655665566556655)
   (nova-compra (string-to-java-date-completa "2022-03-10") 100.00 "Show de pagode" "Lazer" 3939393939393939)
   (nova-compra (string-to-java-date-completa "2022-03-11") 25.90 "Dogão" "Alimentação" 3939393939393939)
   (nova-compra (string-to-java-date-completa "2022-03-12") 215.87 "Praia" "Lazer" 3939393939393939)
   (nova-compra (string-to-java-date-completa "2022-04-01") 976.88 "Oficina" "Automóvel" 3939393939393939)
   (nova-compra (string-to-java-date-completa "2022-04-10") 85.00 "Alura" "Educação" 3939393939393939)])