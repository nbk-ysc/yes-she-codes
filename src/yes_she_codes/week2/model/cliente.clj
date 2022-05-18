(ns yes-she-codes.week2.model.cliente)

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