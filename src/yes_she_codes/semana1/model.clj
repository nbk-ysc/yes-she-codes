(ns yes-she-codes.semana1.model)

(defn novo-cliente
  "Retorna um mapa que representa os dados de um cliente."
  [nome, cpf, email]
  {:nome  nome
   :cpf   cpf
   :email email})

(defn novo-cartao
  "Retorna um mapa que representa os dados de um cartão de crédito, que deve estar relacionado a um cliente."
  [numero, cvv, validade, limite, cliente]
  {:numero   numero
   :cvv      cvv
   :validade validade
   :limite   limite
   :cliente  cliente})

(defn nova-compra
  "Retorna um mapa que representa uma compra realizada em um determinao cartão de crédito."
  [data, valor, estabelecimento, categoria, cartao]
  {:data            data
   :valor           valor
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          cartao})