(ns yes-she-codes.semana4.model
  (:require [schema.core :as s]))

(defn uuid [] (java.util.UUID/randomUUID))

(def Cartao
  {:cartao/id                        java.util.UUID
   (s/optional-key :cartao/numero)   Long
   (s/optional-key :cartao/cvv)      Long
   (s/optional-key :cartao/validade) s/Str
   (s/optional-key :cartao/limite)   BigDecimal
   (s/optional-key :cartao/cliente)  s/Str})

(def Compra
  {:compra/id                               java.util.UUID
   (s/optional-key :compra/data)            s/Str
   (s/optional-key :compra/valor)           BigDecimal
   (s/optional-key :compra/estabelecimento) s/Str
   (s/optional-key :compra/categoria)       s/Str
   (s/optional-key :compra/cartao)          Cartao})

(defn novo-cartao
  ([numero cvv validade limite cliente]
   (novo-cartao (uuid) numero cvv validade limite cliente))
  ([uuid numero cvv validade limite cliente]
   {:cartao/id       uuid
    :cartao/numero   numero
    :cartao/cvv      cvv
    :cartao/validade validade
    :cartao/limite   limite
    :cartao/cliente  cliente}))

(defn nova-compra
  ([data valor estabelecimento categoria cartao]
   (nova-compra (uuid) data valor estabelecimento categoria cartao))
  ([uuid data valor estabelecimento categoria cartao]
   {:compra/id              uuid
    :compra/data            data
    :compra/valor           valor
    :compra/estabelecimento estabelecimento
    :compra/categoria       categoria
    :compra/cartao          cartao}))