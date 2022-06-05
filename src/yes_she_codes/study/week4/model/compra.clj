(ns yes-she-codes.study.week4.model.compra
  (:require [schema.core :as s])
  (:import (java.time LocalDate)))


(s/def Compra
  {:compra/id              s/Uuid
   :compra/data            LocalDate
   :compra/valor           BigDecimal
   :compra/estabelecimento s/Str
   :compra/categoria       s/Str
   :compra/cartao          Long})


