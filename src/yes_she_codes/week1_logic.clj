(ns yes-she-codes.week1-logic
  (:require [clojure.string :as str])
  (:import [java.time LocalDate YearMonth Month]))


(defn novo-cliente
    ;;para aceitar lista db: destructuring
  ([[nome cpf email]]
   (novo-cliente nome cpf email))

    ([nome cpf email]
     (let [cadastro {:nome  nome
                     :cpf   cpf
                     :email email}]
       cadastro)))

(defn string->long
  "Given a string with digits and, optionally, spaces,
returns a long parsed from the string with the spaces
removed."
  [string]
  (Long/parseLong (str/replace string #" " "")))


(defn novo-cartao
  ([[numero cvv validade limite cliente]]
   (novo-cartao numero cvv validade limite cliente))

  ([numero cvv validade limite cliente]
   (let [cadastro {:numero   (string->long numero)
                   :cvv      (string->long cvv)
                   :validade (YearMonth/parse validade)
                   :limite   (bigdec limite)
                   :cliente  cliente}]
     cadastro)))


(defn nova-compra
  ([[data valor estabelecimento categoria cartao]]
   (nova-compra data valor estabelecimento categoria cartao))

  ([data valor estabelecimento categoria cartao]
   (let [cadastro {:data            (LocalDate/parse data)
                   :valor           (bigdec valor)
                   :estabelecimento estabelecimento
                   :categoria       categoria
                   :cartao          (string->long cartao)}]
     cadastro)))


(defn abre-csv
  [path]
  (->> (slurp path)
       str/split-lines
       rest
       (map #(str/split % #","))))


(defn lista-clientes
  ([]
   (let [clientes-csv
         (abre-csv "resources/clientes.csv")]
     (lista-clientes clientes-csv)))

  ([lista-de-listas]
   ;;input manual
   (vec (map novo-cliente lista-de-listas))))


(defn lista-cartoes
  ([]
   (let [cartoes-csv
         (abre-csv "resources/cartoes.csv")]
     (lista-cartoes cartoes-csv)))

  ([lista-de-listas]
   (vec (map novo-cartao lista-de-listas))))


(defn lista-compras
  ([]
   (let [compras-csv
         (abre-csv "resources/compras.csv")]
     (lista-compras compras-csv)))

  ([lista-de-listas]
   (vec (map nova-compra lista-de-listas))))


(defn total-gasto
  [compras]
  (->> compras
       (map :valor)
       (reduce +)))


(defn mes-do-object
  [data]
  (Month/from data))


(defn compras-daquele-mes
  ;;mês int
  [mes compras]
  (let [mes-input (Month/of mes)]
    (filter #(= (mes-do-object (:data %)) mes-input) compras)))


(defn compras-daquele-estabelecimento
  [estabelecimento compras]
   (filter #(= (:estabelecimento %) estabelecimento) compras))


(def total-gasto-no-mes (comp total-gasto compras-daquele-mes))


(defn intervalo-valor
  [min max compras]
  (filter #(and (>= (:valor %) min)
                (<= (:valor %) max))
          compras))


(defn total-categoria
  [compras]
  (let [nome-e-total (fn [[categoria info]]
                       {:categoria categoria
                        :valor-total (total-gasto info)})]
    (->> compras
         (group-by :categoria)
         (map nome-e-total))))
