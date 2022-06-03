(ns yes_she_codes.semana3.logic
  (:require [java-time :as time]
            [yes_she_codes.semana3.model :refer :all]))

(defn novo-cliente
  [nome cpf email]
  {:nome nome
   :cpf cpf
   :email email})

(defn novo-cartao :- CartaoSchema
  [numero_espaco cvv validade limite cliente]
  (let [numero (limpa-whitespace numero_espaco)]
    {:numero    (int numero)
     :cvv       (int numero)
     :validade  (time/year-month "yyyy-MM" validade)
     :limite    (bigdec limite)
     :cliente   cliente}))

(defn nova-compra
  [data valor estabelecimento categoria cartao_espaco]
  (let [cartao (limpa-whitespace cartao_espaco)]
    {:data data
     :valor (bigdec valor)
     :estabelecimento estabelecimento
     :categoria categoria
     :cartao (long (bigdec cartao))}))