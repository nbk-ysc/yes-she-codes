(ns yes-she-codes.core
  (:require [clojure.string :as cstr]
            [clojure-csv.core :as csv]
            [clojure.java.io :as io]))


;Carregar dados de um arquivo csv
(defn take-csv
  [fname]
  (with-open [file (io/reader fname)]
    (doall (map (comp first csv/parse-csv) (line-seq file)))))


;Transformar a os dados do csv em um hashmap
(defn csv-data->maps [csv-data]
  (map zipmap
       (->> (first csv-data)
            (map keyword)
            repeat)
       (rest csv-data)))

(defn novo-cliente
  [nome, cpf, email]
  (let [cliente {:nome nome, :cpf cpf, :email email}]
    cliente))

(defn novo-cartao
  [numero, cvv, validade, limite, cliente]
  (let [cartao {:numero numero, :cvv cvv, :validade validade, :limite limite, :cliente cliente}]
    cartao))


(defn compra-realizada
  [data, valor, estabelecimento, categoria, cartao]
  (let [compra {:data data, :valor valor, :estabelecimento estabelecimento, :categoria categoria, :cartao cartao}]
    compra))

;Calcular o total gasto em compras de um cartão
(defn total-gasto [compras]
  (->> compras
       (map :VALOR)
       (map bigdec)
       (reduce +)))


(defn lista-clientes []
  (csv-data->maps (take-csv (io/resource "clientes.csv"))))


(defn lista-compras []
  (csv-data->maps (take-csv (io/resource "compras.csv"))))


(defn lista-cartoes []
  (csv-data->maps (take-csv (io/resource "cartoes.csv"))))

;Buscar compras por mês
(defn compras-feitas-mes [mes compras]
  (filter (fn [compra] (cstr/includes? (compra :DATA) mes)) compras))

;Buscar compras por estabelecimento
(defn compras-feitas-estabelecimento [estabelecimento compras]
  (filter (fn [compra] (= (compra :ESTABELECIMENTO) estabelecimento)) compras))

;Filtrar compras num intervalo de valores
(defn compras-intervalo-valores [min max compras]
  (filter (fn [compra] (and (<= (bigdec (compra :VALOR)) max) (>= (bigdec (compra :VALOR)) min))) compras))


;Calcular o total da fatura de um mês
(defn total-gasto-no-mes [mes compras]
  (let [compras-mes (compras-feitas-mes mes compras)]
    (total-gasto compras-mes)))


(defn somar-total [compras]
  (->> compras
       (map :VALOR)
       (map bigdec)
       (reduce +)))

(defn total-por-categoria [[categoria compras]]
  {:CATEGORIA categoria
   :TOTAL (somar-total compras)})

(defn agrupar-por-categoria [compras]
  (->> compras
       (group-by :CATEGORIA)
       (map total-por-categoria)))



(println "agrupar por categoria" (agrupar-por-categoria (lista-compras)))
(println "total gasto com compras" (total-gasto (lista-compras)))
(println "total gasto no mes" (total-gasto-no-mes "2022-01" (lista-compras)))
(println "compras feitas no mes" (compras-feitas-mes "2022-01" (lista-compras)))
(println "compras feitas no estabelecimento" (compras-feitas-estabelecimento "Outback" (lista-compras)))
(println "compras no intervalo " (compras-intervalo-valores 20 50 (lista-compras)))
