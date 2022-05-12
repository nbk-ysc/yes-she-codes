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
   "cliente" cliente
   })

(def cliente1 (novo-cliente "daiane" "44219671814" "daiane@gmail.com"))
(println (novo-cartao 1234568521236 442 "2030-07" 1500.0M cliente1))
