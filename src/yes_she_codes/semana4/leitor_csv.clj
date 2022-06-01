(ns yes-she-codes.semana4.leitor_csv
  (:require [yes-she-codes.semana4.model :as model]
            [yes-she-codes.util :as util]
            [clojure.data.csv :as csv]
            [clojure.java.io :as io]))

(defn abre-arquivo-csv [caminho]
  (with-open [reader (io/reader caminho)]
    (doall (csv/read-csv reader))))

(defn transforma-tipos-dos-valores-do-cartao [[numero cvv validade limite cliente]]
  (let [numero-em-long (util/transforma-de-string-para-long numero)
        cvv-em-long (util/transforma-de-string-para-long cvv)
        limite-em-bigdecimal (util/transforma-de-string-para-bigdecimal limite)]
    [numero-em-long cvv-em-long validade limite-em-bigdecimal cliente]))

(defn constroi-cartao [[numero cvv validade limite cliente]]
  (model/novo-cartao numero cvv validade limite cliente))

(defn carrega-cartoes [caminho]
  (let [cartoes-csv (rest (abre-arquivo-csv caminho))
        cartoes-tipos-atualizados (map transforma-tipos-dos-valores-do-cartao cartoes-csv)]
    (map constroi-cartao cartoes-tipos-atualizados)))

(defn transforma-tipos-dos-valores-da-compra [[data valor estabelecimento categoria cartao]]
  (let [cartao-em-long (util/transforma-de-string-para-long cartao)
        valor-em-bigdecimal (util/transforma-de-string-para-bigdecimal valor)]
    [data valor-em-bigdecimal estabelecimento categoria cartao-em-long]))

(defn constroi-compra [[data valor estabelecimento categoria cartao]]
  (model/nova-compra data valor estabelecimento categoria cartao))

(defn carrega-compras [caminho]
  (let [compras-csv (rest (abre-arquivo-csv caminho))
        compras-tipos-atualizados (map transforma-tipos-dos-valores-da-compra compras-csv)]
    (map constroi-compra compras-tipos-atualizados)))