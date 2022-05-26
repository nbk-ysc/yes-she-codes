(ns yes-she-codes.schema
  ;(:gen-class)
  (:use [clojure.pprint])
  (:require [schema.core :as s]
            [yes-she-codes.util :as util]))

;SEMANA 3 - SCHEMAS
(s/set-fn-validation! true)

;DEFINICOES VALIDACAO
(def Maior-2-caracteres (s/pred util/maior-2-caracteres?))
(def Valida-cpf (s/pred util/valida-cpf?))
(def Valida-email (s/pred util/valida-email?))
(def Valida-data-validade (s/pred util/valida-data-validade?))
(def Valida-data-compra (s/pred util/valida-data-compra?))
(def Numero-inteiro-entre-0-999 (s/constrained s/Int util/numero-entre-0-999?))
(def BigDecimal-maior-igual-0 (s/pred util/decimal-maior-igual-0?))
(def Positivo-e-Inteiro (s/pred pos-int?))
(def Valida-categorias (s/pred util/valida-categorias?))


;DEFINIR SCHEMA PARA CLIENTE
(def ClienteSchema
  {:nome  Maior-2-caracteres
   :cpf Valida-cpf
   :email Valida-email
   })

;DEFINIR SCHEMA PARA CARTAO
(def CartaoSchema
  {:numero  Positivo-e-Inteiro
   :cvv Numero-inteiro-entre-0-999
   :validade  Valida-data-validade
   :limite BigDecimal-maior-igual-0
   :cliente Valida-cpf
   })

;DEFINIR SCHEMA PARA COMPRA
(def CompraSchema
  {:data-da-compra Valida-data-compra
   :valor BigDecimal-maior-igual-0
   :estabelecimento  Maior-2-caracteres
   :categoria Valida-categorias
   :cartao Positivo-e-Inteiro
   })


(s/validate ClienteSchema {:nome "Viúva Negra"
                           :cpf  "333.444.555-66"
                           :email "viuva.casca.grossa@vingadoras.com.br"})

(s/validate CartaoSchema {:numero 4321432143214321
                          :cvv 222
                          :validade "2024-02"
                          :limite 2000M
                          :cliente "333.444.555-66"})

(s/validate CompraSchema {:data-da-compra "2022-05-09"
                          :valor 100M
                          :estabelecimento "Amazon"
                          :categoria "Lazer"
                          :cartao 4321432143214321})

(s/defn novo-cliente :- ClienteSchema
  [nome :- Maior-2-caracteres
   cpf :- Valida-cpf
   email :- Valida-email]
  {:nome nome
   :cpf cpf
   :email email})

(println (novo-cliente "mari" "034.771.240-14" "mari@gmail.com"))


