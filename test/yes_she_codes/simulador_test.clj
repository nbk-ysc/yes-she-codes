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