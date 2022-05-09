(ns yes_she_codes.cartao)

(defn novo-cartao
  [num_cartao cvv valid limit client]
  (if-let [cpf (:cpf client)]
    {:cartao num_cartao
     :cvv    cvv
     :valid  valid
     :limit  limit
     :client cpf})
  (println "CPF do cliente nao cadastrado"))