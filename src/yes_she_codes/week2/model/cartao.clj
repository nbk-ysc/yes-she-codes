(ns yes-she-codes.week2.model.cartao)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Dados de um record de cartão de crédito:
; Id (Long ou nil)
; Número (Long)
; CVV (Long)
; Validade (String: yyyy-mm)  => (Obj: java.time.YearMonth)
; Limite (BigDecimal)
; Cliente (String) = cpf

(defn novo-record-cartao
  ([numero cvv validade limite cliente]
   (novo-record-cartao nil numero cvv validade limite cliente))
  ([id numero cvv validade limite cliente]
   {:id       id
    :numero   numero
    :cvv      cvv
    :validade validade
    :limite   limite
    :cliente  cliente}))
