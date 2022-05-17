(ns yes-she-codes.week2.model.model)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Dados de um record de cliente:
; Id (Long ou nil)
; Nome (String)
; CPF (String)
; Email (String)

(defn novo-record-cliente
  ([nome cpf email]
   (novo-record-cliente nil nome cpf email))
  ([id nome cpf email]
   {:id    id
    :nome  nome
    :cpf   cpf
    :email email}))


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


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Dados de um record de compra:
; Id (Long ou nil)
; Data (String: yyyy-mm-dd)  =>  (Obj: java.time.LocalDate)
; Valor (BigDecimal)
; Estabelecimento (String)
; Categoria (String)
; Cartão (Long)

(defn novo-record-compra
  ([data valor estabelecimento categoria cartao]
   (novo-record-compra nil data valor estabelecimento categoria cartao))
  ([id data valor estabelecimento categoria cartao]
   {:id              id
    :data            data
    :valor           valor
    :estabelecimento estabelecimento
    :categoria       categoria
    :cartao          cartao}))

