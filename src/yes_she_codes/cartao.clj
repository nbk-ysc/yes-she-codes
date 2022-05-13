(ns yes-she-codes.cartao
  (:require [java-time :as t]
            [yes-she-codes.cliente :refer :all]
            [yes-she-codes.simulador :refer :all]
            [clojure.string :as str]))

(defn cartao? [parametro-cartao]
  (if-let [[numero cvv validade limite cpf] parametro-cartao]
    (let [cliente (first (cliente-lista? cpf))]
      (and
        (not (nil? numero))
        (not (nil? cvv))
        (not (nil? validade))
        (not (nil? limite))
        (cliente? [(:nome cliente) (:cpf cliente) (:email cliente)])
      ))))

(defn novo-cartao [parametro-cartao]
  (if (cartao? parametro-cartao)
    (if-let [[numero cvv validade limite cpf] parametro-cartao]
      {:numero   numero
       :cvv      cvv
       :validade validade
       :limite   limite
       :cliente  cpf})
    (throw (ex-info "Cartao invalido" {:cartao parametro-cartao}))))

(defn lista-cartoes [parametro-cartao]
  (map novo-cartao parametro-cartao))

(defn csv-data->novo-cartao [data]
  (if-let [[numero cvv validade limite cpf] data]
    (novo-cartao [(Long/parseLong (str/replace numero " " "")) (Integer/parseInt cvv) (t/year-month validade)(Double/parseDouble (str/replace limite "." "")) cpf])
    ))

(defn csv->lista-cartoes [caminho-arquivo]
  (map csv-data->novo-cartao (csv-data caminho-arquivo)))

(defn cartao-lista? [numero]
  (filter #(= (:numero %) numero) (csv->lista-cartoes "arquivos/cartoes.csv")))
