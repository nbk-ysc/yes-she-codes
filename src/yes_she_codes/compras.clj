(ns yes-she-codes2.compras
  (:require [yes-she-codes2.db :as y.db])
  (:require [clojure.string :as str]))









(defn nova-compra [data, valor, estabelecimento, categoria, cartao]
  {:data            data,
   :valor           valor,
   :estabelecimento estabelecimento,
   :categoria       categoria,
   :cartao          cartao})

(defn transforma-compra [[data, valor, estabelecimento, categoria, cartao]]
  (nova-compra data, valor, estabelecimento, categoria, cartao))

(defn transforma-compras [array]
  (map transforma-compra array))

(defn lista-compras []
  (transforma-compras (y.db/busca-registros-de-compras)))

(defn compra [x y]
  (+ x (:valor y)))

(defn total-gasto [lista-de-compras]
  (reduce compra 0 lista-de-compras))

(println "Valor total das compras de todos os cartões" (total-gasto (lista-compras)))

;fiz essa etapa para encontrar o mes

(defn split-data [data]
  (str/split data #"-"))

(defn get-mes [data]
  (get (split-data data) 1))

(defn get-mes-da-compra [compra]
  (get-mes (:data compra)))

(defn compra-realizada-no-mes? [mes compra]
  (= mes (get-mes-da-compra compra)))

(defn compras-no-mes [mes lista-de-compras]
  (filter #(compra-realizada-no-mes? mes %) lista-de-compras))

(println "achei o mes 04?" (compras-no-mes "04" (lista-compras)))

;fiz essa etapa para somar valores de um mes da lista

(def total-gasto-no-mes (comp total-gasto compras-no-mes))
(println "Valor total das compras de um mes" (total-gasto-no-mes "04" (lista-compras)))

;fiz essa etapa para encontrar o estabelecimento

(defn achei-o-estabelecimento? [local todas-as-compras]
  (= local (:estabelecimento todas-as-compras)))

(defn comprei-no-estabelecimento? [local lista-de-compras]
  (filter #(achei-o-estabelecimento? local %) lista-de-compras))

(println "achei o estabelecimento?" (comprei-no-estabelecimento? "Alura" (lista-compras)))