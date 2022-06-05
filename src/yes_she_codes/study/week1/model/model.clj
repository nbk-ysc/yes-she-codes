(ns yes-she-codes.study.week1.model.model)


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Dados de um cliente:
; Nome (String)
; CPF (String)
; Email (String)

(defn novo-cliente
  [nome cpf email]
  {:nome nome
   :cpf cpf
   :email email})


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Dados de um cartão de crédito:
; Número (Long)
; CVV (Long)
; Validade (String: yyyy-mm)  => (Obj: java.time.YearMonth)
; Limite (BigDecimal)
; Cliente (String) = cpf

(defn novo-cartao
  [numero cvv validade limite cliente]
  {:numero numero
   :cvv cvv
   :validade validade
   :limite limite
   :cliente cliente})


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Dados de uma compra:
; Data (String: yyyy-mm-dd)  =>  (Obj: java.time.LocalDate)
; Valor (BigDecimal)
; Estabelecimento (String)
; Categoria (String)
; Cartão (Long)

(defn nova-compra
  [data valor estabelecimento categoria cartao]
  {:data data
   :valor valor
   :estabelecimento estabelecimento
   :categoria categoria
   :cartao cartao})
