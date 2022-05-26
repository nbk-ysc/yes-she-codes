(ns yes-she-codes.week3
  (:use clojure.pprint)
  (:require [schema.core :as s]))

(s/set-fn-validation! true)

(def valida-cpf (s/pred #(re-matches #"\d{3}.\d{3}.\d{3}-\d{2}" %)))

(def valida-email (s/pred #(re-matches #".+\@.+\..+" %)))   ;melhorar


(def de-0-a-999 #(and (>= % 0)
                      (<= % 999)))

(def max-16-algarismos #(and (>= % 0)
                             (< % 10000000000000000)))

(def formato-ano-mes (s/pred #(re-matches #"\d{4}-\d{2}" %)))

(def formato-ano-mes-dia (s/pred #(re-matches #"\d{4}-\d{2}-\d{2}" %)))

(def maior-ou-igual-zero #(>= % 0))

(def valida-categoria (s/pred #(#{"Alimentação", "Automóvel", "Casa",
                                  "Educação", "Lazer", "Saúde"} %)))


(def ClienteSchema
  "Schema para um cliente"
  {:nome  s/Str,
   :cpf   valida-cpf,
   :email valida-email})

(def CartaoSchema
  "Schema para um cartão"
  {:numero    (s/constrained s/Int max-16-algarismos),
   :cvv       (s/constrained s/Int de-0-a-999)
   :validade  formato-ano-mes
   :limite    (s/constrained BigDecimal maior-ou-igual-zero)
   :cliente   valida-cpf})

(def CompraSchema
  "Schema para uma compra"
  {:data              formato-ano-mes-dia,
   :valor             (s/constrained BigDecimal pos?)
   :estabelecimento   (s/constrained s/Str #(> (count %) 2))
   :categoria         valida-categoria
   :cartao            (s/constrained s/Int max-16-algarismos)})



