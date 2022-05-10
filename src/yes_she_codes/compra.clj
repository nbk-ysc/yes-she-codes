(ns yes-she-codes.compra)

(defn nova-compra [data	valor	estabelecimento	categoria	cartao]
  (if-let [numero (:numero cartao)]
    {:data data
     :valor valor
     :estabelecimento estabelecimento
     :categoria categoria
     :cartao numero
     }
    (throw (ex-info "Cartao invalido: cartao não possui numero" {:cartao cartao})))
  )