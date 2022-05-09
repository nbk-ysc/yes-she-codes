(ns yes-she-codes.cartao)

(defn novo-cartao [numero cvv validade limite cliente]
  (if-let [cpf (:cpf cliente)]
    {:numero numero
     :cvv cvv
     :validade validade
     :limite limite
     :cliente cpf}
    (throw (ex-info "Cliente invalido: cliente não possui cpf" {:cliente cliente}))))