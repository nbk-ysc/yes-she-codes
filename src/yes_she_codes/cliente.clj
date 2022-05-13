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
      {:nome nome
       :cpf cpf
       :email email
       })
    (throw (ex-info "Cliente invalido" {:cliente parametro-cliente}))))

(defn lista-clientes [parametro-cliente]
  (map novo-cliente parametro-cliente))

(defn csv-data->novo-cliente [data]
  (if-let [[nome cpf email] data]
    (novo-cliente [(str/lower-case nome) cpf (str/lower-case email)])))

(defn csv->lista-clientes [caminho-arquivo]
  (map csv-data->novo-cliente (csv-data caminho-arquivo)))

(defn cliente-lista? [cpf]
  (filter #(= (:cpf %) cpf) (csv->lista-clientes "arquivos/clientes.csv")))
