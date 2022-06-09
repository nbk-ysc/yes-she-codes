(ns yes-she-codes.compra-test
  (:require [clojure.test :refer :all]
            [semana3.compra :refer :all]
            [schema.core :as s]))

(deftest testa-schema-de-compra

  (testing "Deve aceitar compra com campos válidos"
    (let [compra-valida {:data            "2022-05-09"
                         :valor           100M
                         :estabelecimento "Amazon"
                         :categoria       "Casa"
                         :cartao          4321432143214321}]

      (is (= compra-valida (s/validate CompraSchema compra-valida)))))
  )
