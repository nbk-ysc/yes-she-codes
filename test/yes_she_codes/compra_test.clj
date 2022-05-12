(ns yes-she-codes.compra-test
  (:require [clojure.test :refer :all]
            [yes-she-codes.compra :refer :all]
            [yes-she-codes.cartao :refer :all]
            [yes-she-codes.cliente :refer :all]
            [java-time :as t]))

(deftest nova-compra-test
  (testing "Testando nova compra multiplas vezes"
    (are [parametro-compra esperado] (= esperado (nova-compra parametro-compra))
                                     ["2022-01-01"	"129.90" "Outback" "Alimentação" 1234123412341234]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-01-01") :valor 129.90 :estabelecimento "outback" :categoria "alimentação" :cartao 1234123412341234}
                                     ["2022-01-02"	"260.00" "Dentista" "Saúde" 1234123412341234]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-01-02") :valor 260.00 :estabelecimento "dentista" :categoria "saúde" :cartao 1234123412341234}
                                     ["2022-02-01"	"20.00"	 "Cinema" "Lazer" 1234123412341234]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-02-01") :valor 20.00 :estabelecimento "cinema" :categoria "lazer" :cartao 1234123412341234}
                                     ["2022-01-10"	"150.00" "Show" "Lazer" 4321432143214321]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-01-10") :valor 150.00 :estabelecimento "show" :categoria "lazer" :cartao 4321432143214321}
                                     ["2022-02-10"	"289.99" "Posto de gasolina" "Automóvel" 4321432143214321]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-02-10") :valor 289.99 :estabelecimento "posto de gasolina" :categoria "automóvel" :cartao 4321432143214321}
                                     ["2022-02-20"	"79.90"	 "iFood" "Alimentação" 4321432143214321]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-02-20") :valor 	79.90	 :estabelecimento "ifood"  :categoria "alimentação" :cartao 4321432143214321}
                                     ["2022-03-01"	"85.00"	 "Alura" "Educação" 4321432143214321]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-03-01") :valor 85.00 :estabelecimento "alura" :categoria "educação" :cartao 4321432143214321}
                                     ["2022-01-30"	"85.00"	 "Alura" "Educação" 1598159815981598]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-01-30") :valor 85.00 :estabelecimento "alura" :categoria "educação" :cartao 1598159815981598}
                                     ["2022-01-31"	"350.00" "Tok&Stok" "Casa" 1598159815981598]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-01-31") :valor 350.00 :estabelecimento "tok&stok" :categoria "casa" :cartao 1598159815981598}
                                     ["2022-02-01"	"400.00" "Leroy Merlin" "Casa" 1598159815981598]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-02-01") :valor 400.00 :estabelecimento "leroy merlin" :categoria "casa" :cartao 1598159815981598}
                                     ["2022-03-01"	"50.00"	 "Madero" "Alimentação" 6655665566556655]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-03-01") :valor 50.00	 :estabelecimento "madero" :categoria "alimentação" :cartao 6655665566556655}
                                     ["2022-03-01"	"70.00"	 "Teatro" "Lazer" 6655665566556655]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-03-01") :valor 70.00 :estabelecimento "teatro" :categoria "lazer" :cartao 6655665566556655}
                                     ["2022-03-04"	"250.00" "Hospital" "Saúde" 6655665566556655]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-03-04") :valor 250.00  :estabelecimento "hospital" :categoria "saúde" :cartao 6655665566556655}
                                     ["2022-04-10"	"130.00" "Drogaria"	"Saúde" 6655665566556655]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-04-10")	:valor 130.00 :estabelecimento "drogaria" :categoria "saúde" :cartao 6655665566556655}
                                     ["2022-03-10"	"100.00" "Show de pagode" "Lazer" 3939393939393939]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-03-10")	 :valor 100.00  :estabelecimento "show de pagode" :categoria "lazer" :cartao 3939393939393939}
                                     ["2022-03-11"	"25.90"	 "Dogão" "Alimentação" 3939393939393939]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-03-11") :valor 25.90 :estabelecimento "dogão":categoria "alimentação":cartao 3939393939393939}
                                     ["2022-03-12"	"215.87" "Praia" "Lazer" 3939393939393939]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-03-12") :valor 215.87 :estabelecimento "praia" :categoria "lazer" :cartao 3939393939393939}
                                     ["2022-04-01"	"976.88" "Oficina" "Automóvel" 3939393939393939]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-04-01") :valor 976.88 :estabelecimento "oficina" :categoria "automóvel":cartao 3939393939393939}
                                     ["2022-04-10"	"85.00"	 "Alura" "Educação" 3939393939393939]
                                     {:data (t/local-date "yyyy-MM-dd" "2022-04-10") :valor 85.00 :estabelecimento  "alura"  :categoria "educação" :cartao 3939393939393939}))
  (testing "Testando com cartao invalido"
    (are [parametro-compra] (thrown? Exception (nova-compra parametro-compra))
                            ["2022-01-02"	260.00 "Dentista" "Saúde" nil])))

(deftest lista-compras-test
  (testing "Testando lista de compras"
    (is (= (lista-compras "arquivos/compras.csv")
           [{:data (t/local-date "yyyy-MM-dd" "2022-01-01")
             :valor 129.9
             :estabelecimento "outback"
             :categoria "alimentação"
             :cartao 1234123412341234}
            {:data (t/local-date "yyyy-MM-dd" "2022-01-02")
             :valor 260.0
             :estabelecimento "dentista"
             :categoria "saúde"
             :cartao 1234123412341234}
            {:data (t/local-date "yyyy-MM-dd" "2022-02-01")
             :valor 20.0
             :estabelecimento "cinema"
             :categoria "lazer"
             :cartao 1234123412341234}
            {:data (t/local-date "yyyy-MM-dd" "2022-01-10")
             :valor 150.0
             :estabelecimento "show"
             :categoria "lazer"
             :cartao 4321432143214321}
            {:data (t/local-date "yyyy-MM-dd" "2022-02-10")
             :valor 289.99
             :estabelecimento "posto de gasolina"
             :categoria "automóvel"
             :cartao 4321432143214321}
            {:data (t/local-date "yyyy-MM-dd" "2022-02-20")
             :valor 79.9
             :estabelecimento "ifood"
             :categoria "alimentação"
             :cartao 4321432143214321}
            {:data (t/local-date "yyyy-MM-dd" "2022-03-01")
             :valor 85.0
             :estabelecimento "alura"
             :categoria "educação"
             :cartao 4321432143214321}
            {:data (t/local-date "yyyy-MM-dd" "2022-01-30")
             :valor 85.0
             :estabelecimento "alura"
             :categoria "educação"
             :cartao 1598159815981598}
            {:data (t/local-date "yyyy-MM-dd" "2022-01-31")
             :valor 350.0
             :estabelecimento "tok&stok"
             :categoria "casa"
             :cartao 1598159815981598}
            {:data (t/local-date "yyyy-MM-dd" "2022-02-01")
             :valor 400.0
             :estabelecimento "leroy merlin"
             :categoria "casa"
             :cartao 1598159815981598}
            {:data (t/local-date "yyyy-MM-dd" "2022-03-01")
             :valor 50.0
             :estabelecimento "madero"
             :categoria "alimentação"
             :cartao 6655665566556655}
            {:data (t/local-date "yyyy-MM-dd" "2022-03-01")
             :valor 70.0
             :estabelecimento "teatro"
             :categoria "lazer"
             :cartao 6655665566556655}
            {:data (t/local-date "yyyy-MM-dd" "2022-03-04")
             :valor 250.0
             :estabelecimento "hospital"
             :categoria "saúde"
             :cartao 6655665566556655}
            {:data (t/local-date "yyyy-MM-dd" "2022-04-10")
             :valor 130.0
             :estabelecimento "drogaria"
             :categoria "saúde"
             :cartao 6655665566556655}
            {:data (t/local-date "yyyy-MM-dd" "2022-03-10")
             :valor 100.0
             :estabelecimento "show de pagode"
             :categoria "lazer"
             :cartao 3939393939393939}
            {:data (t/local-date "yyyy-MM-dd" "2022-03-11")
             :valor 25.9
             :estabelecimento "dogão"
             :categoria "alimentação"
             :cartao 3939393939393939}
            {:data (t/local-date "yyyy-MM-dd" "2022-03-12")
             :valor 215.87
             :estabelecimento "praia"
             :categoria "lazer"
             :cartao 3939393939393939}
            {:data (t/local-date "yyyy-MM-dd" "2022-04-01")
             :valor 976.88
             :estabelecimento "oficina"
             :categoria "automóvel"
             :cartao 3939393939393939}
            {:data (t/local-date "yyyy-MM-dd" "2022-04-10")
             :valor 85.0
             :estabelecimento "alura"
             :categoria "educação"
             :cartao 3939393939393939}]))))