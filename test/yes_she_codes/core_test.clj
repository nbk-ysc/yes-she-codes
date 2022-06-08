(ns yes-she-codes.core-test
  (:require [clojure.test :refer :all]
            [yes-she-codes.core :refer :all]
            [yes-she-codes.semana3.schemas :refer :all]
            [schema.core :as s]))

(deftest valida-cliente

  (let [cliente {:nome "Viúva Negra" :cpf "333.444.555-66" :email "viuva.casca.grossa@vingadoras.com.br"}]

       (is (= {:nome "Viúva Negra" :cpf "333.444.555-66" :email "viuva.casca.grossa@vingadoras.com.br"}
              (s/validate ClienteSchema cliente)))))

(deftest valida-cartao

  (let [cartao {:numero 4321432143214321 :cvv 222 :validade "2024-02" :limite 2.000M :cliente "333.444.555-66"}]

    (is (= {:numero 4321432143214321 :cvv 222 :validade "2024-02" :limite 2.000M :cliente "333.444.555-66"}
           (s/validate CartaoSchema cartao)))))


(deftest valida-compra

  (let [compra {:data-compra "2022-05-09" :valor 100M :estabelecimento "Amazon" :categoria "Casa" :cartao 4321432143214321}]
    (is (= {:data-compra "2022-05-09" :valor 100M :estabelecimento "Amazon" :categoria "Casa" :cartao 4321432143214321}
           (s/validate CompraSchema compra)))))