(ns yes-she-codes.week1_logic
  (:import [java.time LocalDate YearMonth Month])
  ;(:use clojure-csv.core parse-csv)
  (:require [clojure-csv.core :as csv]
            [clojure.java.io :as io]))


  (defn novo-cliente
  ([nome cpf email]
   (let [cadastro {:nome  nome
                   :cpf   cpf
                   :email email}]
     cadastro))

  ([[nome cpf email]]                                       ;para aceitar lista db
   (novo-cliente nome cpf email)))

(defn converte-long
  [string]
  (Long/parseLong (clojure.string/replace string #" " "")))


(defn novo-cartao
  ([numero cvv validade limite cliente]
   (let [cadastro {:numero   (converte-long numero)
                   :cvv      (converte-long cvv)
                   :validade (YearMonth/parse validade)
                   :limite   (bigdec limite)
                   :cliente  cliente}]
     cadastro))

  ([[numero cvv validade limite cliente]]
   (novo-cartao numero cvv validade limite cliente)))


(defn nova-compra
  ([data valor estabelecimento categoria cartao]
   (let [cadastro {:data            (LocalDate/parse data)
                   :valor           (bigdec valor)
                   :estabelecimento estabelecimento
                   :categoria       categoria
                   :cartao          (converte-long cartao)}]
     cadastro))

  ([[data valor estabelecimento categoria cartao]]
   (nova-compra data valor estabelecimento categoria cartao)))

(defn abre-csv
  [path]
  (with-open [arquivo (io/reader path)]
    (csv/parse-csv (slurp arquivo))))


(defn lista-clientes
  ([lista-de-listas]                                        ;input manual
   (vec (map novo-cliente lista-de-listas)))

  ([]
   (let [clientes-csv
         (abre-csv "resources/clientes.csv")]
     (lista-clientes (rest clientes-csv)))))


(defn lista-cartoes
  ([lista-de-listas]
   (vec (map novo-cartao lista-de-listas)))

  ([]
   (let [cartoes-csv
         (abre-csv "resources/cartoes.csv")]
     (lista-cartoes (rest cartoes-csv)))))


(defn lista-compras
  ([lista-de-listas]
   (vec (map nova-compra lista-de-listas)))

  ([]
   (let [compras-csv
         (abre-csv "resources/compras.csv")]
     (lista-compras (rest compras-csv)))))



(defn total-gasto
  [compras]
  (let [valores (map :valor compras)]
    (reduce + valores)))


(defn mes-do-object
  [data]
  (Month/from data))

(defn compras-daquele-mes
  [mes compras]                                             ;mês int
  ;(filter #(clojure.string/includes? (:data %) (str mes \-)) compras) ;sem javatime
  (let [mes-input (Month/of mes)]
    (filter #(= (mes-do-object (:data %)) mes-input) compras)))

(defn compras-daquele-estabelecimento
  [estabelecimento compras]
   (filter #(= (:estabelecimento %) estabelecimento) compras))


(defn total-gasto-no-mes
  [mes compras]
  (total-gasto (compras-daquele-mes mes compras)))


(defn intervalo-valor
  [min max compras]
  (let [maior? (filter #(>= (:valor %) min) compras)
        menor? (filter #(<= (:valor %) max) maior?)]
    menor?))


(defn total-categoria
  [compras]

  (let [nome-e-total (fn [[categoria info]]
                       {:categoria categoria
                         :valor-total (total-gasto info)})]

    (->> compras
         (group-by :categoria)
         (map nome-e-total))))
