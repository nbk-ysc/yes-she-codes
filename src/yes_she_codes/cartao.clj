(ns yes-she-codes.cartao
  (:require [java-time :as t]
            [yes-she-codes.cliente :refer :all]
            [yes-she-codes.simulador :refer :all]
            [clojure.string :as str]))

(defn cartao? [parametro-cartao]
  (if-let [[numero cvv validade limite cpf] parametro-cartao]
    (let [cliente  (cliente-lista? cpf)]
      (and
        (not (nil? numero))
        (not (nil? cvv))
        (not (nil? validade))
        (not (nil? limite))
        (cliente? [:nome cliente :cpf cliente :email cliente])
      ))))

(defn novo-cartao [parametro-cartao]
  (if (cartao? parametro-cartao)
    (if-let [[numero cvv validade limite cpf] parametro-cartao]
      {:numero   (Long/parseLong (str/replace numero " " ""))
       :cvv      (Integer/parseInt cvv)
       :validade (t/year-month validade)
       :limite   (Double/parseDouble (str/replace limite "." ""))
       :cliente  cpf})
    (throw (ex-info "Cartao invalido" {:cartao parametro-cartao}))))

(defn lista-cartoes [caminho-arquivo]
  (map novo-cartao (csv-data caminho-arquivo)))

(defn cartao-lista? [numero]
  (filter #(= (:numero %) numero) (lista-cartoes "arquivos/cartoes.csv")))
