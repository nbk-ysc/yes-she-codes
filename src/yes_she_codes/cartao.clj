(ns yes-she-codes.cartao)

(defn novo-cartao [parametro-cartao]
  (if-let [[numero cvv validade limite cliente] parametro-cartao]
    (if-let [cpf (:cpf cliente)]
      {:numero numero
       :cvv cvv
       :validade validade
       :limite limite
       :cliente  cpf}
      (throw (ex-info "Cliente invalido" {:cliente cliente} )))
    (throw (ex-info "Cartao invalido" {:cartao parametro-cartao} ))))

(defn lista-cartoes [parametros-cartoes]
  (map novo-cartao parametros-cartoes))
