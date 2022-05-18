(ns yes_she_codes.semana2.model)

(defrecord Compra [id data valor estabelecimento categoria cartao])
(defrecord Cliente [id nome cpf email])
(defrecord Cartao [id numero cvv validade limite cliente])

(defn nova-compra
  [[data valor estabelecimento categoria cartao]]
  (Compra. nil data valor estabelecimento categoria cartao))

(defn novo-cliente
  [[nome cpf email]]
  (Cliente. nil nome cpf email))

(defn novo-cartao
  [[numero cvv validade limite cliente]]
  (Cartao. nil numero cvv validade limite cliente))
