(ns yes-she-codes.schema
  (:gen-class)
  (:use [clojure.pprint])
  (:require [schema.core :as s]))

;SEMANA 3 - SCHEMAS
(s/set-fn-validation! true)

;FUNCOES DE VALIDACAO

(defn maior-2-caracteres? [palavra]
  (< 2 (count palavra)))

(defn decimal-maior-igual-0? [numero]
  (and (decimal? numero) (<= 0 numero)))

(defn numero-entre-0-999? [numero]
  (and (>= 999 numero) (<= 0 numero)))

(defn valida-cpf? [cpf]
  (def pat (re-pattern "[0-9]{3}\\.[0-9]{3}\\.[0-9]{3}\\-[0-9]{2}"))
  (and (= 14 (count cpf)) (not (nil? (re-find pat cpf)))))

(defn valida-email? [email]
  (def pat (re-pattern "\\S+@\\S+\\.\\S+"))
  (not (nil? (re-find pat email))))

(defn valida-data-validade? [data]
  (def pat (re-pattern "[0-9]{4}-[0-9]{2}"))
  (and (= 7 (count data)) (not (nil? (re-find pat data)))))

(defn valida-data-compra? [data]
  (def pat (re-pattern "[0-9]{4}-[0-9]{2}-[0-9]{2}"))
  (and (= 10 (count data)) (not (nil? (re-find pat data)))))


;DEFINICOES VALIDACAO
(def Maior-2-caracteres (s/pred maior-2-caracteres?))
(def Valida-cpf (s/pred valida-cpf?))
(def Valida-email (s/pred valida-email?))
(def Valida-data-validade (s/pred valida-data-validade?))
(def Valida-data-compra (s/pred valida-data-compra?))
(def Numero-inteiro-entre-0-999 (s/constrained s/Int numero-entre-0-999?))
(def Positivo (s/pred pos?))
(def BigDecimal-maior-igual-0 (s/pred decimal-maior-igual-0?))
(def Positivo-e-Inteiro (s/pred pos-int?))


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
   :categoria Positivo
   ;Deve ser uma das opções: Alimentação, Automóvel, Casa, Educação, Lazer ou Saúde.
   :cartao Positivo-e-Inteiro
   })

(println (s/validate ClienteSchema {:nome "nome"
                                    :cpf  "000.000.000-00"
                                    :email "foobar@gmail.com"}))

(println (s/validate CartaoSchema {:numero 55
                                   :cvv 910
                                   :validade "2020-02"
                                   :limite 0M
                                   :cliente "000.000.000-00"}))

(println (s/validate CompraSchema {:data-da-compra "2022-04-12"
                                   :valor 15M
                                   :estabelecimento "IFood"
                                   :categoria 15
                                   :cartao 55}))


(s/defn novo-cliente :- ClienteSchema
  [nome :- Maior-2-caracteres
   cpf :- s/Str
   email :- s/Str]
  {:nome nome
   :cpf cpf
   :email email})

