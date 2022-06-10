(ns yes-she-codes.semana3.schemas
  (:require [schema.core :as s]
            [yes-she-codes.utilities.logica :as y.logica]))

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


(s/defn ->Compra :- CompraSchema
  [data             :- y.logica/valida-data-compra
   valor            :- y.logica/valida-valor
   estabelecimento  :- y.logica/valida-caracteres
   categoria        :- y.logica/valida-categoria
   cartao           :- y.logica/valida-numero-cartao]
  {:data-compra     data
   :valor           valor
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          cartao})


(->Compra "2022-05-09"  100M  "Amazon"  "Casa"  4321432143214321)

(defn processa-arquivo-de-compras! []
  (->> (y.logica/processa-csv "dados/compras.csv")
       (map #(y.logica/converte-valores-na-linha y.logica/csv->compra %))
       (map #(apply ->Compra %))
       vec))