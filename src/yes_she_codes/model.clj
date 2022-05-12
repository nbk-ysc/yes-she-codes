(ns yes_she_codes.model)

(defn novo-cliente
  [[nome cpf email]]
  {:nome  nome
   :cpf   cpf
   :email email})

(defn novo-cartao
  [[numero cvv validade limite cliente]]
  {:numero   numero
   :cvv      cvv
   :valdiade validade
   :limite   limite
   :cliente  cliente})

(defn nova-compra
  [[data valor estabelecimento categoria cartao]]
  {:data            data
   :valor           valor
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          cartao})