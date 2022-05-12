(ns yes-she-codes.cartao)

(def cartoes [])

(defn novo-cartao
  "retorna um cartão de crédito"
  [numero cvv validade limite cpf-cliente]
  (let [novo-cartao {:numero numero
                     :cvv cvv
                     :validade validade
                     :limite limite
                     :cliente cpf-cliente}]
    (def cartoes (conj cartoes novo-cartao))
    novo-cartao))

(defn lista-cartoes
  ([arg1 cartoes])
  ([] cartoes))