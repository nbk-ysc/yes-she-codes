(ns yes-she-codes.persistencia.cartao
  (:require [yes-she-codes.logic :as logic]
            [yes-she-codes.dominio.cartao :as dominio.cartao]
            [yes-she-codes.db.cartao :as db.cartao]
            [schema.core :as s]))

(s/defn novo-cartao :- dominio.cartao/CartaoSchema
  [[numero cvv validate limite cliente]]
  (let [format-numero (logic/format-numero numero)
        format-data (logic/format-data-cartao validate)]
    (dominio.cartao/->Cartao nil format-numero cvv format-data limite cliente)))

(defn parametros-cartoes []
  (let [cartoes (db.cartao/param-cartoes)]
    (vec (map novo-cartao cartoes))))

(defn lista-cartoes []
  (parametros-cartoes))

(defn agregate-cartao [cpf]
  {:cartao (into {} (logic/filtra-data :cliente cpf (lista-cartoes)))})
