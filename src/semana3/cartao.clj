(ns semana3.cartao
  (:require [schema.core :as s
             :include-macros true]
            [semana3.logic :as s3.logic])
  (:use [clojure pprint]))

(s/set-fn-validation! true)

(def CartaoSchema
  "Schema de um cartão"
  {:numero   s3.logic/Cartao-valido?
   :cvv      s3.logic/Cvv-valido?
   :validade s3.logic/VerificaData?
   :limite   s3.logic/Valor-BigDecimal-positivo-ou-zero?
   :cliente  s3.logic/CpfFormat
   })


(s/defn novo-cartao :- CartaoSchema
  [numero :- s3.logic/Cartao-valido?
   cvv :- s3.logic/Cvv-valido?
   validade :- s3.logic/VerificaData?
   limite :- s3.logic/Valor-BigDecimal-positivo-ou-zero?
   cliente :- s3.logic/CpfFormat]
  {:numero numero, :cvv cvv, :validade validade, :limite limite, :cliente cliente})


;(pprint (s/explain CartaoSchema))
;(pprint (s/validate CartaoSchema {:numero 7686831, :cvv 322, :validade "2022-10", :limite 200, :cliente "243.321.323-00"}))
;
;(pprint (novo-cartao 7686831 43 "2022-10" 500 "352.656.659-89"))