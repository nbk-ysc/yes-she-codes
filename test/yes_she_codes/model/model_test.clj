(ns yes-she-codes.model.model-test
  (:require [clojure.test :refer :all]
            [yes-she-codes.model.model :as m]))


(deftest novo-cliente-test

  (let [clientes [
                  ["Feiticeira Escarlate" "000.111.222-33" "feiticeira.poderosa@vingadoras.com.br"
                   {:nome "Feiticeira Escarlate" :cpf "000.111.222-33" :email "feiticeira.poderosa@vingadoras.com.br"}]
                  ["Viúva Negra" "333.444.555-66" "viuva.casca.grossa@vingadoras.com.br"
                   {:nome "Viúva Negra"  :cpf "333.444.555-66" :email "viuva.casca.grossa@vingadoras.com.br"}]
                  ["Hermione Granger" "666.777.888-99" "hermione.salvadora@hogwarts.com"
                   {:nome "Hermione Granger" :cpf "666.777.888-99" :email "hermione.salvadora@hogwarts.com"}]
                  ["Daenerys Targaryen" "999.123.456-78" "mae.dos.dragoes@got.com"
                   {:nome "Daenerys Targaryen" :cpf "999.123.456-78" :email "mae.dos.dragoes@got.com"}]
                  ]
        ]

    (testing "criando mapa de clientes dados os parametros adequados"
      (doseq [[nome cpf email estrutura-esperada] clientes]
        (is (= (m/novo-cliente nome cpf email) estrutura-esperada))
        )
      )

    )
  )



(deftest novo-cartao-test
  (let [cartoes [
                  [1234123412341234 111 "2023-01" 1000M "000.111.222-33"
                   {:numero 1234123412341234 :cvv 111 :validade "2023-01" :limite 1000M :cliente "000.111.222-33"}]
                  [4321432143214321 222 "2024-02" 2000M "333.444.555-66"
                   {:numero 4321432143214321 :cvv 222 :validade "2024-02" :limite 2000M :cliente "333.444.555-66"}]
                  [1598159815981598 333 "2021-03" 3000M "666.777.888-99"
                   {:numero 1598159815981598 :cvv 333 :validade "2021-03" :limite 3000M :cliente "666.777.888-99"}]
                  ]
        ]

    (testing "criando mapa de cartões dados os parametros adequados"
      (doseq [[numero cvv validade limite cliente estrutura-esperada] cartoes]
        (is (= (m/novo-cartao numero cvv validade limite cliente) estrutura-esperada))
        )
      )

    )
  )

(deftest nova-compra-test
  (let [compras [
                 ["2022-01-02" 260.00M "Dentista" "Saúde" 1234123412341234
                  {:data "2022-01-02" :valor 260.00M :estabelecimento "Dentista" :categoria "Saúde" :cartão 1234123412341234}]
                 ["2022-02-20" 79.90M "iFood" "Alimentação" 4321432143214321
                  {:data "2022-02-20" :valor 79.90M :estabelecimento "iFood" :categoria "Alimentação" :cartão 4321432143214321}]
                 ["2022-03-04" 250.00M "Hospital" "Saúde" 6655665566556655
                  {:data "2022-03-04" :valor 250.00M :estabelecimento "Hospital" :categoria "Saúde" :cartão 6655665566556655}]
                 ["2022-03-11" 25.90M "Dogão" "Alimentação" 3939393939393939
                  {:data "2022-03-11" :valor 25.90M :estabelecimento "Dogão" :categoria "Alimentação" :cartão 3939393939393939}]
                 ]
        ]

    (testing "criando mapa de cartões dados os parametros adequados"
      (doseq [[data valor estabelecimento categoria cartao estrutura-esperada] compras]
        (is (= (m/nova-compra data valor estabelecimento categoria cartao) estrutura-esperada))
        )
      )

    )

  )
