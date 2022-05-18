(ns yes-she-codes.week2.model.compra)

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
