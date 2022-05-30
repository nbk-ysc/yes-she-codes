(ns yes-she-codes.core-test
  (:require [clojure.test :refer :all]
            [schema.core :as s]
            [yes-she-codes.semana1.compras :as y.compras]
            [yes-she-codes.semana1.core :as y.core]
            [yes_she_codes.semana3.cartao :refer :all]
            [yes_she_codes.semana3.cliente :refer :all]
            [yes_she_codes.semana3.compra :refer :all]))

#_(deftest compras-cartao
    (let [cartao 1234123412341234
          compras (y.compras/lista-compra lista-compras)]
      (is (= 409.9 (y.core/soma-compras-cartao cartao compras)))))

#_(deftest cacula-fatura-teste
    (let [mes 1
          cartao 1234123412341234
          compras (y.compras/lista-compra lista-compras)]
      (is (= 389.9 (y.core/cacula-fatura-mes mes cartao compras)))
      (is (= 20.0 (y.core/cacula-fatura-mes 2 cartao compras)))))

#_(deftest compras-estabelecimento
    (let [estabelecimento "Outback"
          compras (y.compras/lista-compra lista-compras)]
      (is (= {:estabelecimento "Outback", :gasto-total 129.9} (y.core/soma-compras-estabelecimento estabelecimento compras)))
      ))

(deftest testando-schema-cliente
  (testing "testa se o schema do cliente aceita todos os campos validos"

    (let [cliente-valido {:id    1,
                          :nome  "Caroline",
                          :cpf   "123.123.123-12",
                          :email "caroline@nubank.com.br"}]
      (is (= (s/validate ClienteSchema cliente-valido)
             cliente-valido))))

  (testing "Deve lançar erro se o nome estiver com formato errado"
    (let [cliente-com-nome-invalido {:id    1,
                                     :nome  nil,
                                     :cpf   "12312312312",
                                     :email "caroline@nubank.com.br"}]
      (is (thrown? clojure.lang.ExceptionInfo (s/validate ClienteSchema cliente-com-nome-invalido)
                   cliente-com-nome-invalido))))

  (testing "Deve lançar erro se o cpf estiver com formato errado"
    (let [cliente-com-cpf-invalido {:id    1,
                                    :nome  "Caroline",
                                    :cpf   "12312312312",
                                    :email "caroline@nubank.com.br"}]
      (is (thrown? clojure.lang.ExceptionInfo (s/validate ClienteSchema cliente-com-cpf-invalido)
                   cliente-com-cpf-invalido))))

  (testing "Deve lançar erro se o email estiver com formato errado"
    (let [cliente-com-email-invalido {:id    1,
                                      :nome  "Caroline",
                                      :cpf   "123.123.123-12",
                                      :email "carolienubank.com.br"}]
      (is (thrown? clojure.lang.ExceptionInfo (s/validate ClienteSchema cliente-com-email-invalido)
                   cliente-com-email-invalido)))))


(deftest testando-schema-cartao
  (testing "testa se o schema do cartao aceita todos os parametros válidos"
    (let [cartao-valido {:num-cartao 1234123412341234,
                         :cvv        206,
                         :validade   "2022-03",
                         :limite     100,
                         :cliente    "123.123.123-12"}]
      (is (= (s/validate CartaoSchema cartao-valido)
             cartao-valido))))

  (testing "Deve dar erro se o formato do cartao estiver errado"
    (let [cartao-invlalido {:num-cartao -123,
                            :cvv        206,
                            :validade   "2022-03",
                            :limite     100,
                            :cliente    "123.123.123-12"}]
      (is (thrown? clojure.lang.ExceptionInfo (s/validate CartaoSchema cartao-invlalido)
                   cartao-invlalido))))

  (testing "Deve dar erro se o formato do cvv estiver errado"
    (let [cartao-cvv-invlalido {:num-cartao 1234123412341234,
                                :cvv        1000,
                                :validade   "2022-03",
                                :limite     100,
                                :cliente    "123.123.123-12"}]
      (is (thrown? clojure.lang.ExceptionInfo (s/validate CartaoSchema cartao-cvv-invlalido)
                   cartao-cvv-invlalido))))

  (testing "Deve dar erro se o formato da validade estiver errado"
    (let [cartao-validade-invlalido {:num-cartao 1234123412341234,
                                     :cvv        206,
                                     :validade   "20-03",
                                     :limite     100,
                                     :cliente    "123.123.123-12"}]
      (is (thrown? clojure.lang.ExceptionInfo (s/validate CartaoSchema cartao-validade-invlalido)
                   cartao-validade-invlalido))))


  (testing "Deve dar erro se o formato do limite estiver errado"
    (let [cartao-limite-invlalido {:num-cartao 1234123412341234,
                                   :cvv        206,
                                   :validade   "2022-03",
                                   :limite     -12,
                                   :cliente    "123.123.123-12"}]
      (is (thrown? clojure.lang.ExceptionInfo (s/validate CartaoSchema cartao-limite-invlalido)
                   cartao-limite-invlalido))))


  (testing "Deve dar erro se o formato do cliente estiver errado"
    (let [cartao-cliente-invlalido {:num-cartao 1234123412341234,
                                    :cvv        206,
                                    :validade   "2022-03",
                                    :limite     100,
                                    :cliente    "12312312312"}]
      (is (thrown? clojure.lang.ExceptionInfo (s/validate CartaoSchema cartao-cliente-invlalido)
                   cartao-cliente-invlalido)))))


(deftest testando-schema-compra
  (testing "testa se o schema da compra aceita todos os campos validos"
    (let [compra-valida {:data            "2022-01-02",
                         :valor           345.83,
                         :estabelecimento "Outback",
                         :categoria       "Alimentação",
                         :cartao          1234123412341234}]
      (is (= (s/validate CartaoSchema compra-valida)
             compra-valida))))

  (testing "Deve dar erro se o formato da data estiver errado"
    (let [cartao-data-invalido {:data            "2022-02",
                                :valor           345.83,
                                :estabelecimento "Outback",
                                :categoria       "Alimentação",
                                :cartao          1234123412341234}]
      (is (thrown? clojure.lang.ExceptionInfo (s/validate CompraSchema cartao-data-invalido)
                   cartao-data-invalido))))


  (testing "Deve dar erro se o formato do valor estiver errado"
    (let [cartao-valor-invalido {:data            "2022-01-02",
                                 :valor           -15,
                                 :estabelecimento "Outback",
                                 :categoria       "Alimentação",
                                 :cartao          1234123412341234}]
      (is (thrown? clojure.lang.ExceptionInfo (s/validate CompraSchema cartao-valor-invalido)
                   cartao-valor-invalido))))

  (testing "Deve dar erro se o formato do estabelecimento estiver errado"
    (let [cartao-estabelecimento-invalido {:data            "2022-01-02",
                                           :valor           345.83,
                                           :estabelecimento "",
                                           :categoria       "Alimentação",
                                           :cartao          1234123412341234}]
      (is (thrown? clojure.lang.ExceptionInfo (s/validate CompraSchema cartao-estabelecimento-invalido)
                   cartao-estabelecimento-invalido))))


  (testing "Deve dar erro se o formato do categoria estiver errado"
    (let [cartao-categoria-invalido {:data            "2022-01-02",
                                     :valor           345.83,
                                     :estabelecimento "Outback",
                                     :categoria       "shopping",
                                     :cartao          1234123412341234}]
      (is (thrown? clojure.lang.ExceptionInfo (s/validate CompraSchema cartao-categoria-invalido)
                   cartao-categoria-invalido))))

  (testing "Deve dar erro se o formato do cartao estiver errado"
    (let [cartao-cartao-invalido {:data            "2022-01-02",
                                  :valor           345.83,
                                  :estabelecimento "Outback",
                                  :categoria       "Alimentação",
                                  :cartao          12341234}]
      (is (thrown? clojure.lang.ExceptionInfo (s/validate CompraSchema cartao-cartao-invalido)
                   cartao-cartao-invalido)))))


(clojure.test/run-all-tests)