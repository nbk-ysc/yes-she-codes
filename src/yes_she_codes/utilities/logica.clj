(ns yes-she-codes.utilities.logica
  (:use clojure.pprint)
  (:require [schema.core :as s]))

(defn str->long [valor]
  (Long/parseLong (clojure.string/replace valor #" " "")))

(defn get-valor
  [compra]
  (get compra :valor 0))

(defn get-data
  [compra]
  (get compra :data 0))

(defn get-mes
  [data]
  (subs data 5 7))

(defn filtro-mes [data mes]
  (-> (get-mes data)
      (= mes)))

(defn valor-esta-no-intervalo?
  [inicio fim compra]
  (<= inicio  (get compra :valor 0) fim ))

(defn valor-gasto-por-categoria
  [compras]
  (->> compras
       (map get-valor)
       (reduce +)))

(defn gasto-por-categoria
  [[categoria compras]]
  { :categoria categoria
   :total-gasto (valor-gasto-por-categoria compras)})



(defn pelo-menos-2-caracteres? [palavra]
  (>= (count palavra) 2))

(defn cpf-valido? [cpf]
  (re-matches #"\d{3}\.\d{3}\.\d{3}\-\d{2}" cpf))

(defn data-no-formato-yyyy-mm? [data]
  (re-matches #"\S{4}-\S{2}" data))

(defn data-no-formato-yyyy-mm-dd? [data]
  (re-matches #"\S{4}-\S{2}-\S{2}" data))


(defn email-valido? [email]
  (re-matches #"\S+@\S+\.\S+" email))

(defn valor-esta-no-intervalo?
  [valor inicio fim]
  (<= inicio valor fim))


(defn processa-csv [caminho-arquivo]
  (->> (slurp caminho-arquivo)
       clojure.string/split-lines
       rest
       (map #(clojure.string/split % #","))))

(defn converte-valores-na-linha [funcoes-de-conversao linha]
  (map #(%1 %2) funcoes-de-conversao linha))

(def csv->compra [identity
                  bigdec
                  identity
                  identity
                  str->long])

(s/set-fn-validation! true)
(def categorias-validas  #{"Alimentação","Automóvel","Casa","Educação","Lazer","Saúde"} )

(defn categoria-valida? [categoria]
  (contains? categorias-validas categoria))


(def valida-caracteres (s/constrained s/Str pelo-menos-2-caracteres? 'pelo-menos-2-caracteres))

(def valida-cpf (s/constrained s/Str cpf-valido? 'cpf-valido))

(def valida-email (s/constrained s/Str email-valido? 'email-valido))

(def valida-numero-cartao (s/constrained s/Int #(valor-esta-no-intervalo? % 0 10000000000000001) 'numero-valido))

(def valida-cvv (s/constrained s/Int #(valor-esta-no-intervalo? % 0 1000) 'cvv-valido))

(def valida-validade (s/constrained s/Str #(data-no-formato-yyyy-mm? %) 'validade-valida))

(def valida-limite (s/constrained BigDecimal #(>= % 0)))

(def valida-valor(s/constrained BigDecimal #(> % 0)))

(def valida-cliente (s/constrained s/Str #(cpf-valido? %) 'cliente-valido))

(def valida-data-compra (s/constrained s/Str #(data-no-formato-yyyy-mm-dd? %) 'data-valida))

(def valida-categoria (s/pred categoria-valida? ))


