(ns yes-she-codes.week2.logic.cartao-test
  (:require [clojure.test :as test]
            [yes-she-codes.week2.logic.cartao :as logic.cartao]))

(test/deftest insere-cartao!-test
  (let [record    {:id nil :numero 0 :cvv 0 :validade "" :limite 0M :cliente ""}
        cartoes  (atom [])]
    (dotimes [_ 10] (logic.cartao/insere-cartao! cartoes record))
    (test/is (= (count @cartoes) 10))))

(test/deftest exclui-cartao!-test
  (let [record   {:id nil :numero 0 :cvv 0 :validade "" :limite 0M :cliente ""}
        cartoes  (atom [])]
    (dotimes [_ 10] (logic.cartao/insere-cartao! cartoes record))
    (doseq [n [1 2 5 7] ] (logic.cartao/exclui-cartao! cartoes n))
    (test/is (= (count @cartoes) 6))

    (test/testing "pesquisa-cartao-por-id!"
      (test/is (= (logic.cartao/pesquisa-cartao-por-id! cartoes 5) nil))
      (test/is (= (logic.cartao/pesquisa-cartao-por-id! cartoes 9) (assoc record :id 9))))))























