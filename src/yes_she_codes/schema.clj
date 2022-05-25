(ns yes-she-codes.schema
  ;(:gen-class)
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

(defn valida-categorias? [categoria]
  (contains? #{"Alimentação", "Automóvel", "Casa", "Educação", "Lazer", "Saúde"} categoria))



;DEFINICOES VALIDACAO
(def Maior-2-caracteres (s/pred maior-2-caracteres?))
(def Valida-cpf (s/pred valida-cpf?))
(def Valida-email (s/pred valida-email?))
(def Valida-data-validade (s/pred valida-data-validade?))
(def Valida-data-compra (s/pred valida-data-compra?))
(def Numero-inteiro-entre-0-999 (s/constrained s/Int numero-entre-0-999?))
(def BigDecimal-maior-igual-0 (s/pred decimal-maior-igual-0?))
(def Positivo-e-Inteiro (s/pred pos-int?))
(def Valida-categorias (s/pred valida-categorias?))


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


(println (s/validate ClienteSchema {:nome "Viúva Negra"
                                    :cpf  "333.444.555-66"
                                    :email "viuva.casca.grossa@vingadoras.com.br"}))

(println (s/validate CartaoSchema {:numero 4321432143214321
                                   :cvv 222
                                   :validade "2024-02"
                                   :limite 2000M
                                   :cliente "333.444.555-66"}))

(println (s/validate CompraSchema {:data-da-compra "2022-05-09"
                                   :valor 100M
                                   :estabelecimento "Amazon"
                                   :categoria "Lazer"
                                   :cartao 4321432143214321}))


(s/defn novo-cliente :- ClienteSchema
  [nome :- Maior-2-caracteres
   cpf :- Valida-cpf
   email :- Valida-email]
  {:nome nome
   :cpf cpf
   :email email})

(pprint (novo-cliente "Viúva Negra"
               "333.444.555-66"
               "viuva.casca.grossa@vingadoras.com.br"))

