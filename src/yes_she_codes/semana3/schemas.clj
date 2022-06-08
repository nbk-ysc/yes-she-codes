(ns yes-she-codes.semana3.schemas
  (:require [schema.core :as s]
            [yes-she-codes.semana3.logica :as y.logica]))

(s/set-fn-validation! true)


(def ClienteSchema
  {:nome  y.logica/valida-caracteres
   :cpf   y.logica/valida-cpf
   :email y.logica/valida-email})


(def CartaoSchema
  {:numero   y.logica/valida-numero-cartao
   :cvv      y.logica/valida-cvv
   :validade y.logica/valida-validade
   :limite   y.logica/valida-limite
   :cliente  y.logica/valida-cliente })


(def CompraSchema
  {:data-compra     y.logica/valida-data-compra
   :valor           y.logica/valida-valor
   :estabelecimento y.logica/valida-caracteres
   :categoria       y.logica/valida-categoria
   :cartao          y.logica/valida-numero-cartao})


(s/validate CompraSchema {:data-compra "2022-05-09" :valor 100M :estabelecimento "Amazon" :categoria "Casa" :cartao 4321432143214321})