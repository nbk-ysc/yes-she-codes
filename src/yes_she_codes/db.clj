(ns yes-she-codes.db)

(def cliente1 {:nome "Feiticeira Escarlate"
               :cpf "000.111.222-33"
               :email "feiticeira.poderosa@vingadoras.com.br"
               })

(def cliente2 {:nome "Viúva Negra"
               :cpf "333.444.555-66"
               :email "viuva.casca.grossa@vingadoras.com.br"
               })

(def cliente3 {:nome "Hermione Granger"
               :cpf "666.777.888-99"
               :email "hermione.salvadora@hogwarts.com"
               })

(def cliente4 {:nome "Daenerys Targaryen"
               :cpf "999.123.456-78"
               :email "mae.dos.dragoes@got.com"
               })

(defn todos-clientes []
  [cliente1 cliente2 cliente3 cliente4])

(def cartao1 {:numero 1234123412341234
              :cvv 111
              :validade "2023-01"
              :limite 1000
              :cliente "000.111.222-33"
              })

(def cartao2 {:numero 4321432143214321
              :cvv 222
              :validade "2024-02"
              :limite 2000
              :cliente "333.444.555-66"
              })

(defn todos-cartoes[]
  [cartao1 cartao2])

(def compra1 {:data "2022-01-01"
             :valor 129.90
             :estabelecimento "Outback"
             :categoria "Alimentação"
             :cartao 1234123412341234
             })

(def compra2 {:data "2022-01-02"
              :valor 260.00
              :estabelecimento "Dentista"
              :categoria "Saúde"
              :cartao 1234123412341234
              })
(def compra3 {:data "2022-02-01"
              :valor 20.00
              :estabelecimento "Cinema"
              :categoria "Lazer"
              :cartao 1234123412341234
              })

(def compra7 {:data "2022-03-01"
              :valor 85.00
              :estabelecimento "Alura"
              :categoria "Educação"
              :cartao 4321432143214321
              })
(def compra8 {:data "2022-01-30"
              :valor 85.00
              :estabelecimento "Alura"
              :categoria "Educação"
              :cartao 1598159815981598
              })

(defn todas-compras[]
  [compra1 compra2 compra3 compra7 compra8])