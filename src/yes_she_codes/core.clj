(ns yes-she-codes.core)

(defn novo-cliente
  [nome cpf email]
  {"Nome" nome
   "CPF" cpf
   "Email" email})

(println (novo-cliente "daiane" "44219671814" "daiane@gmail.com"))

