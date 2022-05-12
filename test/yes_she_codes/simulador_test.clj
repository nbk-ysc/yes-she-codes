(ns yes-she-codes.simulador-test
  (:require [clojure.test :refer :all]
            [yes-she-codes.simulador :refer :all]
            [yes-she-codes.compra :refer :all]
            [yes-she-codes.cartao :refer :all]
            [yes-she-codes.cliente :refer :all]
            [java-time :as t]))

(deftest total-gasto-test
  (testing "Testando total gasto"
    (is (= (total-gasto (lista-compras "arquivos/compras.csv"))
           3753.44))))

(deftest busca-compras-mes-test
  (testing "Testando buscar compras por mes"
    (is (= (busca-compras-mes  "2022-01" (lista-compras "arquivos/compras.csv"))

           [{:cartao          1234123412341234
             :categoria       "alimentação"
             :data            (t/local-date "2022-01-01")
             :estabelecimento "outback"
             :valor           129.9}
            {:cartao          1234123412341234
             :categoria       "saúde"
             :data            (t/local-date "2022-01-02")
             :estabelecimento "dentista"
             :valor           260.0}
            {:cartao          4321432143214321
             :categoria       "lazer"
             :data            (t/local-date "2022-01-10")
             :estabelecimento "show"
             :valor           150.0}
            {:cartao          1598159815981598
             :categoria       "educação"
             :data            (t/local-date "2022-01-30")
             :estabelecimento "alura"
             :valor           85.0}
            {:cartao          1598159815981598
             :categoria       "casa"
             :data            (t/local-date "2022-01-31")
             :estabelecimento "tok&stok"
             :valor           350.0}]))))

(deftest busca-compras-estabelecimento-test
  (testing "Testando buscar compras por estabelecimento"
    (is (= (busca-compras-estabelecimento  "outback" (lista-compras "arquivos/compras.csv"))
           [{:cartao          1234123412341234
             :categoria       "alimentação"
             :data            (t/local-date "2022-01-01")
             :estabelecimento "outback"
             :valor           129.9}]))))

(deftest total-gasto-no-mes-test
  (testing "Testando total gasto no mes"
    (is (= (total-gasto-no-mes 1234123412341234 "2022-01" (lista-compras "arquivos/compras.csv"))
           389.9 ))))

(deftest intervalo-compras-test
  (testing "Testando intervalo de valor de compras"
    (is (= (intervalo-compras 0 50 (lista-compras "arquivos/compras.csv"))
           [{:cartao          1234123412341234
             :categoria       "lazer"
             :data            (t/local-date "2022-02-01")
             :estabelecimento "cinema"
             :valor           20.0}
            {:cartao          6655665566556655
             :categoria       "alimentação"
             :data            (t/local-date "2022-03-01")
             :estabelecimento "madero"
             :valor           50.0}
            {:cartao          3939393939393939
             :categoria       "alimentação"
             :data            (t/local-date "2022-03-11")
             :estabelecimento "dogão"
             :valor           25.9}] ))))

(deftest gasto-categoria-test
  (testing "Testando categoria por teste"
    (is (= (gasto-categoria (lista-compras "arquivos/compras.csv"))
           [{:categoria "alimentação"
             :total 285.7}
            {:categoria "saúde"
             :total 640.0}
            {:categoria "lazer"
             :total 555.87}
            {:categoria "automóvel"
             :total 1266.87}
            {:categoria "educação"
             :total 255.0}
            {:categoria "casa"
             :total 750.0}]))))