(ns yes-she-codes.semana3-test
  (:require [clojure.test :refer :all]
            [yes-she-codes.semana3 :refer :all]
            [schema.core :as s]))

(s/set-fn-validation! true)

(deftest validate-schema
  (testing "verifica cliente schema valido"
    (is (= (s/validate ClienteSchema
                       {:nome "Viúva Negra", :cpf "333.444.555-66", :email "viuva.casca.grossa@vingadoras.com.br"})
           {:nome "Viúva Negra", :cpf "333.444.555-66", :email "viuva.casca.grossa@vingadoras.com.br"})))


  (testing "verifica cliente schema invalido (nome não informado)"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate ClienteSchema
                             {:nome nil, :cpf "333.444.555-66", :email "viuva.casca.grossa@vingadoras.com.br"}))))

  (testing "verifica cliente schema invalido (cpf invalido)"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate ClienteSchema
                             {:nome "Viúva Negra", :cpf "333.444.555213", :email "viuva.casca.grossa@vingadoras.com.br"}))))

  (testing "verifica cliente schema invalido (email invalido) "
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate ClienteSchema
                             {:nome "Viúva Negra", :cpf "333.444.555-66", :email "viuva.casca.grossa"}))))

  (testing "verifica cartao schema valido"
    (is (= (s/validate CartaoSchema
                       {:numero 4321432143214321, :cvv 222, :validate "02/2222", :limite 2000.0, :cliente "333.444.555-66"})
           {:numero 4321432143214321, :cvv 222, :validate "02/2222", :limite 2000.0, :cliente "333.444.555-66"})))

  (testing "verifica cartao schema invalido (numero cartao negativo)"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CartaoSchema
                       {:numero -10, :cvv 222, :validate "02/2222", :limite 2000.0, :cliente "333.444.555-66"}))))

  (testing "verifica cartao schema invalido (cvv maior que 999)"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CartaoSchema
                       {:numero 4321432143214321, :cvv 1000, :validate "02/2222", :limite 2000.0, :cliente "333.444.555-66"}))))

  (testing "verifica cartao schema invalido (cvv maior que 999)"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CartaoSchema
                             {:numero 4321432143214321, :cvv 1000, :validate "02/2222", :limite 2000.0, :cliente "333.444.555-66"}))))

  (testing "verifica cartao schema invalido (validate incorreta)"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CartaoSchema
                             {:numero 4321432143214321, :cvv 1000, :validate "2002/22", :limite 2000.0, :cliente "333.444.555-66"}))))

  (testing "verifica cartao schema invalido (limite negativo)"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CartaoSchema
                             {:numero 4321432143214321, :cvv 1000, :validate "2002/22", :limite 2000.0, :cliente "333.444.555-66"}))))

  (testing "verifica cartao schema invalido (cpf cliente incorreto)"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CartaoSchema
                             {:numero 4321432143214321, :cvv 1000, :validate "2002/22", :limite 20000, :cliente "333.444"}))))

  (testing "verifica compra schema valido"
    (is (= (s/validate CompraSchema
                       {:data "22/05/2009", :valor 100M, :estabelecimento "Amazon", :categoria "Casa" :cartao 1234123412341234})
           {:data "22/05/2009", :valor 100M, :estabelecimento "Amazon", :categoria "Casa" :cartao 1234123412341234})))

  (testing "verifica compra schema invalido (data incorreta)"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CompraSchema
                       {:data "200", :valor 100M, :estabelecimento "Amazon", :categoria "Casa" :cartao 1234123412341234}))))

  (testing "verifica compra schema invalido (valor negativo)"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CompraSchema
                       {:data "22/05/2009", :valor -200, :estabelecimento "Amazon", :categoria "Casa" :cartao 1234123412341234}))))

  (testing "verifica compra schema invalido (estabelecimento string vazia)"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CompraSchema
                       {:data "22/05/2009", :valor 100M, :estabelecimento "", :categoria "Casa" :cartao 1234123412341234}))))

  (testing "verifica compra schema invalido (estabelecimento string vazia)"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CompraSchema
                             {:data "22/05/2009", :valor 100M, :estabelecimento "Amazon", :categoria "Mato" :cartao 1234123412341234}))))

  (testing "verifica compra schema invalido (numero cartao incorreto)"
    (is (thrown? clojure.lang.ExceptionInfo
                 (s/validate CompraSchema
                             {:data "22/05/2009", :valor 100M, :estabelecimento "", :categoria "Casa" :cartao 12})))))




