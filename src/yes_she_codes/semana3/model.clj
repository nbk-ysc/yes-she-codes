(ns yes-she-codes.semana3.model
  (:require [schema.core :as s]
            [yes-she-codes.semana3.validations :as ysc.validations]
            [java-time :as time]))

(def ClienteSchema
  {:nome  (s/constrained s/Str ysc.validations/nome-valido?)
   :cpf   (s/constrained s/Str ysc.validations/cpf-valido?)
   :email (s/constrained s/Str ysc.validations/email-valido?)})

;(print (s/validate ClienteSchema {:nome "joao"
;                                  :cpf "111.111.111-11"
;                                  :email "joao@nubank.com.br"}))

(def CartaoSchema
  {:numero   (s/constrained Long ysc.validations/numero-cartao-valido?)
   :cvv      (s/constrained s/Int ysc.validations/cvv-valido?)
   :validade java.time.LocalDate
   :limite   (s/constrained BigDecimal pos?)
   :cliente  (s/constrained s/Str ysc.validations/cpf-valido?)})

;(print (s/validate CartaoSchema {:numero 1234123412341234
;                                 :cvv 123
;                                 :validade (time/local-date 2022 10)
;                                 :limite 2000
;                                 :cliente "111.111.111-11"}))

(def CompraSchema
  {:data (s/constrained java.time.LocalDate ysc.validations/data-da-compra-valida?)
   :valor (s/constrained BigDecimal pos?)
   :estabelecimento (s/constrained s/Str ysc.validations/nome-valido?)
   :categoria (s/constrained s/Str ysc.validations/categoria-valida?)
   :cartao (s/constrained Long ysc.validations/numero-cartao-valido?)})

;(print (s/validate CompraSchema {:data (time/local-date 2022 01 01)
;                                 :valor 25.50M
;                                 :estabelecimento "alura"
;                                 :categoria "Educação"
;                                 :cartao 1234123412341234}))