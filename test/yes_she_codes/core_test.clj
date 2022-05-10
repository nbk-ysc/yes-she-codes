(ns yes-she-codes.core-test
  (:require [clojure.test :refer :all]
            [yes-she-codes.core :refer :all]))

(deftest a-test
  (testing "lista-cartoes vazia"
    (is (= [] (lista-cartoes))))
  (testing "adicionar novo cartao"
    (novo-cartao 1234123412341234	111	"2023-01" 1.000	"000.111.222-33")
    (is (= 1 (count (lista-cartoes))))))


