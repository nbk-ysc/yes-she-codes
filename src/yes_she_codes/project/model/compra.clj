(ns yes-she-codes.project.model.compra
  (:require [schema.core :as s]
            [yes-she-codes.project.model.constrains.constrains :as constrains]
            [yes-she-codes.project.model.cartao :as model.cartao])
  (:import (java.time LocalDate)))

(s/defschema Data
  (s/constrained
    LocalDate
    constrains/data-menor-igual-a-hoje?))

(s/defschema Estabelecimento
  (s/constrained
    s/Str
    constrains/pelo-menos-dois-chars?))

(def tipos-categoria
  #{"Alimentação", "Automóvel", "Casa", "Educação", "Lazer", "Saúde"})

(s/defschema Categoria
  (apply s/enum tipos-categoria))

(s/defschema Compra
  {:data            Data
   :valor           model.cartao/ValorFinanceiro
   :estabelecimento Estabelecimento
   :categoria       Categoria
   :cartao          model.cartao/NumeroCartao})

(s/defschema Compras
  [Compra])

(s/defschema GastoCategoria
  {Categoria model.cartao/ValorFinanceiro})