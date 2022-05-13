(ns yes-she-codes.logic.util-test
  (:require [clojure.test :as t]
            [yes-she-codes.logic.util :as u]
            [java-time :as jt]))

(t/deftest mesmo-mes?-test
  (let [data-2022-07-10 (jt/local-date "2022-07-10")
        data-2021-02-28 (jt/local-date "2021-02-28")]
    (t/testing "retorno se data pertence ao mesmo mes comparado"
      (t/is (= (u/mesmo-mes? data-2022-07-10 10) false))
      (t/is (= (u/mesmo-mes? data-2021-02-28 02) true)))))


(t/deftest pertence-ao-intervalo?-test
  (let [data-max (jt/local-date "2022-12-31")
        data-min (jt/local-date "2022-01-01")
        data-in  (jt/local-date "2022-03-01")
        data-out (jt/local-date "2021-12-31")]
    (t/testing "retorno deve pertencer ao intervalo"
      (t/is (= (u/pertence-ao-intervalo? data-max data-min data-in) true))
      (t/is (= (u/pertence-ao-intervalo? data-max data-min data-max) true))
      (t/is (= (u/pertence-ao-intervalo? data-max data-min data-min) true)))
    (t/testing "retorno não deve pertencer ao intervalo"
      (t/is (= (u/pertence-ao-intervalo? data-max data-min data-out) false)))))

(t/deftest arquivo->vetor
  (let [file-path       "/Users/vitoria.galli/Documents/alura/bootcamp/yes-she-codes/data/teste.csv"
        expected-vector ["campo00,campo01,camp02"
                         "valor10,valor11,valor12"
                         "valor20,valor21,valor22"]]
    (t/testing "carregar dados do arquivo dentro de um vetor"
      (t/is (= (u/arquivo->vetor file-path) expected-vector )))))

(t/deftest string-sem-espacos-test
  (t/testing "retorno de strings sem espacos"
    (t/is (= (u/string-sem-espacos "1 2 3 4 5") "12345"))
    (t/is (= (u/string-sem-espacos "1 2 \t 3 4      5") "12345"))
    (t/is (= (u/string-sem-espacos "1 \t \r 2 \t  3 4 5") "12345"))))


(t/deftest csv-splitter-test
  (t/testing "splitter com ',' como separador"
    (t/is (= (u/csv-splitter "1,2,3,4,5") '("1" "2" "3" "4" "5")))
    (t/is (= (u/csv-splitter "123,csv,csv123") ["123" "csv" "csv123"]))
    (t/is (= (u/csv-splitter ", , , ,") ["" " " " " " "]))
    (t/is (= (u/csv-splitter ",,,,") []))))

