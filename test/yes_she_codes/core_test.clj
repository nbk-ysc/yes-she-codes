(ns yes-she-codes.core-test
  (:require [clojure.test :refer :all]
            [yes-she-codes.core :refer :all]))

(defn cria-map [nome cpf email]
  [{:nome nome :cpf cpf :email email}])

(defn cria-cartao [numero cvv validate limite cpf]
  {:numero numero :cvv cvv :validate validate :limite limite :cpf cpf})

(deftest a-test
  (testing "verifica se cria um novo cliente corretamente"
    (is (= [{:nome  "Emillyn", :cpf "1829182918", :email "testando"}]  ) (cria-map "Emillyn" "1829182918" "testando"))))

