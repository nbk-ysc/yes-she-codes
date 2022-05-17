(ns yes-she-codes.semana1.db
  (:require [yes-she-codes.semana1.model :as ysc.model]
            [yes-she-codes.semana1.util :refer :all]
            [clojure.data.csv :as csv]
            [clojure.java.io :as io]))

(defn abre-arquivo-csv [caminho]
  (with-open [reader (io/reader caminho)]
    (doall (csv/read-csv reader))))

(defn constroi-cliente [[nome cpf email]]
  (ysc.model/novo-cliente nome cpf email))

(defn carrega-clientes [caminho-para-arquivo-clientes]
  (let [clientes-csv (rest (abre-arquivo-csv caminho-para-arquivo-clientes))]
    (map constroi-cliente clientes-csv)))

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