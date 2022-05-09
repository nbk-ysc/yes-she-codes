(ns yes_she_codes.compras)

(defn nova-compra
  [data valor estabelecimento categ cartao]
  {:data data
   :valor valor
   :estab estabelecimento
   :categ categ
   :cartao cartao})