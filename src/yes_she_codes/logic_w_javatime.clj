(ns yes_she_codes.logic_w_javatime
  (:require [yes_she_codes.db :as ysc.db]
            [clojure.string :as str]))
;[clojure-csv.core :as csv]))

(require '[clojure.data.csv :as csv]
         '[clojure.java.io :as io]
         '[java-time :as time])

;;UTILS
(defn limpa-whitespace
  [string]
  (str/replace string " " ""))

(defn mesmo-mes?
  [mes compra]
  (.contains (get compra :data) mes))

(defn mesmo-estabelecimento?
  [estabelecimento compra]
  (= (get compra :estabelecimento) estabelecimento))

(defn mesmo-cartao?
  [numero_string compra]
  (let [numero (long (bigdec (limpa-whitespace numero_string)))]
    (= (get compra :cartao) numero)))

(defn valor-no-intervalo?
  [valor-min valor-max compra]
  (let [valor (get compra :valor)]
    (and (>= valor valor-min) (<= valor valor-max) )))


;;CLIENTE
(defn novo-cliente
  [nome cpf email]
  {:nome nome :cpf cpf :email email})

(defn transforma-cliente
  [[nome cpf email]]
  (novo-cliente nome, cpf, email))

(defn lista-clientes
  [registro]
  (map transforma-cliente registro))


;;CARTAO
(defn novo-cartao
  [numero cvv validade limite cliente]
  {:numero (long (bigdec numero)) :cvv (long (bigdec cvv)) :validade validade :limite (bigdec limite) :cliente cliente})

(defn transforma-cartao
  [[numero-espaco cvv validade limite cliente]]
  (let [numero (limpa-whitespace numero-espaco)]
    (novo-cartao numero cvv validade limite cliente)))

(defn lista-cartoes
  [registro]
  (map transforma-cartao registro))


;COMPRAS
(defn nova-compra
  [data valor estabelecimento categoria cartao]
  {:data data :valor (bigdec valor) :estabelecimento estabelecimento :categoria categoria :cartao (long (bigdec cartao))})

(defn transforma-compra
  [[data valor estabelecimento categoria cartao-espaco]]
  (let [cartao (limpa-whitespace cartao-espaco)]
    (nova-compra data valor estabelecimento categoria cartao)))

(defn lista-compras
  [registro]
  (map transforma-compra registro))


;;OUTRAS FUNÇÕES
(defn total-gastos
  [lista-compras]
  (reduce + (map :valor lista-compras)))

(defn compra-por-mes
  [mes lista-compras]
  (filter #(mesmo-mes? mes %) lista-compras))

(defn compra-por-estabelecimento
  [estabelecimento lista]
  (filter #(mesmo-estabelecimento? estabelecimento %) lista))

(defn compra-por-cartao
  [numero lista]
  (filter #(mesmo-cartao? numero %) lista))

(defn total-gasto-por-mes
  [mes lista]
  (->> lista
       (compra-por-mes mes)
       (map :valor)
       (reduce +)))

(defn total-gasto-por-mes-por-cartao
  [mes numero lista]
  (->> lista
       (compra-por-mes mes)
       (compra-por-cartao numero)
       (map :valor)
       (reduce +)))

(defn filtra-compras-por-intervalo
  [valor-min valor-max lista]
  (filter #(valor-no-intervalo? valor-min valor-max %) lista))

(defn total-por-grupo
  [[grupo compras]]
  {grupo (->> compras
              (map :valor)
              (reduce +))})

(defn total-por-categoria
  [compras]
  (->> compras
       (group-by :categoria)
       (map total-por-grupo)))

(defn csv-para-vec
  [file]
  (with-open [reader (io/reader file)]
    (doall
      (->> (csv/read-csv reader)
           rest
           (map vec)))))

;; API JAVA TIME

(def data1 (time/year-month "yyyy-MM" "2022-03"))
(def data2 (time/local-date "yyyy-MM-dd" "2023-03-02"))

;; CARTOES
(defn novo-cartao-javatime
  [numero cvv validade limite cliente]
  {:numero (long (bigdec numero)) :cvv (long (bigdec cvv)) :validade (time/year-month "yyyy-MM" validade) :limite (bigdec limite) :cliente cliente})

(defn transforma-cartao-javatime
  [[numero-espaco cvv validade limite cliente]]
  (let [numero (limpa-whitespace numero-espaco)]
    (novo-cartao-javatime numero cvv validade limite cliente)))

(defn lista-cartoes-javatime
  [registro]
  (map transforma-cartao-javatime registro))

;; COMPRAS
(defn nova-compra-javatime
  [data valor estabelecimento categoria cartao]
  {:data (time/local-date "yyyy-MM-dd" data) :valor (bigdec valor) :estabelecimento estabelecimento :categoria categoria :cartao (long (bigdec cartao))})

(defn transforma-compra-javatime
  [[data valor estabelecimento categoria cartao-espaco]]
  (let [cartao (limpa-whitespace cartao-espaco)]
    (nova-compra-javatime data valor estabelecimento categoria cartao)))

(defn lista-compras-javatime
  [registro]
  (map transforma-compra-javatime registro))

;; TESTA APENAS MES
(defn mesmo-mes?
  [mes-string compra]
  (let [mes (time/month "MM" mes-string)]
    (= (time/month (get compra :data)) mes)))

;; TESTA MES E ANO
(defn mesmo-mes-e-ano?
  [mes-string compra]
  (let [mes (time/year-month "yyyy-MM" mes-string)]
    (and
      (= (time/year (get compra :data)) (time/year mes))
      (= (time/month (get compra :data)) (time/month mes)))))

(defn compra-por-mes
  [mes lista-compras]
  (filter #(mesmo-mes? mes %) lista-compras))

(defn compra-por-mes-do-ano
  [mes lista-compras]
  (filter #(mesmo-mes-e-ano? mes %) lista-compras))
