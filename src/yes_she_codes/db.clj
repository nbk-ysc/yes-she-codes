(ns yes-she-codes.db)


(def clientes [["feiticeira escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"]
               ["Viúva Negra" "333.444.555-66" "viuva.casca.grossa@vingadoras.com.br"]
               ["Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"]
               ["Daenerys Targaryen" "999.123.456-78" "mae.dos.dragoes@got.com"]])

(def cartoes [["1234 1234 1234 1234" 111 1000 "12/2012" "000.111.222-33"]
              ["4321 4321 4321 4321" 222 2000 "12/2012" "333.444.555-66"]
              ["1598 1598 1598 1598" 333 3000 "12/2012" "666.777.888-99"]
              ["6655 6655 6655 6655" 444 4000 "12/2012" "666.777.888-99"]
              ["3939 3939 3939 3939" 555 5000 "12/2012" "999.123.456-78"]])

(def compras [["2022-01-01" 129.90 "Outback" "Alimentacao" "1234 1234 1234 1234"]
              ["2022-01-01" 129.90 "Dentista" "Saude" "1234 1234 1234 1234"]
              ["2022-02-01" 129.90 "Cinema" "Lazer" "1234 1234 1234 1234"]
              ["2022-04-01" 129.90 "Show" "Lazer" "1234 1234 1234 1234"]
              ["2022-04-01" 129.90 "Posto de Gasolina" "Automovel" "1234 1234 1234 1234"]
              ["2022-04-01" 129.90 "Ifood" "Alimentacao" "1234 1234 1234 1234"]
              ["2022-04-01" 129.90 "Alura" "Educacao" "1234 1234 1234 1234"]
              ["2022-04-01" 129.90 "Tok&Stock" "Casa" "1234 1234 1234 1234"]
              ["2022-04-01" 129.90 "Leroy Merlin" "Casa" "1234 1234 1234 1234"]
              ["2022-04-01" 129.90 "Madero" "Alimentacao" "1234 1234 1234 1234"]])


(defn param-clientes [] clientes)

(defn param-cartoes [] cartoes)

(defn param-compras [] compras)