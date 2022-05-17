(ns yes-she-codes.core-test
  (:require [clojure.test :refer :all]
            [yes-she-codes.compra :refer :all]))

(deftest a-test
  (testing "adicionar uma nova compra"
    (is (= (nova-compra "2022-01-01" 258.95 "Estabelecimento Teste" "Categoria Teste" "123 654 789 0000")
           {:data "2022-01-01", :valor 258.95M, :estabelecimento "Estabelecimento Teste", :categoria "Categoria Teste", :cartao 1236547890000}))))


