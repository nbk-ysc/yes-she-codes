(ns yes-she-codes.simulador-test
  (:require [clojure.test :refer :all]
            [yes-she-codes.simulador :refer :all]))

(deftest total-gasto-test
  (testing "Testando total gasto"
    (is (= (total-gasto [{:valor 100.5}
                         {:valor 250}
                         {:valor 400}]) 750.50))))
