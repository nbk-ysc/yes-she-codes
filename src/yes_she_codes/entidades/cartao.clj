(ns yes-she-codes.entidades.cartao)

(defrecord Cartao [id numero cvv validade limite cliente])

; fazer uma filtro de cartão para chamar na validação da compra
; caso o cartão n exista, entra no except de compra inválida