(ns yes-she-codes.cliente
  (:require  [yes-she-codes.simulador :refer :all]
             [clojure.string :as str]))

(defn cliente? [parametro-cliente]
  (if-let [[nome cpf email] parametro-cliente]
    (and (not (nil? nome))
         (not (nil? cpf))
         (not (nil? email)))))

(defn novo-cliente [parametro-cliente]
  (if (cliente? parametro-cliente)
    (let [[nome cpf email] parametro-cliente]
      {:nome (str/lower-case nome)
       :cpf cpf
       :email (str/lower-case email)
       })
    (throw (ex-info "Cliente invalido" {:cliente parametro-cliente}))))

(defn lista-clientes [caminho-arquivo]
  (map novo-cliente (csv-data caminho-arquivo)))

(defn cliente-lista? [cpf]
  (filter #(= (:cpf %) cpf) (lista-clientes "arquivos/clientes.csv")))