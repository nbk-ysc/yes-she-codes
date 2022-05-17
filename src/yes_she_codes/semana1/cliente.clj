(ns yes-she-codes.semana1.cliente
  (:require [yes-she-codes.semana1.db :as y.db]))

(defn novo-cliente
  "Cria um cadastro com o cliente novo"
  [dados-cliente]
  (if-let [[nome cpf email] dados-cliente]
    {:nome  nome
     :cpf   cpf
     :email email}
    (println "Cliente invalido" {:cliente dados-cliente})))


(defn lista-clientes [lista-dados-clientes]
  (mapv novo-cliente lista-dados-clientes))





