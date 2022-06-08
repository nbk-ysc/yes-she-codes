(ns yes-she-codes.semana3.logica
  (:require [schema.core :as s]
            [yes-she-codes.utilities.logica :as y.utilities ])
  (:import java.math.BigDecimal )  )

(s/set-fn-validation! true)
(def categorias-validas  #{"Alimentação","Automóvel","Casa","Educação","Lazer","Saúde"} )

(defn categoria-valida? [categoria]
  (contains? categorias-validas categoria))


(def valida-caracteres (s/constrained s/Str y.utilities/pelo-menos-2-caracteres? 'pelo-menos-2-caracteres))

(def valida-cpf (s/constrained s/Str y.utilities/cpf-valido? 'cpf-valido))

(def valida-email (s/constrained s/Str y.utilities/email-valido? 'email-valido))

(def valida-numero-cartao (s/constrained s/Int #(y.utilities/valor-esta-no-intervalo? % 0 10000000000000001) 'numero-valido))

(def valida-cvv (s/constrained s/Int #(y.utilities/valor-esta-no-intervalo? % 0 1000) 'cvv-valido))

(def valida-validade (s/constrained s/Str #(y.utilities/data-no-formato-yyyy-mm? %) 'validade-valida))

(def valida-limite (s/constrained BigDecimal #(>= % 0)))

(def valida-valor(s/constrained BigDecimal #(> % 0)))

(def valida-cliente (s/constrained s/Str #(y.utilities/cpf-valido? %) 'cliente-valido))

(def valida-data-compra (s/constrained s/Str #(y.utilities/data-no-formato-yyyy-mm-dd? %) 'data-valida))


(def valida-categoria (s/pred categoria-valida? ))







