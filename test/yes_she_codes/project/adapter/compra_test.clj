(ns yes-she-codes.project.adapter.compra-test
  (:require [clojure.test :refer :all]
            [schema.core :as s]
            [schema.test :as s.test]
            [yes-she-codes.project.model.compra :as model.compra]
            [yes-she-codes.project.adapter.compra :as adapter.compra]
            [yes-she-codes.project.wire.in.csv :as in.csv]
            [java-time :as time]))

(def compras-validas [{:data            (time/local-date "2021-05-09")
                       :valor           100.00M
                       :estabelecimento "estabelecimento"
                       :categoria       "Casa"
                       :cartao          4321432143214321}])

(def csv-valido [["DATA" "VALOR" "ESTABELECIMENTO" "CATEGORIA" "CARTÃO"]
                 ["2021-05-09" "100.00" "estabelecimento" "Casa" "4321 4321 4321 4321"]])


(s/set-fn-validation! false)

(s/validate model.compra/Compras compras-validas)
(s/validate in.csv/RawCsv csv-valido)


;;; testar casos de falhas!!

(s.test/deftest csv->model-test

  (testing "deve se adaptar ao modelo de compra"
    (is (= (adapter.compra/csv->model csv-valido)
            compras-validas)))


  )
