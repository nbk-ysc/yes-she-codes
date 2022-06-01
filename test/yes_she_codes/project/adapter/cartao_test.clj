(ns yes-she-codes.project.adapter.cartao-test
  (:require [clojure.test :refer :all]
            [schema.test :as s.test]
            [yes-she-codes.project.adapter.cartao :as adapter.cartao]
            [java-time :as time]))

(defn ^:private dissoc-id
  [map]
  (dissoc map :cartao/id))

(defn ^:private dissoc-ids
  [vec]
  (map dissoc-id vec))

(s.test/deftest csv->cartoes-test
  (let [cartoes [#:cartao{:numero   1234123412341234,
                          :cvv      111,
                          :validade (time/year-month "2023-01"),
                          :limite   1000M,
                          :cliente  "000.111.222-33"}]
        csv [["NÚMERO" "CVV" "VALIDADE" "LIMITE" "CLIENTE"]
             ["1234 1234 1234 1234" "111" "2023-01" "1000" "000.111.222-33"]]
        csv-chave-extra [["CÓDIGO" "BANCO" "CVV" "VALIDADE" "LIMITE" "NÚMERO" "CLIENTE"]
                         ["111" "Nubank" "111" "2023-01" "1000" "1234 1234 1234 1234" "000.111.222-33"]]
        csv-faltando-chave [["NÚMERO" "CVV" "LIMITE" "CLIENTE"]
                            ["1234 1234 1234 1234" "111" "1000" "000.111.222-33"]]
        csv-valor-fora-schema [["CVV" "VALIDADE" "LIMITE" "NÚMERO" "CLIENTE"]
                               ["111" "2023-01" "1000" "1234 1234 1234 1234" "000111222/33"]]]
    (testing "arquivo vazio deve retornar vetor vazio"
      (is (= (dissoc-ids (adapter.cartao/csv->cartoes [[]]))
             [])))
    (testing "deve se adaptar ao modelo de cartao"
      (is (= (dissoc-ids (adapter.cartao/csv->cartoes csv))
             cartoes)))
    (testing "deve se adaptar ao modelo de cartao mesmo com chaves extras"
      (is (= (dissoc-ids (adapter.cartao/csv->cartoes csv-chave-extra))
             cartoes)))
    (testing "falhando quando algum falta alguma chave de valor"
      (is (thrown-with-msg? clojure.lang.ExceptionInfo
                            #"does not match schema"
                            (adapter.cartao/csv->cartoes csv-faltando-chave))))
    (testing "falhando quando algum valor não atende o padrão de conversão"
      (is (thrown-with-msg? clojure.lang.ExceptionInfo
                            #"does not match schema"
                            (adapter.cartao/csv->cartoes csv-valor-fora-schema))))))

