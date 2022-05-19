;(ns yes-she-codes.week2.week2
;  (:require [clojure.test :as t]
;            [yes-she-codes.week2.model.model :as m]
;            [yes-she-codes.week2.logic.logic :as l]))
;
;
;
;(t/deftest novo-record-test
;  (t/testing "criando mapa de cartões dados os parametros adequados"
;    (let [compras [["2022-01-02" 260.00M "Dentista" "Saúde" 1234123412341234
;                    {:id nil :data "2022-01-02" :valor 260.00M :estabelecimento "Dentista" :categoria "Saúde" :cartao 1234123412341234}]
;                   ["2022-02-20" 79.90M "iFood" "Alimentação" 4321432143214321
;                    {:id nil :data "2022-02-20" :valor 79.90M :estabelecimento "iFood" :categoria "Alimentação" :cartao 4321432143214321}]
;                   ["2022-03-04" 250.00M "Hospital" "Saúde" 6655665566556655
;                    {:id nil :data "2022-03-04" :valor 250.00M :estabelecimento "Hospital" :categoria "Saúde" :cartao 6655665566556655}]
;                   ["2022-03-11" 25.90M "Dogão" "Alimentação" 3939393939393939
;                    {:id nil :data "2022-03-11" :valor 25.90M :estabelecimento "Dogão" :categoria "Alimentação" :cartao 3939393939393939}]]]
;      (doseq [[data valor estabelecimento categoria cartao estrutura-esperada] compras]
;        (t/is (= (m/novo-record-compra data valor estabelecimento categoria cartao) estrutura-esperada))))))
;
;
;
;;; null pointer exception!!!
;(t/deftest insere-compra-test
;
;  ;(t/testing "inclusao de record criado"
;  ;  (let [record   {:id nil :data "" :valor 0M :estabelecimento "estabelecimento" :categoria "categoria" :cartao 0}
;  ;        compras  [{:id 0 :data "" :valor 0M :estabelecimento "" :categoria "" :cartao 0}
;  ;                  {:id 1 :data "" :valor 0M :estabelecimento "" :categoria "" :cartao 0}
;  ;                  {:id 2 :data "" :valor 0M :estabelecimento "" :categoria "" :cartao 0}]
;  ;        resultado-esperado [{:id 0 :data "" :valor 0M :estabelecimento "" :categoria "" :cartao 0}
;  ;                            {:id 1 :data "" :valor 0M :estabelecimento "" :categoria "" :cartao 0}
;  ;                            {:id 2 :data "" :valor 0M :estabelecimento "" :categoria "" :cartao 0}
;  ;                            {:id 3 :data "" :valor 0M :estabelecimento "estabelecimento" :categoria "categoria" :cartao 0}]]
;  ;    (t/is (= (l/insere-record record compras) resultado-esperado))))
;
;  ;; VERIFICAR se inclusao de id existente deve de fato atualizar os valores
;  ;(t/testing "inclusao de record com id já existente"
;  ;  (let [record   {:id 1 :data "" :valor 5M :estabelecimento "estabelecimento" :categoria "categoria" :cartao 0}
;  ;        compras  [{:id 0 :data "" :valor 0M :estabelecimento "" :categoria "" :cartao 0}
;  ;                  {:id 1 :data "" :valor 0M :estabelecimento "" :categoria "" :cartao 0}
;  ;                  {:id 2 :data "" :valor 1M :estabelecimento "" :categoria "" :cartao 0}]
;  ;        resultado-esperado [{:id 0 :data "" :valor 0M :estabelecimento "" :categoria "" :cartao 0}
;  ;                            {:id 1 :data "" :valor 0M :estabelecimento "" :categoria "" :cartao 0}
;  ;                            {:id 2 :data "" :valor 5M :estabelecimento "" :categoria "" :cartao 0}]]
;  ;    (t/is (= (l/insere-record record compras) resultado-esperado))))
;  ;
;  ;(t/testing "inclusao de record quando compras esta vazia"
;  ;  (let [record   {:id nil :data "" :valor 0M :estabelecimento "estabelecimento" :categoria "categoria" :cartao 0}
;  ;        compras  []
;  ;        resultado-esperado [{:id 0 :data "" :valor 0M :estabelecimento "estabelecimento" :categoria "categoria" :cartao 0}]]
;  ;    (t/is (= (l/insere-record record compras) resultado-esperado))))
;
;
;  )
