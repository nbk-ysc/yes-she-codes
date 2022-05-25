(ns yes-she-codes.week3-testes
  (:require [yes-she-codes.week3 :refer :all]
            [schema.core :as s])
  (:use clojure.pprint))


(println (s/explain ClienteSchema))
(pprint (s/validate ClienteSchema {:nome "Feiticeira Escarlate",
                                   :cpf "000.111.222-33"
                                   :email "feiticeira.poderosa@vingadoras.com.br"}))

(pprint (s/validate CartaoSchema {:numero   1234123412341234,
                                  :cvv      111,
                                  :validade "2023-01",
                                  :limite   1.000M,
                                  :cliente  "000.111.222-33"}))

(pprint (s/validate CompraSchema {:data "2022-01-01",
                                 :valor 129.90M,
                                 :estabelecimento "Outback",
                                 :categoria "Alimentação",
                                 :cartao 1234123412341234}))