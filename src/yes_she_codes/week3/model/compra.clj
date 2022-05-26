(ns yes-she-codes.week3.model.compra
  (:require [schema.core :as s]
            [yes-she-codes.week3.model.constrains.constrains :as constrains]
            [yes-she-codes.week3.model.cartao :as model.cartao])
  (:import (java.time LocalDate)))

(def Data            (s/constrained LocalDate constrains/data-menor-igual-a-hoje?))
(def Estabelecimento (s/constrained s/Str constrains/pelo-menos-dois-chars?))
(def Categoria       (s/enum "Alimentação", "Automóvel", "Casa", "Educação", "Lazer", "Saúde"))

(def CompraSchema
  {:data            Data
   :valor           model.cartao/ValorFinanceiro
   :estabelecimento Estabelecimento
   :categoria       Categoria
   :cartao          model.cartao/NumeroCartao})
