(ns yes-she-codes.logic.logic-test
  (:require [clojure.test :as t]
            [yes-she-codes.logic.logic :as l]))


(t/deftest total-gasto-test
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
    (t/testing "soma dos valores de uma lista de compras"
      (t/is (= (l/total-gasto lista-compras) valor-esperado)))))


(t/deftest lista-de-compras-do-mes-test
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
    (t/testing "soma dos valores de uma lista de compras para um determinado mes"
      (t/is (= (l/lista-de-compras-do-mes mes lista-compras) lista-esperada-do-mes)))))


(t/deftest lista-de-compras-do-estabelecimento-test
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
                                             :cartão 1234123412341234}]]
    (t/testing "soma dos valores de uma lista de compras para um determinado mes"
      (t/is (= (l/lista-de-compras-do-estabelecimento estabelecimento lista-compras) lista-esperada-do-estabelecimento)))))


(t/deftest total-gasto-no-mes-test
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
    (t/testing "soma dos valores de uma lista de compras para um determinado mes"
      (t/is (= (l/total-gasto-no-mes mes lista-compras) valor-esperado)))))


(t/deftest lista-de-compras-por-intervalo-test
  (let [lista-compras [{:data "2022-03-28",
                        :valor 129.90M,
                        :estabelecimento "Outback",
                        :categoria "Alimentação",
                        :cartão 1234123412341234}
                       {:data "2022-01-10",
                        :valor 260.00M,
                        :estabelecimento "Dentista",
                        :categoria "Saúde",
                        :cartão 1234123412341234}
                       {:data "2022-02-05",
                        :valor 20.00M,
                        :estabelecimento "Cinema",
                        :categoria "Lazer",
                        :cartão 1234123412341234}]
        tempo-max "2022-03-25"
        tempo-min "2022-01-10"
        lista-esperada-no-intervalo [{:data "2022-01-10",
                                      :valor 260.00M,
                                      :estabelecimento "Dentista",
                                      :categoria "Saúde",
                                      :cartão 1234123412341234}
                                     {:data "2022-02-05",
                                      :valor 20.00M,
                                      :estabelecimento "Cinema",
                                      :categoria "Lazer",
                                      :cartão 1234123412341234}]]
    (t/testing "lista dentro de um intervalo dado"
      (t/is (= (l/lista-de-compras-por-intervalo tempo-min tempo-max lista-compras) lista-esperada-no-intervalo)))))


(t/deftest gasto-por-categoria-test
  (let [lista-compras [{:data "2022-01-01", :valor 129.90M, :estabelecimento "Outback", :categoria "Alimentação", :cartão 1234123412341234}
                       {:data "2022-01-02", :valor 260.00M, :estabelecimento "Dentista", :categoria "Saúde", :cartão 1234123412341234}
                       {:data "2022-02-01", :valor 20.00M, :estabelecimento "Cinema", :categoria "Lazer", :cartão 1234123412341234}
                       {:data "2022-01-10", :valor 150.00M, :estabelecimento "Show", :categoria "Lazer", :cartão 4321432143214321}
                       {:data "2022-02-10", :valor 289.99M, :estabelecimento "Posto de gasolina", :categoria "Automóvel", :cartão 4321432143214321}
                       {:data "2022-02-20", :valor 79.90M, :estabelecimento "iFood", :categoria "Alimentação", :cartão 4321432143214321}
                       {:data "2022-03-01", :valor 85.00M, :estabelecimento "Alura", :categoria "Educação", :cartão 4321432143214321}
                       {:data "2022-01-30", :valor 85.00M, :estabelecimento "Alura", :categoria "Educação", :cartão 1598159815981598}
                       {:data "2022-01-31", :valor 350.00M, :estabelecimento "Tok&Stok", :categoria "Casa", :cartão 1598159815981598}
                       {:data "2022-02-01", :valor 400.00M, :estabelecimento "Leroy Merlin", :categoria "Casa", :cartão 1598159815981598}
                       {:data "2022-03-01", :valor 50.00M, :estabelecimento "Madero", :categoria "Alimentação", :cartão 6655665566556655}
                       {:data "2022-03-01", :valor 70.00M, :estabelecimento "Teatro", :categoria "Lazer", :cartão 6655665566556655}
                       {:data "2022-03-04", :valor 250.00M, :estabelecimento "Hospital", :categoria "Saúde", :cartão 6655665566556655}
                       {:data "2022-04-10", :valor 130.00M, :estabelecimento "Drogaria", :categoria "Saúde", :cartão 6655665566556655}
                       {:data "2022-03-10", :valor 100.00M, :estabelecimento "Show de pagode", :categoria "Lazer", :cartão 3939393939393939}
                       {:data "2022-03-11", :valor 25.90M, :estabelecimento "Dogão", :categoria "Alimentação", :cartão 3939393939393939}
                       {:data "2022-03-12", :valor 215.87M, :estabelecimento "Praia", :categoria "Lazer", :cartão 3939393939393939}
                       {:data "2022-04-01", :valor 976.88M, :estabelecimento "Oficina", :categoria "Automóvel", :cartão 3939393939393939}
                       {:data "2022-04-10", :valor 85.00M, :estabelecimento "Alura", :categoria "Educação", :cartão 3939393939393939}]
        gasto-alimentacao-esperado 285.70M
        gasto-lazer-esperado 555.87M
        gasto-saude-esperado 640.00M
        gasto-por-categoria (l/gasto-por-categoria lista-compras)]
    (t/testing "soma dos valores de uma lista de compras agrupado por categoria"
      (t/is (= (get gasto-por-categoria "Alimentação") gasto-alimentacao-esperado))
      (t/is (= (get gasto-por-categoria "Lazer") gasto-lazer-esperado))
      (t/is (= (get gasto-por-categoria "Saúde") gasto-saude-esperado))
      )))
