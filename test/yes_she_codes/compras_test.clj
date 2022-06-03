(ns yes-she-codes.compras-test
  (:require [clojure.test :refer :all]
            [schema.core :as s]
            [yes_she_codes.compras :refer :all]))

(deftest compra-schema-test
  (let [compra-sem-id-test {:data            "2022-05-09"
                            :valor           100M
                            :estabelecimento "Amazon"
                            :categoria       "Casa"
                            :cartao          4321432143214321}]
    (testing "Que o schema de compra aceita uma compra válida SEM ID"
      (is compra-sem-id-test (s/validate CompraSchema compra-sem-id-test)))

    (testing "Que o schema de compra aceita uma compra válida COM ID VÁLIDO"
      (let [compra-com-id-valido-test (assoc compra-sem-id-test :id 0)]
        (is compra-com-id-valido-test (s/validate CompraSchema compra-com-id-valido-test))))

    (testing "Que o schema de compra aceita uma compra válida COM ID NULO"
      (let [compra-com-id-nulo-test (assoc compra-sem-id-test :id nil)]
        (is compra-com-id-nulo-test (s/validate CompraSchema compra-com-id-nulo-test))))))
