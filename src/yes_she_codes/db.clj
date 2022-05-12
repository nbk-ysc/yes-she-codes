(ns yes-she-codes.db
  (:require [java-time :as t]
            [clojure.data.csv :as csv]
            [clojure.java.io :as io]))


(defn ler-csv [from]
  (with-open [reader (io/reader from)]
    (doall
      (csv/read-csv reader))))



(defn clientes []
  (ler-csv "csv/clientes.csv"))

(defn novo-cliente [nome, cpf, email]
  {:nome  nome
   :cpf   cpf
   :email email})

(defn dados-clientes
  [[nome cpf email]]
  (novo-cliente nome cpf email))

(defn lista-clientes []
  (map dados-clientes (clientes)))



(defn cartoes []
  (ler-csv "csv/cartoes.csv"))

(defn novo-cartao [numero, cvv, validade, limite, cliente]
  {:numero   numero
   :cvv      cvv
   :validade (t/year-month validade)
   :limite   (new BigDecimal limite)
   :cliente  cliente})

(defn dados-cartoes
  [[numero cvv validade limite cliente]]
  (novo-cartao numero cvv validade limite cliente))

(defn lista-cartoes []
  (map dados-cartoes (cartoes)))



(defn compras []
  (ler-csv "csv/compras.csv"))

(defn nova-compra [data, valor, estabelecimento, categoria, cartao]
  {:data            (t/local-date data)
   :valor           (new BigDecimal valor)
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          cartao})

(defn dados-compras
  [[data valor estabelecimento categoria cartao]]
  (nova-compra data valor estabelecimento categoria cartao))

(defn lista-compras []
  (map dados-compras (compras)))










