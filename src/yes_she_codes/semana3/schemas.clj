(ns yes-she-codes.semana3.schemas
  (:use clojure.pprint)
  (:require [schema.core :as s]
            [yes-she-codes.semana3.logic :as y.logic]))

(s/set-fn-validation! true)


(def ClienteSchema
  {(s/optional-key :id) (s/pred pos-int?)
   :nome (s/constrained s/Str y.logic/count-maior-ou-igual-a-dois?)
   :cpf (s/constrained s/Str y.logic/cpf-valido?)
   :email (s/constrained s/Str y.logic/email-valido?)})

(def CartaoSchema
  {:numero (s/constrained s/Int y.logic/numero-valido?)
   :cvv (s/constrained s/Int y.logic/cvv-valido?)
   :validade (s/constrained s/Str y.logic/validade-valida?)
   :limite (s/constrained BigDecimal y.logic/limite-valido?)
   :cliente (s/constrained s/Str y.logic/cpf-valido?)})

(def CompraSchema
  {:data (s/constrained s/Str y.logic/data-valida?)
   :valor (s/constrained BigDecimal y.logic/valor-valido?)
   :estabelecimento (s/constrained s/Str y.logic/count-maior-ou-igual-a-dois?)
   :categoria (s/constrained s/Str y.logic/categoria-valida?)
   :cartao (s/constrained s/Int y.logic/numero-valido?)})








