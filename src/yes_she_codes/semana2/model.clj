(ns yes-she-codes.semana2.model)

(defrecord Cliente [id nome cpf email])

(defrecord Cartao [id numero cvv validade limite cliente])

(defrecord Compra [id data valor estabelecimento categoria cartao])