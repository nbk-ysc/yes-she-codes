(ns yes-she-codes.study.week2.logic.common.common-test
  (:require [clojure.test :as test]
            [yes-she-codes.study.week2.logic.common.common :as logic.common]))


(test/deftest insere-record-test
  (let [record {:id nil :key1 "val3" :key2 "val3" :key3 "val3"}
        entidade [{:id 1 :key1 "val1" :key2 "val1" :key3 "val1"}
                  {:id 2 :key1 "val2" :key2 "val2" :key3 "val2"}]
        lista-esperada [{:id 1 :key1 "val1" :key2 "val1" :key3 "val1"}
                        {:id 2 :key1 "val2" :key2 "val2" :key3 "val2"}
                        {:id 3 :key1 "val3" :key2 "val3" :key3 "val3"}]]
    (test/is (= (logic.common/insere-record entidade record) lista-esperada))))

(test/deftest pesquisa-record-por-id
  (let [entidade [{:id 1 :key1 "val1" :key2 "val1" :key3 "val1"}
                  {:id 2 :key1 "val2" :key2 "val2" :key3 "val2"}
                  {:id 3 :key1 "val3" :key2 "val3" :key3 "val3"}]
        id-2 {:id 2 :key1 "val2" :key2 "val2" :key3 "val2"}]
    (test/is (= (logic.common/pesquisa-record-por-id entidade 2) id-2))))

(test/deftest exclui-record-test
  (let [entidade [{:id 1 :key1 "val1" :key2 "val1" :key3 "val1"}
                  {:id 2 :key1 "val2" :key2 "val2" :key3 "val2"}
                  {:id 3 :key1 "val3" :key2 "val3" :key3 "val3"}]
        pos-exclusao [{:id 1 :key1 "val1" :key2 "val1" :key3 "val1"}
                      {:id 3 :key1 "val3" :key2 "val3" :key3 "val3"}]]
    (test/is (= (logic.common/exclui-record entidade 2) pos-exclusao))
    (test/is (= (logic.common/exclui-record entidade 10) entidade))))
