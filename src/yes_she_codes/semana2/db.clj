(ns yes-she-codes.semana2.db
  (:require [yes-she-codes.semana2.model :as ysc.model]
            [yes-she-codes.semana2.logic :as ysc.logic]
            [yes-she-codes.util :refer :all]
            [clojure.data.csv :as csv]
            [clojure.java.io :as io]))

(defn abre-arquivo-csv [caminho]
  (with-open [reader (io/reader caminho)]
    (doall (csv/read-csv reader))))

(defn constroi-cliente [[nome cpf email]]
  (ysc.model/->Cliente nil nome cpf email))

(defn constroi-cartao [[numero cvv validade limite cliente]]
  (ysc.model/->Cartao nil numero cvv validade limite cliente))

(defn constroi-compra [[data valor estabelecimento categoria cartao]]
  (ysc.model/->Compra nil data valor estabelecimento categoria cartao))

(defmulti carrega-dados (fn [informacoes] (get informacoes :tipo-da-entidade)))

(defmethod carrega-dados :clientes [informacoes]
  (let [caminho (get informacoes :caminho)
        clientes-csv (rest (abre-arquivo-csv caminho))
        clientes (map constroi-cliente clientes-csv)
        atomo (get informacoes :atomo)]
    (doall (map #(ysc.logic/insere-item! atomo %) clientes))))

(defmethod carrega-dados :cartoes [informacoes]
  (let [caminho (get informacoes :caminho)
        cartoes-csv (rest (abre-arquivo-csv caminho))
        cartoes-tipos-atualizados (map transforma-tipos-dos-valores-do-cartao cartoes-csv)
        cartoes (map constroi-cartao cartoes-tipos-atualizados)
        atomo (get informacoes :atomo)]
    (doall (map #(ysc.logic/insere-item! atomo %) cartoes))))

(defmethod carrega-dados :compras [informacoes]
  (let [caminho (get informacoes :caminho)
        compras-csv (rest (abre-arquivo-csv caminho))
        compras-tipos-atualizados (map transforma-tipos-dos-valores-da-compra compras-csv)
        compras (map constroi-compra compras-tipos-atualizados)
        atomo (get informacoes :atomo)]
    (doall (map #(ysc.logic/insere-item! atomo %) compras))))