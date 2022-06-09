(ns yes-she-codes.logic.cartao
  (:require
    [yes-she-codes.csv.dados_csv :as y.dados]))

(defn novo-cartao
  [numero cvv validade limite cliente]
  {:numero numero :cvv cvv :validade validade :limite limite :cliente cliente})

(defn transforma-cartoes [registros]
  (map (fn [[numero cvv validade limite cliente]]
         (novo-cartao numero, cvv, validade, limite, cliente))
       registros))

(def cartoes (transforma-cartoes (y.dados/lista-cartoes)))

(defn compras-de-um-cartao
  [compras cartao]
  (filter #(= (:cartao %) cartao) compras))

