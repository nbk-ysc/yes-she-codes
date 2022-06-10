(ns yes-she-codes.domain.purchase-test
  (:require [clojure.test :refer :all]
            [yes-she-codes.domain.purchase :refer :all]
            [java-time :as t]
            [schema.core :as s]))

(deftest test-purchase-schema

  (testing "Should accept purchase with valid mandatory fields"

    (let [valid-purchase {:date            (t/local-date "2022-05-09")
                         :value          100M
                         :store "Amazon"
                         :category       "Casa"
                         :card          4321432143214321}]

      (is (= valid-purchase (s/validate PurchaseSchema valid-purchase)))))


  (testing "Should not accept data NIL"

    (let [invalid-purchase {:date            nil
                           :value          100M
                           :store "Amazon"
                           :category       "Casa"
                           :card          4321432143214321}]

      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate PurchaseSchema invalid-purchase)))))

  (testing "Should not accept negative value"

    (let [invalid-purchase {:date            (t/local-date "2022-05-09")
                           :value          -100M
                           :store "Amazon"
                           :category       "Casa"
                           :card          4321432143214321}]

      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate PurchaseSchema invalid-purchase)))))


  (testing "Should not accept store with only one character"

    (let [invalid-purchase {:date            (t/local-date "2022-05-09")
                           :value          100M
                           :store "A"
                           :category       "Casa"
                           :card          4321432143214321}]

      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate PurchaseSchema invalid-purchase)))))

  (testing "Should not accept category out of list"

    (let [invalid-purchase {:date            (t/local-date "2022-05-09")
                           :value          100M
                           :store "Amazon"
                           :category       "INEXISTENTE"
                           :card          4321432143214321}]

      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate PurchaseSchema invalid-purchase)))))


  (testing "Should not accept invalid card"

    (let [invalid-purchase {:date            (t/local-date "2022-05-09")
                           :value          100M
                           :store "Amazon"
                           :category       "Casa"
                           :card          0}]

      (is (thrown? clojure.lang.ExceptionInfo
                   (s/validate PurchaseSchema invalid-purchase)))))

  )
