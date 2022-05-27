(ns yes-she-codes.project.adapter.compra-test
  (:require [clojure.test :refer :all]
            [schema.test :as s.test]
            [yes-she-codes.project.adapter.compra :as adapter.compra]
            [java-time :as time]))

(s.test/deftest csv->compras-test

  (let [compras [{:data            (time/local-date "2021-05-09")
                  :valor           100.00M
                  :estabelecimento "estabelecimento"
                  :categoria       "Casa"
                  :cartao          4321432143214321}]
        csv [["DATA" "VALOR" "ESTABELECIMENTO" "CATEGORIA" "CARTÃO"]
             ["2021-05-09" "100.00" "estabelecimento" "Casa" "4321 4321 4321 4321"]]
        ]

    (testing "arquivo vazio deve retornar vetor vazio"
      (is (= (adapter.compra/csv->compras [[]])
             [])))

    (testing "deve se adaptar ao modelo de compra"
      (is (= (adapter.compra/csv->compras csv)
             compras)))

    ))
