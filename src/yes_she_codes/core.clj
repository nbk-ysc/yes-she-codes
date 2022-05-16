(ns yes-she-codes.core
  (:use clojure.pprint)
  (:require [yes-she-codes.db :as y.db]
            [yes-she-codes.cliente :as y.cliente]
            [yes-she-codes.cartao :as y.cartao]
            [yes-she-codes.compra :as y.compra]
            [clojure.string :as str]))

(def lista-compras y.db/compras)

;CALCULAR O TOTAL GASTO EM COMPRAS DE UMA LISTA DE COMPRAS
(defn total-gasto [lista-compras]
  (->> lista-compras
       (map :valor)
       (reduce +)))

;(println "Total Gasto R$" (total-gasto lista-compras))

;BUSCAR COMPRAS POR MES
(defn get-moth [mes]
  (get (str/split mes #"-") 1))

(defn lista-compras-por-mes [mes lista-compras]
  ;(println "Todas as compras no mês" mes)
  (filter #(= (get-moth (:data %)) mes) lista-compras))

;(pprint (lista-compras-por-mes "02" lista-compras))


;BUSCAR COMPRAS POR ESTABELECIMENTO
(defn lista-compras-por-estabelecimento [estabelecimento lista-compras]
  (println "Todas as compras n@" estabelecimento)
  (-> :estabelecimento
      (group-by lista-compras)
      (get estabelecimento)))

;(pprint (lista-compras-por-estabelecimento "Cinema" lista-compras))

;CALCULAR O TOTAL DA FATURA DE UM MÊS
(defn get-extrato-mes [cartao mes lista-compras]
  (->> lista-compras
       (lista-compras-por-mes mes)
       (filter #(= (:cartao %) cartao))))

(defn total-gasto-no-mes [cartao mes lista-compras]
  (println "Todas as compras do cartão" cartao "no mês" mes ": R$ ")
  (->> lista-compras
       (get-extrato-mes cartao mes)
       (map :valor)
       (reduce +)))

;(println (total-gasto-no-mes 1234123412341234 "01" lista-compras))

;FILTRAR COMPRAS NUM INTERVALO DE VALORES
(defn filtro-maximo-minimo [lista-compras valormax valormin]
  (println "Compras realizadas entre R$" valormin "e R$" valormax)
  (->> lista-compras
       (filter #(and (> (:valor %) valormin) (< (:valor %) valormax)))))

;(pprint (filtro-maximo-minimo lista-compras 130.0 84.0))

;AGRUPAR GASTOS POR CATEGORIA
(defn total-categoria [[categoria compras]]
  (->> compras
       (map :valor)
       (reduce +)
       (println categoria "R$")))

(defn gastos-por-categoria [lista-compras]
  (->> lista-compras
       (group-by :categoria)
       (map total-categoria)))

;(gastos-por-categoria lista-compras)

;LER ARQUIVO
(defn processa-csv [arquivo funcao-mapeamento]
  (->> (slurp arquivo)
       str/split-lines
       rest
       (map  #(str/split % #","))
       (mapv funcao-mapeamento)))

(defn lista-clientes []
  (pprint (processa-csv "dados/clientes.csv" (fn [[nome cpf email]]
                                               (y.cliente/novo-cliente nome cpf email)))))
;(lista-clientes)

(defn lista-clientes []
  (pprint (processa-csv "dados/cartoes.csv" (fn [[numero cvv validade limite cliente]]
                                              (y.cartao/novo-cartao numero cvv validade limite cliente)))))
;(lista-cartoes)

(defn lista-compras []
  (processa-csv "dados/compras.csv" (fn [[data valor estabelecimento categoria cartao]]
                                              (y.compra/nova-compra data valor estabelecimento categoria cartao))))


