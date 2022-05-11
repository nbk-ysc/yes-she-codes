(ns yes-she-codes.core-test
  (:require [clojure.test :refer :all]
            [yes-she-codes.adatper.adapter-test :refer :all]
            [yes-she-codes.logic.logic-test :refer :all]
            [yes-she-codes.model.model-test :refer :all]))

; runs all tests in all namespaces
; prints results.
(deftest eg-tests (is (= 1 1)))
(run-all-tests #"yes-she-codes.*")
