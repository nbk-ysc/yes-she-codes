(ns yes_she_codes.semana3.logic
  (:require [java-time :as time]
            [clojure.string :as str]
            [yes_she_codes.semana3.model :refer :all]))

(defn limpa-whitespace
  [string]
  (str/replace string " " ""))

(s/defn novo-cliente :- ClienteSchema
  [nome cpf email]
  {:nome nome
   :cpf cpf
   :email email})

(s/defn novo-cartao :- CartaoSchema
  [numero_espaco cvv validade limite cliente]
  (let [numero (limpa-whitespace numero_espaco)]
    {:numero    (int numero)
     :cvv       (int numero)
     :validade  (time/year-month "yyyy-MM" validade)
     :limite    (bigdec limite)
     :cliente   cliente}))

(s/defn nova-compra
  [data valor estabelecimento categoria cartao_espaco]
  (let [cartao (limpa-whitespace cartao_espaco)]
    {:data data
     :valor (bigdec valor)
     :estabelecimento estabelecimento
     :categoria categoria
     :cartao (long (bigdec cartao))}))