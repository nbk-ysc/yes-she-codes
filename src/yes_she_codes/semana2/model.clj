(ns yes_she_codes.semana2.model)

(defrecord Compra [id data valor estabelecimento categoria cartao])

(defn nova-compra
  [[data valor estabelecimento categoria cartao]]
  (Compra. nil data valor estabelecimento categoria cartao))
