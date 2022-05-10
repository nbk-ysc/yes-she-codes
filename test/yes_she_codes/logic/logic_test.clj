(ns yes-she-codes.logic.logic-test
  (:require [clojure.test :refer :all])
  (:require [yes-she-codes.logic.logic :as l]))

(deftest total-gasto-test
  (let [lista-compras [{:data "2022-01-01",
                        :valor 129.90M,
                        :estabelecimento "Outback",
                        :categoria "Alimentação",
                        :cartão 1234123412341234}
                       {:data "2022-01-02",
                        :valor 260.00M,
                        :estabelecimento "Dentista",
                        :categoria "Saúde",
                        :cartão 1234123412341234}
                       {:data "2022-02-01",
                        :valor 20.00M,
                        :estabelecimento "Cinema",
                        :categoria "Lazer",
                        :cartão 1234123412341234}]
        valor-esperado 409.9M]

    (testing "soma dos valores de uma lista de compras"
      (is (= (l/total-gasto lista-compras) valor-esperado))
      )
    )
  )


(deftest lista-de-compras-do-mes-test
  (let [lista-compras [{:data "2022-03-01",
                        :valor 129.90M,
                        :estabelecimento "Outback",
                        :categoria "Alimentação",
                        :cartão 1234123412341234}
                       {:data "2022-01-02",
                        :valor 260.00M,
                        :estabelecimento "Dentista",
                        :categoria "Saúde",
                        :cartão 1234123412341234}
                       {:data "2022-03-01",
                        :valor 20.00M,
                        :estabelecimento "Cinema",
                        :categoria "Lazer",
                        :cartão 1234123412341234}]
        mes            "03"
        lista-esperada-do-mes [{:data "2022-03-01",
                         :valor 129.90M,
                         :estabelecimento "Outback",
                         :categoria "Alimentação",
                         :cartão 1234123412341234}
                        {:data "2022-03-01",
                         :valor 20.00M,
                         :estabelecimento "Cinema",
                         :categoria "Lazer",
                         :cartão 1234123412341234}]]

    (testing "soma dos valores de uma lista de compras para um determinado mes"
      (is (= (l/lista-de-compras-do-mes mes lista-compras) lista-esperada-do-mes))
      )
    )
  )


(deftest lista-de-compras-do-estabelecimento-test
  (let [lista-compras [{:data "2022-03-01",
                        :valor 129.90M,
                        :estabelecimento "Outback",
                        :categoria "Alimentação",
                        :cartão 1234123412341234}
                       {:data "2022-01-02",
                        :valor 260.00M,
                        :estabelecimento "Dentista",
                        :categoria "Saúde",
                        :cartão 1234123412341234}
                       {:data "2022-03-01",
                        :valor 20.00M,
                        :estabelecimento "Dentista",
                        :categoria "Saúde",
                        :cartão 1234123412341234}]
        estabelecimento   "Dentista"
        lista-esperada-do-estabelecimento [{:data "2022-01-02",
                         :valor 260.00M,
                         :estabelecimento "Dentista",
                         :categoria "Saúde",
                         :cartão 1234123412341234}
                        {:data "2022-03-01",
                         :valor 20.00M,
                         :estabelecimento "Dentista",
                         :categoria "Saúde",
                         :cartão 1234123412341234}]
        ]

    (testing "soma dos valores de uma lista de compras para um determinado mes"
      (is (= (l/lista-de-compras-do-estabelecimento estabelecimento lista-compras) lista-esperada-do-estabelecimento))
      )
    )
  )


(deftest total-gasto-no-mes-test
  (let [lista-compras [{:data "2022-03-01",
                        :valor 129.90M,
                        :estabelecimento "Outback",
                        :categoria "Alimentação",
                        :cartão 1234123412341234}
                       {:data "2022-01-02",
                        :valor 260.00M,
                        :estabelecimento "Dentista",
                        :categoria "Saúde",
                        :cartão 1234123412341234}
                       {:data "2022-03-01",
                        :valor 20.00M,
                        :estabelecimento "Cinema",
                        :categoria "Lazer",
                        :cartão 1234123412341234}]
        mes            "03"
        valor-esperado 149.9M]

    (testing "soma dos valores de uma lista de compras para um determinado mes"
      (is (= (l/total-gasto-no-mes mes lista-compras) valor-esperado))
      )
    )
  )
