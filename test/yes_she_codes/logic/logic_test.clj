(ns yes-she-codes.logic.logic-test
  (:require [clojure.test :as t]
            [yes-she-codes.logic.logic :as l])
  (:import (java.time LocalDate)))


(t/deftest total-gasto-test
  (let [lista-compras [{:data "", :valor 129.90M, :estabelecimento "", :categoria "", :cartão 0}
                       {:data "", :valor 260.00M, :estabelecimento "", :categoria "", :cartão 0}
                       {:data "", :valor 20.00M, :estabelecimento "", :categoria "", :cartão 0}]
        valor-esperado 409.9M]
    (t/testing "soma dos valores de uma lista de compras"
      (t/is (= (l/total-gasto lista-compras) valor-esperado)))))


(t/deftest lista-de-compras-do-mes-test
  (let [data1 (LocalDate/parse "2022-04-10")
        data2 (LocalDate/parse "2022-05-04")
        data3 (LocalDate/parse "2021-04-29")
        lista-compras [{:data data1, :valor 0M, :estabelecimento "", :categoria "", :cartão 0}
                       {:data data2, :valor 0M, :estabelecimento "", :categoria "", :cartão 0}
                       {:data data3, :valor 0M, :estabelecimento "", :categoria "", :cartão 0}]
        mes            04
        lista-esperada-do-mes [{:data data1, :valor 0M, :estabelecimento "", :categoria "", :cartão 0}
                              {:data data3, :valor 0M, :estabelecimento "", :categoria "", :cartão 0}]]
    (t/testing "soma dos valores de uma lista de compras para um determinado mes"
      (t/is (= (l/lista-de-compras-do-mes mes lista-compras) lista-esperada-do-mes)))))


(t/deftest lista-de-compras-do-estabelecimento-test
  (let [lista-compras [{:data "", :valor 129.90M, :estabelecimento "Outback", :categoria "Alimentação", :cartão 0}
                       {:data "", :valor 260.00M, :estabelecimento "Dentista", :categoria "Saúde", :cartão 0}
                       {:data "", :valor 20.00M, :estabelecimento "Dentista", :categoria "Saúde", :cartão 0}]
        estabelecimento "Dentista"
        lista-esperada-do-estabelecimento [{:data "", :valor 260.00M, :estabelecimento "Dentista", :categoria "Saúde", :cartão 0}
                                           {:data "", :valor 20.00M, :estabelecimento "Dentista", :categoria "Saúde", :cartão 0}]]
    (t/testing "soma dos valores de uma lista de compras para um determinado mes"
      (t/is (= (l/lista-de-compras-do-estabelecimento estabelecimento lista-compras) lista-esperada-do-estabelecimento)))))


(t/deftest total-gasto-no-mes-test
  (let [data1 (LocalDate/parse "2022-03-10")
        data2 (LocalDate/parse "2022-11-04")
        data3 (LocalDate/parse "2021-03-29")
        lista-compras [{:data data1, :valor 129.90M, :estabelecimento "", :categoria "", :cartão 0}
                       {:data data2, :valor 260.00M, :estabelecimento "", :categoria "", :cartão 0}
                       {:data data3, :valor 20.00M, :estabelecimento "", :categoria "", :cartão 0}]
        mes            03
        valor-esperado 149.9M]
    (t/testing "soma dos valores de uma lista de compras para um determinado mes"
      (t/is (= (l/total-gasto-no-mes mes lista-compras) valor-esperado)))))


(t/deftest lista-de-compras-por-intervalo-test
  (let [data-max (LocalDate/parse "2022-03-25")
        data-min (LocalDate/parse "2022-01-10")
        data3    (LocalDate/parse "2022-03-28")
        data2    (LocalDate/parse "2022-01-10")
        data1    (LocalDate/parse "2022-02-05")
        lista-compras [{:data data1, :valor 0M, :estabelecimento "", :categoria "", :cartão 0}
                       {:data data2, :valor 0M, :estabelecimento "", :categoria "", :cartão 0}
                       {:data data3, :valor 0M, :estabelecimento "", :categoria "", :cartão 0}]
        lista-esperada-no-intervalo [{:data data1, :valor 0M, :estabelecimento "", :categoria "", :cartão 0}
                                     {:data data2, :valor 0M, :estabelecimento "", :categoria "", :cartão 0}]]
    (t/testing "lista dentro de um intervalo dado"
      (t/is (= (l/lista-de-compras-por-intervalo data-max data-min lista-compras) lista-esperada-no-intervalo)))))


(t/deftest gasto-por-categoria-test
  (let [lista-compras [{:data "" , :valor 129.90M, :estabelecimento "", :categoria "Alimentação", :cartão 0}
                       {:data "" , :valor 260.00M, :estabelecimento "", :categoria "Saúde", :cartão 0}
                       {:data "" , :valor 20.00M, :estabelecimento "", :categoria "Lazer", :cartão 0}
                       {:data "" , :valor 150.00M, :estabelecimento "", :categoria "Lazer", :cartão 0}
                       {:data "", :valor 289.99M, :estabelecimento "", :categoria "Automóvel", :cartão 0}
                       {:data "", :valor 79.90M, :estabelecimento "", :categoria "Alimentação", :cartão 0}
                       {:data "", :valor 85.00M, :estabelecimento "", :categoria "Educação", :cartão 0}
                       {:data "", :valor 85.00M, :estabelecimento "", :categoria "Educação", :cartão 0}
                       {:data "", :valor 350.00M, :estabelecimento "", :categoria "Casa", :cartão 0}
                       {:data "", :valor 400.00M, :estabelecimento "", :categoria "Casa", :cartão 0}
                       {:data "", :valor 50.00M, :estabelecimento "", :categoria "Alimentação", :cartão 0}
                       {:data "", :valor 70.00M, :estabelecimento "", :categoria "Lazer", :cartão 0}
                       {:data "", :valor 250.00M, :estabelecimento "", :categoria "Saúde", :cartão 0}
                       {:data "", :valor 130.00M, :estabelecimento "", :categoria "Saúde", :cartão 0}
                       {:data "", :valor 100.00M, :estabelecimento "", :categoria "Lazer", :cartão 0}
                       {:data "", :valor 25.90M, :estabelecimento "", :categoria "Alimentação", :cartão 0}
                       {:data "", :valor 215.87M, :estabelecimento "", :categoria "Lazer", :cartão 0}
                       {:data "", :valor 976.88M, :estabelecimento "", :categoria "Automóvel", :cartão 0}
                       {:data "", :valor 85.00M, :estabelecimento "", :categoria "Educação", :cartão 0}]
        gasto-alimentacao-esperado 285.70M
        gasto-lazer-esperado 555.87M
        gasto-saude-esperado 640.00M
        gasto-por-categoria (l/gasto-por-categoria lista-compras)]
    (t/testing "soma dos valores de uma lista de compras agrupado por categoria"
      (t/is (= (get gasto-por-categoria "Alimentação") gasto-alimentacao-esperado))
      (t/is (= (get gasto-por-categoria "Lazer") gasto-lazer-esperado))
      (t/is (= (get gasto-por-categoria "Saúde") gasto-saude-esperado)))))
