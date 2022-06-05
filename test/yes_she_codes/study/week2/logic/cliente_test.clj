(ns yes-she-codes.study.week2.logic.cliente-test
  (:require [clojure.test :as test]
            [yes-she-codes.study.week2.logic.cliente :as logic.cliente]))

(test/deftest insere-cliente!-test
  (let [record    {:id nil :nome "" :cpf "" :email ""}
        clientes  (atom [])]
    (dotimes [_ 10] (logic.cliente/insere-cliente! clientes record))
    (test/is (= (count @clientes) 10))))

(test/deftest exclui-cliente!-test
  (let [record   {:id nil :nome "" :cpf "" :email ""}
        clientes  (atom [])]
    (dotimes [_ 10] (logic.cliente/insere-cliente! clientes record))
    (doseq [n [1 2 5 7] ] (logic.cliente/exclui-cliente! clientes n))
    (test/is (= (count @clientes) 6))

    (test/testing "pesquisa-cliente-por-id!"
      (test/is (= (logic.cliente/pesquisa-cliente-por-id! clientes 7) nil))
      (test/is (= (logic.cliente/pesquisa-cliente-por-id! clientes 6) (assoc record :id 6))))))