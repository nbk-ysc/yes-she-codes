(ns semana3.cliente
  (:require [schema.core :as s
             :include-macros true]
            [semana3.logic :as s3.logic])
  (:use [clojure pprint]))

(s/set-fn-validation! true)

(def ClienteSchema
  "Schema de um cliente"
  {:nome s3.logic/Possui-2-caracteres,
   :cpf s3.logic/CpfFormat,
   :email s3.logic/EmailFormat
   })


(s/defn novo-cliente :- ClienteSchema
        [nome :- s3.logic/Possui-2-caracteres,
         cpf :- s3.logic/CpfFormat,
         email :- s3.logic/EmailFormat]
        {:nome nome, :cpf cpf, :email email})
;
;(pprint (s/explain ClienteSchema))
;(pprint (s/validate ClienteSchema {:nome "marilia", :cpf "352.956.989-98", :email "ahiuhsu@jdaios.com"}))
;
;(pprint (novo-cliente "gustavo" "352.656.659-89" "ggggg@huih.com"))