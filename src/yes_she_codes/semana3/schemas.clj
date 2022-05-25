(ns yes-she-codes.semana3.schemas
  (:use clojure.pprint)
  (:require [schema.core :as s]
            [yes-she-codes.semana3.logic :as y.logic]))

(s/set-fn-validation! true)


(def CountMaiorOuIgualADois (s/pred y.logic/count-maior-ou-igual-a-dois?))

(def CpfValido (s/pred y.logic/cpf-valido?))

(def EmailValido (s/pred y.logic/email-valido?))

(def NumeroValido (s/pred y.logic/numero-valido?))

(def CvvValido (s/pred y.logic/cvv-valido?))

(def ValidadeValida (s/pred y.logic/validade-valida?))

(def LimiteValido (s/pred y.logic/limite-valido?))

(def DataValida (s/pred y.logic/data-valida?))

(def ValorValido (s/pred y.logic/valor-valido?))

(def CategoriaValida (s/pred y.logic/categoria-valida?))



(def ClienteSchema
  {:nome CountMaiorOuIgualADois
   :cpf CpfValido
   :email EmailValido})

(def CartaoSchema
  {:numero NumeroValido
   :cvv CvvValido
   :validade ValidadeValida
   :limite LimiteValido
   :cliente CpfValido})

(def CompraSchema
  {:data DataValida
   :valor ValorValido
   :estabelecimento CountMaiorOuIgualADois
   :categoria CategoriaValida
   :cartao NumeroValido})








