(ns yes-she-codes.model.model)

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Criar a função novo-cliente, que devolve uma estrutura de dados que represente os dados de um cliente.
;
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
; Criar a função novo-cartao, que retorne uma estrutura de dados que represente um cartão de crédito.
; O cartão deve estar relacionado a um cliente.
;
; Dados de um cartão de crédito:
; Número (Long)
; CVV (Long)
; Validade (String: yyyy-mm)
; Limite (BigDecimal)
; Cliente (String) = cpf

(defn novo-cartao
  [numero cvv validade limite cliente]
  {:numero numero
   :cvv cvv
   :validade validade
   :limite limite
   :cliente cliente}
  )


;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
; Criar a função nova-compra, que retorne uma estrutura de dados que represente uma compra realizada em um determinado cartão.
;
; Dados de uma compra:
; Data (String: yyyy-mm-dd)
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
   :cartão cartao}
  )
