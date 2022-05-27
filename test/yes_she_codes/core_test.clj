(ns yes-she-codes.core-test
  (:require [clojure.test :refer :all]
            [yes-she-codes.core :refer :all]))

(defn cria-map [nome cpf email]
  [{:nome nome :cpf cpf :email email}])

(defn cria-cartao [numero cvv validate limite cpf]
  {:numero numero :cvv cvv :validate validate :limite limite :client cpf})

(deftest a-test
  (testing "verifica se cria um novo cliente corretamente"
    (is (= [{:nome  "Emillyn", :cpf "1829182918", :email "testando"}]) (cria-map "Emillyn" "1829182918" "testando")))

  (testing "verifica se cria um novo cartao corretamente"
    (is (= {:numero  12819289182918, :cvv 233, :validate "13/2222" :limite 1000.0 :cpf "238923928"} )
        (cria-cartao 12819289182918 233 "2022/12" 1000.0 "238923928"))))

