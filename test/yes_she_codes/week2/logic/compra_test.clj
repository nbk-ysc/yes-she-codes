(ns yes-she-codes.week2.logic.compra-test
  (:require [clojure.test :as test]
            [yes-she-codes.week2.logic.compra :as logic.compra]
            [java-time :as time]))

(test/deftest insere-compra-test

  (let [data-futura    (time/local-date "2100-01-01")
        data-passada   (time/local-date "2021-01-01")
        compras        [{:id 0 :data "" :valor 0M :estabelecimento "" :categoria "" :cartao 0}
                        {:id 0 :data "" :valor 0M :estabelecimento "" :categoria "" :cartao 0}]
        tamanho-lista  (count compras)
        record-ok      {:id nil :data data-passada :valor 10M, :estabelecimento "Alura", :categoria "Educação", :cartao 0}
        erro-data      {:id nil :data data-futura :valor 10M, :estabelecimento "Alura", :categoria "Educação", :cartao 0}
        erro-valor     {:id nil :data data-passada :valor -10M, :estabelecimento "Alura", :categoria "Educação", :cartao 0}
        erro-estabel   {:id nil :data data-passada :valor 10M, :estabelecimento "A", :categoria "Educação", :cartao 0}
        erro-categoria {:id nil :data data-passada :valor 10M, :estabelecimento "Alura", :categoria "Educ", :cartao 0}]

    (test/testing "inserindo uma compra valida"
      (test/is (=
                 (count (logic.compra/insere-compra compras record-ok))
                 (inc tamanho-lista))))

    (test/testing "compra com data maior que hoje"
      (test/is (thrown?
                 java.lang.AssertionError
                 (logic.compra/insere-compra compras erro-data))))

    (test/testing "compra com valor que não seja bicdec positivo"
      (test/is (thrown?
                 java.lang.AssertionError
                 (logic.compra/insere-compra compras erro-valor))))

    (test/testing "compra com estabelecimento com menos que dois chars"
      (test/is (thrown?
                 java.lang.AssertionError
                 (logic.compra/insere-compra compras erro-estabel))))

    (test/testing "compra com categoria fora de opções"
      (test/is (thrown?
                 java.lang.AssertionError
                 (logic.compra/insere-compra compras erro-categoria))))))

(test/deftest insere-compra!-test
  (let [record   {:id nil :data (time/local-date "2022-01-01") :valor 10M :estabelecimento "Alura" :categoria "Educação" :cartao 0}
        compras  (atom [])]
    (dotimes [_ 10] (logic.compra/insere-compra! compras record))
    (test/is (= (count @compras) 10))))

(test/deftest exclui-compra!-test
  (let [record   {:id nil :data (time/local-date "2022-01-01") :valor 10M :estabelecimento "Alura" :categoria "Educação" :cartao 0}
        compras  (atom [])]
    (dotimes [_ 10] (logic.compra/insere-compra! compras record))
    (doseq [n [1 2 5 7] ] (logic.compra/exclui-compra! compras n))
    (test/is (= (count @compras) 6))

    (test/testing "pesquisa-compra-por-id!"
      (test/is (= (logic.compra/pesquisa-compra-por-id! compras 2) nil))
      (test/is (= (logic.compra/pesquisa-compra-por-id! compras 4) (assoc record :id 4))))))























