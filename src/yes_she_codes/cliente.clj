(ns yes-she-codes.cliente)

(defn novo-cliente
  "Cria um cadastro com o cliente novo"
  [nome cpf email]
  { :nome nome
    :cpf cpf
    :email email})