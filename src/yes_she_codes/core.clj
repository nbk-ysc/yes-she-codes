(ns yes-she-codes.core
  (:import (java.awt.image ConvolveOp)))

(defn novo-cliente
  [nome cpf email]
  {"nome" nome
   "CPF" cpf
   "email" email})

(println (novo-cliente "daiane" "44219671814" "daiane@gmail.com"))


(defn novo-cartao
  [numero CVV  validade limite cliente]
  {"numero" numero
   "CVV" CVV
   "validade" validade
   "limite" limite
   "cliente" cliente})

(def cliente1 (novo-cliente "daiane" "44219671814" "daiane@gmail.com"))
(def cartao1 (novo-cartao 1234568521236 442 "2030-07" 1500.0M cliente1 ))

(println cartao1)

(defn nova-compra
  [data valor estabelecimento categoria cartao]
  {"data" data
   "valor" valor
   "estabelecimento" estabelecimento
   "categoria" categoria
   "cartao" cartao})

(def compra1 (nova-compra "2022-05-12" 150.0M "palace" "beleza" cartao1))
(println compra1)




