(ns yes-she-codes.project.model.compra
  (:require [schema.core :as s]
            [yes-she-codes.project.model.constraints.constraints :as constraints]
            [yes-she-codes.project.model.cartao :as model.cartao])
  (:import (java.time LocalDate)))

(s/defschema Data
  (s/constrained
    LocalDate
    constraints/data-menor-igual-a-hoje?))

(s/defschema Estabelecimento
  (s/constrained
    s/Str
    constraints/pelo-menos-dois-chars?))

(def tipos-categoria
  #{"Alimentação", "Automóvel", "Casa", "Educação", "Lazer", "Saúde"})

(s/defschema Categoria
  (apply s/enum tipos-categoria))

(s/defschema Compra
  {:compra/id              s/Uuid
   :compra/data            Data
   :compra/valor           model.cartao/ValorFinanceiro
   :compra/estabelecimento Estabelecimento
   :compra/categoria       Categoria
   :compra/cartao          model.cartao/NumeroCartao})

(s/defschema GastoCategoria
  {Categoria model.cartao/ValorFinanceiro})

(s/defschema CompraComComponentes
  {:compra/id              s/Uuid
   :compra/data            Data
   :compra/valor           model.cartao/ValorFinanceiro
   :compra/estabelecimento Estabelecimento
   :compra/categoria       Categoria
   :compra/cartao          model.cartao/CartaoComComponents})
