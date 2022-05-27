(ns yes-she-codes.project.adapter.cliente-test
  (:require [clojure.test :refer :all]
            [schema.core :as s]
            [schema.test :as s.test]
            [yes-she-codes.project.model.cliente :as model.cliente]
            [yes-she-codes.project.adapter.cliente :as adapter.cliente]
            [yes-she-codes.project.wire.in.csv :as in.csv]))

(s.test/deftest csv->clientes-test
  (let [clientes [{:nome "Feiticeira Escarlate", :cpf "000.111.222-33", :email "feiticeira.poderosa@vingadoras.com.br"}
                  {:nome "Viúva Negra", :cpf "333.444.555-66", :email "viuva.casca.grossa@vingadoras.com.br"}
                  {:nome "Hermione Granger", :cpf "666.777.888-99", :email "hermione.salvadora@hogwarts.com"}
                  {:nome "Daenerys Targaryen", :cpf "999.123.456-78", :email "mae.dos.dragoes@got.com"}]
        csv [["NOME" "CPF" "EMAIL"]
             ["Feiticeira Escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"]
             ["Viúva Negra" "333.444.555-66" "viuva.casca.grossa@vingadoras.com.br"]
             ["Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"]
             ["Daenerys Targaryen" "999.123.456-78" "mae.dos.dragoes@got.com"]]]

    (s/set-fn-validation! true)
    (s/validate model.cliente/Clientes clientes)
    (s/validate in.csv/RawCsv csv)

    (testing "arquivo vazio deve retornar vetor vazio"
      (is (= (adapter.cliente/csv->clientes [[]])
             [])))
    (testing "deve se adaptar ao modelo de cliente"
      (is (= (adapter.cliente/csv->clientes csv)
             clientes)))))
