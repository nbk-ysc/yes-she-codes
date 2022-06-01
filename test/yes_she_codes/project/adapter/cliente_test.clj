(ns yes-she-codes.project.adapter.cliente-test
  (:require [clojure.test :refer :all]
            [schema.test :as s.test]
            [yes-she-codes.project.adapter.cliente :as adapter.cliente]))

(defn ^:private dissoc-id
  [map]
  (dissoc map :cliente/id))

(defn ^:private dissoc-ids
  [vec]
  (map dissoc-id vec))

(s.test/deftest csv->clientes-test
  (let [clientes [#:cliente{:nome "Feiticeira Escarlate", :cpf "000.111.222-33", :email "feiticeira.poderosa@vingadoras.com.br"}
                  #:cliente{:nome "Viúva Negra", :cpf "333.444.555-66", :email "viuva.casca.grossa@vingadoras.com.br"}
                  #:cliente{:nome "Hermione Granger", :cpf "666.777.888-99", :email "hermione.salvadora@hogwarts.com"}
                  #:cliente{:nome "Daenerys Targaryen", :cpf "999.123.456-78", :email "mae.dos.dragoes@got.com"}]
        csv [["NOME" "CPF" "EMAIL"]
             ["Feiticeira Escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"]
             ["Viúva Negra" "333.444.555-66" "viuva.casca.grossa@vingadoras.com.br"]
             ["Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"]
             ["Daenerys Targaryen" "999.123.456-78" "mae.dos.dragoes@got.com"]]
        csv-erro [["NOME" "CPF" "EMAIL"]
                  ["Feiticeira Escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"]
                  ["Viúva Negra" "333444555-66" "viuva.casca.grossa@vingadoras.com.br"]
                  ["Hermione Granger" "666777888-99" "hermione.salvadora@hogwarts.com"]
                  ["Daenerys Targaryen" "999123456-78" "mae.dos.dragoes@got.com"]]]
    (testing "arquivo vazio deve retornar vetor vazio"
      (is (= (dissoc-ids (adapter.cliente/csv->clientes [[]]))
             [])))
    (testing "deve se adaptar ao modelo de cliente"
      (is (= (dissoc-ids (adapter.cliente/csv->clientes csv))
             clientes)))
    (testing "falhando quando falta a chave de valor"
      (is (thrown-with-msg? clojure.lang.ExceptionInfo
                            #"does not match schema"
                            (adapter.cliente/csv->clientes csv-erro))))))
