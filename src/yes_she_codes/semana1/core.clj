(ns yes-she-codes.semana1.core
  (:require [yes-she-codes.semana1.db :as y.db])
  (:require [yes-she-codes.semana1.cliente :as y.cliente])
  (:require [yes-she-codes.semana1.cartao :as y.cartao])
  (:require [yes-she-codes.semana1.compras :as y.compras])
  (:require [clojure.string :as str]))


; Lista tdos os clientes
(println "\nLista dos clientes:\n"(y.cliente/lista-clientes (y.db/lista-dados-clientes)))

; Lista tdos os cartões cadastrados
(println "\nCartoes cadastrados:\n"(y.cartao/lista-cartao (y.db/lista-dados-cartao)))

; Lista compras dos cartoes cadastrados
(println "\nCompras realizadas até o momento:\n"(y.compras/lista-compra (y.db/lista-dados-compra)))

; Soma valores recebidos
(defn total-gasto [lista-compras]
  (->> lista-compras
       (mapv :valor)
       (apply +)))

(println "\nTotal geral de compras (todos os cartoes):"
         (total-gasto (y.compras/lista-compra (y.db/lista-dados-compra))))

; Mapeia cartao e seus gastos respectivos
(defn lista-gastos [[cartao compras]]
  {:cartao cartao
   :gasto-total (total-gasto compras)})

; Modifica o formato da data para o filtro de compras por mes
(defn mes-da-data [data]
  (Long/parseLong (second (re-matches #"\d{4}-(\d{2})-\d{2}" data))))

; Mapeia os estabelecimentos e seus valores
(defn lista-gastos-estabelecimento [[estabelecimento compras]]
  {:estabelecimento      estabelecimento
   :gasto-total (total-gasto compras)})

; Calcula os gastos por cartao
(defn soma-compras-cartao [cartao lista-compras]
  (println "\nTotal de gasto do cartao: " cartao)
  ;(println lista-compras)
   (->> lista-compras
                     (group-by :cartao)
                     (mapv lista-gastos)
                     (filter #(= (:cartao %) (str cartao)))
                           first
                           :gasto-total)
  )

(soma-compras-cartao 1234123412341234 (y.compras/lista-compra (y.db/lista-dados-compra)))

; Calcula o total da fatura de um cartao no mes
(defn cacula-fatura-mes [mes cartao lista-compras]
  (println "\nGastos do da fatura do cartao" cartao "No mes" mes)
  (->> lista-compras
       (filter #(and (= mes (mes-da-data (:data %)))
                     (= (str cartao) (:cartao %))))
       (total-gasto)))

;(cacula-fatura-mes 1 1234123412341234 (y.compras/lista-compra (y.db/lista-dados-compra)))

; Lista as compras do mes
(defn lista-compras-mes [mes cartao lista-compras]
  (println "\nLista gastos gerais no mes: " mes)
  (->> lista-compras
       (filter #(and (= mes (mes-da-data (:data %)))
                     (= (str cartao) (:cartao %))))
       println))

;(lista-compras-mes mes cartao (y.compras/lista-compra (y.db/lista-dados-compra)))

;Filtro de compra por estabelecimento
(defn soma-compras-estabelecimento
  [estabelecimento
   lista-compras]

(println "\nGastos totais por estabelecimento")
  (->> lista-compras
       (group-by :estabelecimento)
       (map lista-gastos-estabelecimento)
       (filter #(= (str/lower-case (:estabelecimento %)) (str/lower-case estabelecimento)))
       first))

(soma-compras-estabelecimento "Outback" (y.compras/lista-compra (y.db/lista-dados-compra)))