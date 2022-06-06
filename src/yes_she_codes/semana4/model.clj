(ns yes-she-codes.semana4.model
  (:use [clojure pprint]))

(defn nova-compra
  [[data valor estabelecimento categoria cartao]]
  {:compra/data             data
   :compra/valor            valor
   :compra/estabelecimento  estabelecimento
   :compra/categoria        categoria
   :compra/cartao           cartao})

(defn novo-cartao
  [[numero cvv validade limite cliente]]
  {:cartao/numero    numero
   :cartao/cvv       cvv
   :cartao/validade  validade
   :cartao/limite    limite
   :cartao/cliente   cliente})

