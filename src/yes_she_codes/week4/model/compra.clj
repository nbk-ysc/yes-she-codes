(ns yes-she-codes.week4.model.compra
  (:require [schema.core :as s])
  (:import (java.time LocalDate)))



(s/def Compra
  {:compra/id              s/Uuid
   :compra/data            LocalDate
   :compra/valor           BigDecimal
   :compra/estabelecimento s/Str
   :compra/categoria       s/Str
   :compra/cartao          Long})


;(s/defn nova-compra
;  [#:compra{:keys [data valor estabelecimento categoria cartao]}] :- [Compra]
;  #:compra{:id              (uuid)
;           :data            data
;           :valor           valor
;           :estabelecimento estabelecimento
;           :categoria       categoria
;           :cartao          cartao}
;
;  )

