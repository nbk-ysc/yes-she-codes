(ns yes-she-codes.project.adapter.cartao-test
  (:require [clojure.test :refer :all]
            [schema.core :as s]
            [schema.test :as s.test]
            [yes-she-codes.project.model.cartao :as model.cartao]
            [yes-she-codes.project.adapter.cartao :as adapter.cartao]
            [yes-she-codes.project.wire.in.csv :as in.csv]
            [java-time :as time]))

(s.test/deftest csv->cartoes-test
  (let [cartoes [{:numero   1234123412341234,
                  :cvv      111,
                  :validade (time/year-month "2023-01"),
                  :limite   1000M,
                  :cliente  "000.111.222-33"}]
        csv [["NÚMERO" "CVV" "VALIDADE" "LIMITE" "CLIENTE"]
             ["1234 1234 1234 1234" "111" "2023-01" "1000" "000.111.222-33"]]]

    (s/set-fn-validation! true)
    (s/validate model.cartao/Cartoes cartoes)
    (s/validate in.csv/RawCsv csv)

    (testing "arquivo vazio deve retornar vetor vazio"
      (is (= (adapter.cartao/csv->cartoes [[]])
             [])))
    (testing "deve se adaptar ao modelo de cartao"
      (is (= (adapter.cartao/csv->cartoes csv)
             cartoes)))))
