(ns yes-she-codes.semana1.teste
  (:require [yes-she-codes.semana1.db :as y.db])
  (:require [yes-she-codes.semana1.cliente :as y.cliente])
  (:require [yes-she-codes.semana1.cartao :as y.cartao])
  (:require [yes-she-codes.semana1.compras :as y.compras])
  (:require [clojure.string :as str]))

; Modifica o formato da data para o filtro de compras por mes
(defn mes-da-data [data]
  (Long/parseLong (second (re-matches #"\d{4}-(\d{2})-\d{2}" data))))

;Separa as compras por mes de um cartao e soma
(defn lista-compras-mes [mes cartao lista-compras]
  (println "\nGastos do da fatura do cartao" cartao "No mes" mes)
  (->> lista-compras
       (filter #(and (= mes (mes-da-data (:data %)))
                     (= (str cartao) (:cartao %))))
       println))

(lista-compras-mes 1 1234123412341234 (y.compras/lista-compra (y.db/lista-dados-compra)))