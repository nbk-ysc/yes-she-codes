(ns yes-she-codes.domain.card-test
  (:require [clojure.test :refer :all]
            [schema.core :as s]
            [java-time :as t]
            [yes-she-codes.domain.card :refer :all]))


(deftest test-card-schema

  (testing "Should accept card with valid mandatory fields"
    (let [valid-card {:number   4321432143214321
                         :cvv      222
                         :expiration-date (t/year-month "2024-02")
                         :limit   2000M
                         :client  "333.444.555-00"}]

      (is (= valid-card (s/validate CardSchema valid-card)))))

  (testing "Should NOT accept negative card number"
    (let [invalid-card {:number   -1
                           :cvv      222
                           :expiration-date (t/year-month "2024-02")
                           :limit   2000M
                           :client  "333.444.555-00"}]

      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CardSchema invalid-card)))))


  (testing "Should NOT accept cvv bigger than 999"
    (let [invalid-card {:number   4321432143214321
                           :cvv      1000
                           :expiration-date (t/year-month "2024-02")
                           :limit   2000M
                           :client  "333.444.555-00"}]

      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CardSchema invalid-card)))))


  (testing "Should not accept null expiration-date"
    (let [invalid-card {:number   4321432143214321
                           :cvv      999
                           :expiration-date nil
                           :limit   2000M
                           :client  "333.444.555-00"}]

      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CardSchema invalid-card)))))


  (testing "Should not accept negative limit"
    (let [invalid-card {:number   4321432143214321
                           :cvv      999
                           :expiration-date (t/year-month "2024-02")
                           :limit   -2000M
                           :client  "333.444.555-00"}]

      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CardSchema invalid-card)))))


  (testing "Should not accept client with wrong cpf"
    (let [invalid-card {:number   4321432143214321
                           :cvv      999
                           :expiration-date (t/year-month "2024-02")
                           :limit   2000M
                           :client  "33344455500"}]

      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate CardSchema invalid-card)))))

  )
