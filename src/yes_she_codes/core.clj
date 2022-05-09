(ns yes-she-codes.core)

(defn novo-cliente
  "retorna um novo cliente com os dados informados"
  [nome cpf email]
  (let [cliente {:nome nome
                 :cpf cpf
                 :email email}]
    cliente))

(defn novo-cartao
  "retorna um cartão de crédito"
  [numero cvv validade limite cpf-cliente]
  (let [cartao {:numero numero
                :cvv cvv
                :validade validade
                :limite limite
                :cliente cpf-cliente}]
    cartao))