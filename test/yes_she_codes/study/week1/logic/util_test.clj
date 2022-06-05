(ns yes-she-codes.study.week1.logic.util-test
  (:require [clojure.test :as t]
            [yes-she-codes.study.week1.logic.util :as u]))


(t/deftest csv-data->vector-test
  (t/testing "carregar dados do arquivo dentro de um vetor"
    (let [caminho-arquivo     "test/data/teste.csv"
          estrutura-esperada  [["valor10", "valor11", "valor12"]
                              ["valor20", "valor21", "valor22"]]]
      (t/is (= (u/csv-data->vector caminho-arquivo) estrutura-esperada )))))


(t/deftest string-sem-espacos-test
  (t/testing "retorno de strings sem espacos"
    (t/is (= (u/string-sem-espacos "1 2 3 4 5") "12345"))
    (t/is (= (u/string-sem-espacos "1 2 \t 3 4      5") "12345"))
    (t/is (= (u/string-sem-espacos "1 \t \r 2 \t  3 4 5") "12345"))))


