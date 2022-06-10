(ns yes-she-codes.cartoes-test
  (:require [clojure.test :refer :all]
            [schema.core :as s]
            [yes-she-codes.models.cartoes :refer :all]))

(deftest cartao-schema-test
  (let [cartao-test {:numero   4321432143214321
                     :cvv      222
                     :validade "2024-02"
                     :limite   2000.00M
                     :cliente  "333.444.555-66"}]
    (testing "Que o schema de cartao aceita um cartao válido"
      (is cartao-test (s/validate CartaoSchema cartao-test)))))
