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
    (is (= (busca-compras-mes [{:data "2022-01-01"}
                               {:data "2022-03-02"}
                               {:data "2022-01-02"}] "2022-01")
           [{:data "2022-01-01"}
            {:data "2022-01-02"}] ))))