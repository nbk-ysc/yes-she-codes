(ns yes-she-codes.logic-test
  (:require [clojure.test :refer :all])
  (:require [yes-she-codes.schema-cliente :refer :all]))


(deftest validaSchemaTest

  (testing "Nome correto"
    (is (= "Carol" (validaNome "Carol"))))

  (testing "Nome incorreto"
    (is (thrown? clojure.lang.ExceptionInfo (validaNome "A"))))


  (testing "Cpf correto"
    (is (= "831.428.658-51" (validaCpf "831.428.658-51"))))

  (testing "Formato de cpf incorreto"
    (is (thrown? clojure.lang.ExceptionInfo (validaCpf "831"))))

  (testing "Email correto"
    (is (= "abc@nubank.com" (validaEmail "abc@nubank.com"))))

  (testing "Formato de email incorreto"
    (is (thrown? clojure.lang.ExceptionInfo (validaEmail "abc")))))



(println (validaSchemaTest))
