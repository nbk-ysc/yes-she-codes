(ns yes-she-codes.model.data-test
  (:require [clojure.test :refer :all]
            [yes-she-codes.model.data :as d]))


(deftest lista-clientes-test

  (let [lista-esperada
        [ {:nome "Feiticeira Escarlate" :cpf "000.111.222-33" :email "feiticeira.poderosa@vingadoras.com.br"}
         {:nome "Viúva Negra"  :cpf "333.444.555-66" :email "viuva.casca.grossa@vingadoras.com.br"}
         {:nome "Hermione Granger" :cpf "666.777.888-99" :email "hermione.salvadora@hogwarts.com"}
         {:nome "Daenerys Targaryen" :cpf "999.123.456-78" :email "mae.dos.dragoes@got.com"}]
        ]

    (testing "retorno da lista esperada"
      (is (= (d/lista-clientes) lista-esperada))
      )

    )
  )
