(ns yes-she-codes.simulador-test
  (:require [clojure.test :refer :all]
            [yes-she-codes.simulador :refer :all]))

(deftest total-gasto-test
  (testing "Testando total gasto"
    (is (= (total-gasto [{:valor 100.5}
                         {:valor 250}
                         {:valor 400}]) 750.50))))

(deftest busca-compras-mes-test
  (testing "Testando buscar compras por mes"
    (is (= (busca-compras-mes "2022-01" [{:valor 100.5 :data "2022-01-01"}
                               {:valor 250 :data "2022-03-02"}
                               {:valor 400 :data "2022-01-02"}])
           [{:data  "2022-01-01"
             :valor 100.5}
            {:data  "2022-01-02"
             :valor 400}] ))))

(deftest busca-compras-estabelecimento-test
  (testing "Testando buscar compras por estabelecimento"
    (is (= (busca-compras-estabelecimento  "praia" [{:valor 100.5 :data "2022-01-01" :estabelecimento "praia"}
                               {:valor 250 :data "2022-03-02" :estabelecimento "Oficina"}
                               {:valor 400 :data "2022-01-02" :estabelecimento "Praia"}])
           [{:data  "2022-01-01"
             :valor 100.5
             :estabelecimento "praia"}
            {:data  "2022-01-02"
             :valor 400
             :estabelecimento "Praia"}] ))))

(deftest total-gasto-no-mes-test
  (testing "Testando total gasto no mes"
    (is (= (total-gasto-no-mes 1234123412341234 "2022-01" [{:valor 100.5 :data "2022-01-01" :estabelecimento "praia" :cartao 1234123412341234}
                                                           {:valor 250 :data "2022-03-02" :estabelecimento "Oficina" :cartao 4321432143214321}
                                                           {:valor 400 :data "2022-01-02" :estabelecimento "Praia" :cartao 1234123412341234}])
           500.5 ))))

(deftest intervalo-compras-test
  (testing "Testando intervalo de valor de compras"
    (is (= (intervalo-compras 100 300 [{:valor 100.5 :data "2022-01-01" :estabelecimento "praia" :cartao 1234123412341234}
                                       {:valor 250 :data "2022-03-02" :estabelecimento "Oficina" :cartao 4321432143214321}
                                       {:valor 400 :data "2022-01-02" :estabelecimento "Praia" :cartao 1234123412341234}])
           [{:valor 100.5 :data "2022-01-01" :estabelecimento "praia" :cartao 1234123412341234}
            {:valor 250 :data "2022-03-02" :estabelecimento "Oficina" :cartao 4321432143214321}]))))

(deftest gasto-categoria-test
  (testing "Testando categoria por teste"
    (is (= (gasto-categoria [{:data "2022-01-01"
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
                              :cartao 3939393939393939}])
           [{:categoria "Alimentação"
             :total 285.7}
            {:categoria "Saúde"
             :total 640.0}
            {:categoria "Lazer"
             :total 555.87}
            {:categoria "Automóvel"
             :total 1266.87}
            {:categoria "Educação"
             :total 255.0}
            {:categoria "Casa"
             :total 750.0}]
           ))))