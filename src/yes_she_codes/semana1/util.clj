(ns yes-she-codes.semana1.util
  (:require [clojure.string :as str]
            [java-time :as jtime]))

(defn transforma-de-string-para-long [valor]
  (Long/valueOf (str/replace valor #" " "")))

(defn transforma-de-string-para-bigdecimal [valor]
  (bigdec (str/replace valor #" " "")))

(defn altera-tipo-da-validade-do-cartao [data] ; yyyy-mm
  (let [data-separada (str/split data #"-")
        ano (transforma-de-string-para-long (get data-separada 0))
        mes (transforma-de-string-para-long (get data-separada 1))]
    (jtime/local-date ano mes)))

(defn altera-tipo-da-data-da-compra [data] ; yyyy-mm-dd
  (let [data-separada (str/split data #"-")
        ano (transforma-de-string-para-long (get data-separada 0))
        mes (transforma-de-string-para-long (get data-separada 1))
        dia (transforma-de-string-para-long (get data-separada 2))]
    (jtime/local-date ano mes dia)))

(defn transforma-tipos-dos-valores-do-cartao [[numero cvv validade limite cliente]]
  (let [numero-em-long (transforma-de-string-para-long numero)
        cvv-em-long (transforma-de-string-para-long cvv)
        validade-em-localdate (altera-tipo-da-validade-do-cartao validade)
        limite-em-bigdecimal (transforma-de-string-para-bigdecimal limite)]
    [numero-em-long cvv-em-long validade-em-localdate limite-em-bigdecimal cliente]))

(defn transforma-tipos-dos-valores-da-compra [[data valor estabelecimento categoria cartao]]
  (let [data-em-localdate (altera-tipo-da-data-da-compra data)
        cartao-em-long (transforma-de-string-para-long cartao)
        valor-em-bigdecimal (transforma-de-string-para-bigdecimal valor)]
    [data-em-localdate valor-em-bigdecimal estabelecimento categoria cartao-em-long]))