(ns yes-she-codes.core)

(defn novo-cliente
  "retorna um novo cliente com os dados informados"
  [nome cpf email]
  (let [novo-cliente {:nome nome
                 :cpf cpf
                 :email email}]
    novo-cliente))

(defn novo-cartao
  "retorna um cartão de crédito"
  [numero cvv validade limite cpf-cliente]
  (let [novo-cartao {:numero numero
                :cvv cvv
                :validade validade
                :limite limite
                :cliente cpf-cliente}]
    novo-cartao))

(defn nova-compra
  "retorna uma nova compra"
  [data valor estabelecimento categoria numero-cartao]
  (let [nova-compra {:data data
                :valor valor
                :estabelecimento estabelecimento
                :categoria categoria
                :cartao numero-cartao}]
    nova-compra))