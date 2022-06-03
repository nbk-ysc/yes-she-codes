(ns yes-she-codes.dominio.cliente
  (:use clojure.pprint)
  (:require [schema.core :as s]
            [yes-she-codes.logic.logic :as y.logic]))

(defrecord Cliente [id nome cpf email])

(s/set-fn-validation! true)

(def ClienteSchema
  {(s/optional-key :id) (s/pred pos-int?)
   :nome  y.logic/NomeValido,
   :cpf   y.logic/CpfValido,
   :email y.logic/EmailValido})

(s/defn novo-cliente :- ClienteSchema
  [id nome :- y.logic/NomeValido, cpf :- y.logic/CpfValido, email :- y.logic/EmailValido]
  {:id id,
   :nome  nome,
   :cpf   cpf,
   :email email})

;(pprint (novo-cliente "CANDY" "237.776.298-04" "candy.gonzales@nubank.com.br"))

;(pprint (s/validate ClienteSchema {:nome "negra" :cpf "333.444.555-66" :email "viuva.casca.grossa@vingadoras.com.br"}))
;
;(pprint (= ({:nome  "negra",
;              :cpf   "333.444.555-66",
;              :email "viuva.casca.grossa@vingadoras.com.br"}) ({:nome  "negra",
;                                                                           :cpf   "333.444.555-66",
;                                                                           :email "viuva.casca.grossa@vingadoras.com.br"}) ))