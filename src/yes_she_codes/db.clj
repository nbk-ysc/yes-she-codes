(ns yes-she-codes.db
  (:require [yes-she-codes.model :as ysc.model]
            [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [clojure.string :as str]))

(defn abre-arquivo-csv [caminho]
  (with-open [reader (io/reader caminho)]
    (doall (csv/read-csv reader))))

(defn transforma-de-string-para-long [valor]
  (Long/valueOf (str/replace valor #" " "")))

(defn transforma-de-string-para-bigdecimal [valor]
  (bigdec (str/replace valor #" " "")))

(defn constroi-cliente [[nome cpf email]]
  (ysc.model/novo-cliente nome cpf email))

(defn carrega-clientes [caminho-para-arquivo-clientes]
  (let [clientes-csv (rest (abre-arquivo-csv caminho-para-arquivo-clientes))]
    (map constroi-cliente clientes-csv)))

(defn transforma-tipos-dos-valores-da-compra [[data valor estabelecimento categoria cartao]]
  (let [cartao-em-long (transforma-de-string-para-long cartao)
        valor-em-bigdecimal (transforma-de-string-para-bigdecimal valor)]
    [data valor-em-bigdecimal estabelecimento categoria cartao-em-long]))

(defn transforma-tipos-dos-valores-do-cartao [[numero cvv validade limite cliente]]
  (let [numero-em-long (transforma-de-string-para-long numero)
        cvv-em-long (transforma-de-string-para-long cvv)
        limite-em-bigdecimal (transforma-de-string-para-bigdecimal limite)]
    [numero-em-long cvv-em-long validade limite-em-bigdecimal cliente]))

(defn constroi-cartao [[numero cvv validade limite cliente]]
  (ysc.model/novo-cartao numero cvv validade limite cliente))

(defn carrega-cartoes [caminho-para-arquivo-cartoes]
  (let [cartoes-csv (rest (abre-arquivo-csv caminho-para-arquivo-cartoes))
        cartoes-tipos-atualizados (map transforma-tipos-dos-valores-do-cartao cartoes-csv)]
    (map constroi-cartao cartoes-tipos-atualizados)))

(defn constroi-compra [[data valor estabelecimento categoria cartao]]
  (ysc.model/nova-compra data valor estabelecimento categoria cartao))

(defn carrega-compras [caminho-para-arquivo-compras]
  (let [compras-csv (rest (abre-arquivo-csv caminho-para-arquivo-compras))
        compras-tipos-atualizados (map transforma-tipos-dos-valores-da-compra compras-csv)]
    (map constroi-compra compras-tipos-atualizados)))