(ns yes-she-codes.project.logic.compra-test
  (:require [clojure.test :refer :all]
            [schema.core :as s]
            [schema.test :as s.test]
            [yes-she-codes.project.model.compra :as model.compra]
            [yes-she-codes.project.logic.compra :as logic.compra]
            [java-time :as time])
  (:import (java.time Month)))



(def id (java.util.UUID/randomUUID))
(def compra-valida #:compra{:id              id
                            :data            (time/local-date "2022-05-09")
                            :valor           100M
                            :estabelecimento "estabelecimento"
                            :categoria       "Casa"
                            :cartao          4321432143214321})

(s/validate model.compra/Compra compra-valida)

(s.test/deftest total-gasto-test
  (testing "retorna 0M para lista vazia"
    (is (= (logic.compra/total-gasto [])
           0M)))
  (testing "retorna a soma dos valores gastos"
    (is (= (logic.compra/total-gasto [(assoc compra-valida :compra/valor 100M)
                                      (assoc compra-valida :compra/valor 290M)])
           390M))))

(s.test/deftest lista-de-compras-do-mes-test
  (let [compra1 (assoc compra-valida :compra/data (time/local-date "2021-01-01"))
        compra2 (assoc compra-valida :compra/data (time/local-date "2021-09-01"))
        compra3 (assoc compra-valida :compra/data (time/local-date "2021-01-09"))
        compras [compra1 compra2 compra3]]
    (testing "retorna [] quando lista está vazia"
      (is (= (logic.compra/lista-de-compras-do-mes (Month/of 8) [])
             [])))
    (testing "retorna lista de compras do mes de janeiro"
      (is (= (logic.compra/lista-de-compras-do-mes (Month/of 1) compras)
             [compra1 compra3])))))

(s.test/deftest lista-de-compras-do-estabelecimento-test
  (let [compra1 (assoc compra-valida :compra/estabelecimento "estab")
        compra2 (assoc compra-valida :compra/estabelecimento "estab-procurado")
        compra3 (assoc compra-valida :compra/estabelecimento "estab-procurado")
        compras [compra1 compra2 compra3]]
    (testing "retorna [] quando lista está vazia"
      (is (= (logic.compra/lista-de-compras-do-estabelecimento "estab-procurado" [])
             [])))
    (testing "retorna [] quando estabelecimento nao existe]"
      (is (= (logic.compra/lista-de-compras-do-estabelecimento "nao-existe" compras)
             [])))
    (testing "retorna lista de compras do mes de janeiro"
      (is (= (logic.compra/lista-de-compras-do-estabelecimento "estab-procurado" compras)
             [compra2 compra3])))))

(s.test/deftest total-gasto-no-mes-test
  (let [compra1 (assoc compra-valida :compra/data (time/local-date "2021-04-01") :compra/valor 100M)
        compra2 (assoc compra-valida :compra/data (time/local-date "2021-07-04") :compra/valor 200M)
        compra3 (assoc compra-valida :compra/data (time/local-date "2021-04-09") :compra/valor 300M)
        compras [compra1 compra2 compra3]]
    (testing "retorna 0M para lista vazia"
      (is (= (logic.compra/total-gasto-no-mes (Month/of 7) [])
             0M)))
    (testing "retorna 0M para mes sem gasto"
      (is (= (logic.compra/total-gasto-no-mes (Month/of 12) compras)
             0M)))
    (testing "retorna total gasto no mes de abril"
      (is (= (logic.compra/total-gasto-no-mes (Month/of 4) compras)
             400M)))))

(s.test/deftest lista-de-compras-por-intervalo-de-valores-test
  (let [compra1 (assoc compra-valida :compra/valor 100M)
        compra2 (assoc compra-valida :compra/valor 101M)
        compra3 (assoc compra-valida :compra/valor 30M)
        compra4 (assoc compra-valida :compra/valor 29M)
        compras [compra1 compra2 compra3 compra4]]
    (testing "retorna [] quando lista está vazia"
      (is (= (logic.compra/lista-de-compras-por-intervalo-de-valores 1000000000M 0M [])
             [])))
    (testing "retorna lista com valores pertencentes ao intervalo"
      (is (= (logic.compra/lista-de-compras-por-intervalo-de-valores 100M 30M compras)
             [compra1 compra3])))))
;
(s.test/deftest gasto-por-categoria-test
  (let [compra1 (assoc compra-valida :compra/categoria "Saúde" :compra/valor 100M)
        compra2 (assoc compra-valida :compra/categoria "Educação" :compra/valor 200M)
        compra3 (assoc compra-valida :compra/categoria "Lazer" :compra/valor 300M)
        compra4 (assoc compra-valida :compra/categoria "Saúde" :compra/valor 400M)
        compras [compra1 compra2 compra3 compra4]]
    (testing "retorna {} quando lista está vazia"
      (is (= (logic.compra/gasto-por-categoria [])
             {})))
    (testing "retorna gasto por categoria"
      (is (= (logic.compra/gasto-por-categoria compras)
             {"Saúde" 500M "Educação" 200M "Lazer" 300M})))))
