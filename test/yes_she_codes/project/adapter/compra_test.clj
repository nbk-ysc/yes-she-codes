(ns yes-she-codes.project.adapter.compra-test
  (:require [clojure.test :refer :all]
            [schema.test :as s.test]
            [yes-she-codes.project.adapter.compra :as adapter.compra]
            [java-time :as time]))

(defn ^:private dissoc-id
  [map]
  (dissoc map :compra/id))

(defn ^:private dissoc-ids
  [vec]
  (map dissoc-id vec))

(s.test/deftest csv->model-test
  (let [compras [#:compra{:data            (time/local-date "2021-05-09"),
                          :valor           100.00M,
                          :estabelecimento "estabelecimento",
                          :categoria       "Casa",
                          :cartao          4321432143214321}]
        csv [["DATA" "VALOR" "ESTABELECIMENTO" "CATEGORIA" "CARTÃO"]
             ["2021-05-09" "100.00" "estabelecimento" "Casa" "4321 4321 4321 4321"]]
        csv-com-chaves-extras [["DATA" "HORÁRIO" "VALOR" "ESTABELECIMENTO" "CATEGORIA" "CARTÃO"]
                               ["2021-05-09" "18:18:00" "100.00" "estabelecimento" "Casa" "4321 4321 4321 4321"]]
        csv-faltando-chave [["DATA" "ESTABELECIMENTO" "CATEGORIA" "CARTÃO"]
                         ["2021-05-09" "estabelecimento" "Casa" "4321 4321 4321 4321"]]
        csv-valor-fora-do-schema [["DATA" "VALOR" "ESTABELECIMENTO" "CATEGORIA" "CARTÃO"]
                                  ["09-05-2021" "100.00" "estabelecimento" "Casa" "4321 4321 4321 4321"]]]
    (testing "arquivo vazio deve retornar vetor vazio"
      (is (= []
             (dissoc-ids (adapter.compra/csv->model [[]])))))
    (testing "deve se adaptar ao modelo de compra mesmo com informações a mais"
      (is (= compras
             (dissoc-ids (adapter.compra/csv->model csv)))))
    (testing "deve se adaptar ao modelo de compra"
      (is (= compras
             (dissoc-ids (adapter.compra/csv->model csv-com-chaves-extras)))))
    (testing "falhando quando falta a chave de valor"
      (is (thrown-with-msg? clojure.lang.ExceptionInfo
                            #"does not match schema"
                            (adapter.compra/csv->model csv-faltando-chave))))
    (testing "falhando quando algum valor não atende a conversao"
      (is (thrown-with-msg? clojure.lang.ExceptionInfo
                            #"does not match schema"
                            (adapter.compra/csv->model csv-valor-fora-do-schema))))))
