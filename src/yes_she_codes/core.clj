(ns yes-she-codes.core
  (:require [yes_she_codes.db :as y.db])
  (:require [yes-she-codes.cliente :as y.cliente])
  (:require [yes_she_codes.cartao :as y.cartao])
  (:require [yes_she_codes.compras :as y.compras])
  (:require [clojure.string :as str]))


(println "Todos os Clientes:\n"
         (y.cliente/lista-clientes (y.db/lista-dados-clientes)))

(println "\nTodos os Cartoes:\n" (y.cartao/lista-cartao (y.db/lista-dados-cartao)))

(println "\nCompras Gerais:\n" (y.compras/lista-compra (y.db/lista-dados-compra)))

; Soma valores recebidos dos cartoes
(defn total-gasto [lista-compras]
  (->> lista-compras
       (map :valor)
       (apply +)))

; Mapeia cartao e seus gastos respectivos
(defn lista-gastos [[cartao compras]]
  {:cartao cartao
   :gasto-total (total-gasto compras)})

; Separa as informaçoes necessarias para a soma dos gastos por cartao
(defn soma-compras-cartao [cartao lista-compras]
  (println "\nTotal de gasto do cartao: " cartao)
  (->> lista-compras
       (group-by :cartao)
       (map lista-gastos)
       (filter #(= (:cartao %) (str cartao)))
       println))

(println "\nTotal geral de compras (todos os cartoes):"
         (total-gasto (y.compras/lista-compra (y.db/lista-dados-compra))))

(soma-compras-cartao 1234123412341234 (y.compras/lista-compra (y.db/lista-dados-compra)))

;Separa as compras por mes de um cartao e soma
(defn soma-compras-mes [mes cartao lista-compras]
  (println "\nGastos do cartao" cartao "No mes" mes)
  (->> lista-compras
       (filter (and #(str/includes? (:data %) (str mes))
                    #(= (:cartao %) (str cartao))))
       (total-gasto)
       println))

(soma-compras-mes 1 1234123412341234 (y.compras/lista-compra (y.db/lista-dados-compra)))


;todo Melhorar as saidas das compras por mes para ficar no msm formato que o compra por cartao
;todo montar o filtro de compras por estabelecimentos


;Exemplo Filtro de compra por estabelecimento
  #_(defn compras-estabelecimento [estabelecimento lista-compras]
    (filter #(= (str/lower-case (:estabelecimento %)) (str/lower-case estabelecimento)) lista-compras))

  ;(println (compras-estabelecimento estabelecimento (y.compras/lista-compra (y.db/lista-dados-compra))))