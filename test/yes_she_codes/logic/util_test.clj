(ns yes-she-codes.logic.util-test
  (:require [clojure.test :as t]
            [yes-she-codes.logic.util :as u])
  (:import (java.time LocalDate)))


(t/deftest str->local-date-test
  (t/testing "objeto de data gerado a partir de umas string 'yyyy-MM-dd'"
    (t/is (= (.toString (u/str->local-date "2022-12-10")) "2022-12-10"))
    (t/is (= (.toString (u/str->local-date "2021-01-01")) "2021-01-01"))))


(t/deftest str->year-month-test
  (t/testing "objeto de data gerado a partir de umas string 'yyyy-MM'"
    (t/is (= (.toString (u/str->year-month "2022-12")) "2022-12"))
    (t/is (= (.toString (u/str->year-month "2021-01")) "2021-01"))))


(t/deftest qual-mes?-test
  (let [data-2022-07-10 (LocalDate/parse "2022-07-10")
        data-2021-02-28 (LocalDate/parse "2021-02-28")]
    (t/testing "retorno do mes dado um objeto de data"
      (t/is (= (u/qual-mes? data-2022-07-10) 07))
      (t/is (= (u/qual-mes? data-2021-02-28) 2)))))


(t/deftest pertence-ao-intervalo?-test
  (let [data-max (LocalDate/parse "2022-12-31")
        data-min (LocalDate/parse "2022-01-01")
        data-in  (LocalDate/parse "2022-03-01")
        data-out (LocalDate/parse "2021-12-31")]
    (t/testing "retorno deve pertencer ao intervalo"
      (t/is (= (u/pertence-ao-intervalo? data-max data-min data-in) true))
      (t/is (= (u/pertence-ao-intervalo? data-max data-min data-max) true))
      (t/is (= (u/pertence-ao-intervalo? data-max data-min data-min) true))
      )
    (t/testing "retorno não deve pertencer ao intervalo"
      (t/is (= (u/pertence-ao-intervalo? data-max data-min data-out) false)))))