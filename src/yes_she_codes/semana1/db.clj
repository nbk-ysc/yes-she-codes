(ns yes-she-codes.semana1.db
  (:require
    [yes-she-codes.utilities.logica :as y.utilities ]))

(defn novo-cliente
  [nome cpf email]
  {:nome nome
   :CPF cpf
   :email email})

(defn novo-cartao
  [numero CVV  validade limite cliente]
  {:numero   (y.utilities/str->long numero)
   :CVV      (y.utilities/str->long CVV)
   :validade validade
   :limite   (bigdec limite)
   :cliente  cliente})

(defn nova-compra
  [data valor estabelecimento categoria cartao]
  {:data            data
   :valor           (bigdec valor)
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          (y.utilities/str->long cartao)})


(def cliente1 (novo-cliente "Feiticeira Escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"))
(def cliente2 (novo-cliente "Viúva Negra" "333.444.555-66" "viuva.casca.grossa@vingadoras.com.br"))
(def cliente3 (novo-cliente "Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"))
(def cliente4 (novo-cliente "Daenerys Targaryen" "999.123.456-78" "mae.dos.dragoes@got.com"))


(def cartao1 (novo-cartao 1234123412341234 111 "2023-01" 1.000M "000.111.222-33"))
(def cartao2 (novo-cartao 4321432143214321 222 "2024-02" 2.000M "333.444.555-66"))
(def cartao3 (novo-cartao 1598159815981598 333 "2021-03" 3.000M "666.777.888-99"))
(def cartao4 (novo-cartao 6655665566556655 444 "2025-04" 4.000M "666.777.888-99"))
(def cartao5 (novo-cartao 3939393939393939 555 "2026-05" 5.000M "999.123.456-78"))


(def compra1 (nova-compra "2022-01-01"  129.90M "Outback"  "Alimentação"  1234123412341234))
(def compra2 (nova-compra "2022-01-02"  260.00M "Dentista" "Saúde"        1234123412341234))
(def compra3 (nova-compra "2022-02-01"  20.00M "Cinema"   "Lazer"        1234123412341234))

(def compra4 (nova-compra "2022-01-10"  150.00M "Show"              "Lazer"       4321432143214321))
(def compra5 (nova-compra "2022-02-10"  289.99M "Posto de gasolina" "Automóvel"   4321432143214321))
(def compra6 (nova-compra "2022-02-20"  79.90M  "iFood"             "Alimentação" 4321432143214321))
(def compra7 (nova-compra "2022-03-01"  85.00M  "Alura"             "Educação"    4321432143214321))

(def compra8  (nova-compra "2022-01-30"  85.00M   "Alura"         "Educação"  1598159815981598))
(def compra9  (nova-compra "2022-01-31"  350.00M  "Tok&Stok"      "Casa"      1598159815981598))
(def compra10 (nova-compra "2022-02-01"  400.00M  "Leroy Merlin"  "Casa"      1598159815981598))

(def compra11 (nova-compra "2022-03-01"  50.00M   "Madero"      "Educação"  6655665566556655))
(def compra12 (nova-compra "2022-03-01"  70.00M   "Teatro"      "Casa"      6655665566556655))
(def compra13 (nova-compra "2022-03-04"  250.00M  "Hospital"    "Casa"      6655665566556655))
(def compra14 (nova-compra "2022-04-10"  130.00M  "Drogaria"    "Casa"      6655665566556655))

(def compra15 (nova-compra "2022-03-10"  100.00M    "Show de pagode"  "Lazer"         3939393939393939))
(def compra16 (nova-compra "2022-03-11"  25.90M     "Dogão"           "Alimentação"   3939393939393939))
(def compra17 (nova-compra "2022-03-12"  215.87M    "Praia"           "Lazer"         3939393939393939))
(def compra18 (nova-compra "2022-04-01"  976.880M   "Oficina"         "Automóvel"     3939393939393939))
(def compra19 (nova-compra "2022-04-10"  85.00M     "Alura"            "Educação"     3939393939393939))

(defn lista-clientes []
 [cliente1 cliente2 cliente3 cliente4])

(defn lista-cartoes []
 [cartao1 cartao2 cartao3 cartao4 cartao5])

(defn lista-compras []
 [compra1 compra2 compra3 compra4 compra5 compra6 compra7 compra8 compra9 compra10 compra11 compra12 compra13 compra14 compra15 compra16 compra17 compra18 compra19])