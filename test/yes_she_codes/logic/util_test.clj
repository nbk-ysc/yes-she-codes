(ns yes-she-codes.logic.util-test
  (:require [clojure.test :as t]
            [yes-she-codes.logic.util :as u]))

(t/deftest str->local-date-test
  (t/testing "objeto de data gerado a partir de umas string 'yyyy-MM-dd'"
    (t/is (= (.toString (u/str->local-date "2022-12-10")) "2022-12-10"))
    (t/is (= (.toString (u/str->local-date "2021-01-01")) "2021-01-01"))))

(t/deftest str->year-month-test
  (t/testing "objeto de data gerado a partir de umas string 'yyyy-MM'"
    (t/is (= (.toString (u/str->year-month "2022-12")) "2022-12"))
    (t/is (= (.toString (u/str->year-month "2021-01")) "2021-01"))))

(t/deftest qual-mes?-test
  (let [data-2022-07-10 (u/str->local-date "2022-07-10")
        data-2021-02 (u/str->year-month "2021-02")]
    (t/testing "retorno do mes dado um objeto de data'"
      (t/is (= (u/qual-mes? data-2022-07-10) 07))
      (t/is (= (u/qual-mes? data-2021-02) 2)))))
