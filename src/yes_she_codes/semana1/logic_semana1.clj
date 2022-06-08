(ns yes_she_codes.semana1.logic_semana1
  (:require [yes_she_codes.semana1.db :as ysc.db]
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
  (= mes (second (re-matches #"\d{4}-(\d{2})-\d{2}" (get compra :data)))))

(defn mesmo-ano-mes?
  [mes compra]
  (.contains (get compra :data) mes))

(defn mesmo-estabelecimento?
  [estabelecimento compra]
  (= (get compra :estabelecimento) estabelecimento))

(defn mesmo-cartao?
  [numero compra]
  (= (get compra :cartao) numero))

(defn valor-no-intervalo?
  [valor-min valor-max compra]
  (let [valor (get compra :valor)]
    (and (>= valor valor-min) (<= valor valor-max) )))

(defn processa-csv [caminho-arquivo funcao-mapeamento]
  (->> (slurp caminho-arquivo)
       clojure.string/split-lines
       rest
       (map #(clojure.string/split % #","))
       funcao-mapeamento))


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

(defn lista-clientes-csv
  [caminho]
  (processa-csv caminho lista-clientes))


;;CARTAO
(defn novo-cartao
  [numero_espaco cvv validade limite cliente]
  (let [numero (limpa-whitespace numero_espaco)]
    {:numero (long (bigdec numero)) :cvv (long (bigdec cvv)) :validade validade :limite (bigdec limite) :cliente cliente}))

(defn transforma-cartao
  [[numero-espaco cvv validade limite cliente]]
  (let [numero (limpa-whitespace numero-espaco)]
    (novo-cartao numero cvv validade limite cliente)))

(defn lista-cartoes
  [registro]
  (map transforma-cartao registro))

(defn lista-cartoes-csv
  [caminho]
  (processa-csv caminho lista-cartoes))


;COMPRA
(defn nova-compra
  [data valor estabelecimento categoria cartao_espaco]
  (let [cartao (limpa-whitespace cartao_espaco)]
    {:data data :valor (bigdec valor) :estabelecimento estabelecimento :categoria categoria :cartao (long (bigdec cartao))}))

(defn transforma-compra
  [[data valor estabelecimento categoria cartao-espaco]]
  (let [cartao (limpa-whitespace cartao-espaco)]
    (nova-compra data valor estabelecimento categoria cartao)))

(defn lista-compras
  [registro]
  (map transforma-compra registro))

(defn lista-compras-csv
  [caminho]
  (processa-csv caminho lista-compras))


;;OUTRAS FUNÇÕES
(defn total-gastos
  [lista-compras]
  (reduce + (map :valor lista-compras)))

(defn compra-por-mes
  [mes lista-compras]
  (filter #(mesmo-mes? mes %) lista-compras))

(defn compra-por-ano-mes
  [mes lista-compras]
  (filter #(mesmo-ano-mes? mes %) lista-compras))

(defn compra-por-estabelecimento
  [estabelecimento lista]
  (filter #(mesmo-estabelecimento? estabelecimento %) lista))

(defn compra-por-cartao
  [numero lista]
  (filter #(mesmo-cartao? numero %) lista))

(def total-gasto-por-mes (comp total-gastos compra-por-mes))

(def total-gasto-por-ano-mes (comp total-gastos compra-por-ano-mes))


;(defn total-gasto-por-mes
;  [mes lista]
;  (->> lista
;       (compra-por-mes mes)
;       (map :valor)
;       (reduce +)))
;
;(defn total-gasto-por-ano-mes
;  [mes lista]
;  (->> lista
;       (compra-por-ano-mes mes)
;       (map :valor)
;       (reduce +)))

(defn total-gasto-por-mes-por-cartao
  [mes numero lista]
  (->> lista
       (compra-por-mes mes)
       (compra-por-cartao numero)
       (map :valor)
       (reduce +)))

(defn total-gasto-por-ano-mes-por-cartao
  [mes numero lista]
  (->> lista
       (compra-por-ano-mes mes)
       (compra-por-cartao numero)
       (map :valor)
       (reduce +)))

(defn filtra-compras-por-intervalo
  [valor-min valor-max lista]
  (filter #(valor-no-intervalo? valor-min valor-max %) lista))

(defn total-por-categoria
  [compras]
  (->> compras
       (group-by :categoria)
       (map (fn [[categoria compras]]
              {:categoria categoria
               :valor (total-gastos compras)}))))

(processa-csv "/Users/carolina.nunes/IdeaProjects/yes-she-codes/files/compras.csv" transforma-compra)