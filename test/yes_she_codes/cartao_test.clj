(ns yes-she-codes.cartao-test
  (:require [clojure.test :refer :all]
            [semana3.cartao :refer :all]
            [schema.core :as s]))

(deftest testa-schema-cartao

  (testing "Deve aceitar o cartao valido"
    (let [cartao-valido {:numero   4321432143214321
                         :cvv      222
                         :validade "2024-02"
                         :limite   2000M
                         :cliente  "333.444.555-66"
                         }]
      (is (= cartao-valido (s/validate CartaoSchema cartao-valido)))))
  )