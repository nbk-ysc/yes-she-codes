(ns yes_she_codes.logic
  (:require [clojure.string :as str]
            [clojure.data.csv :as csv]
            [clojure.java.io :as io]
            [clojure.string :as str]))

(defn total-gasto
  [compras]
  (->> compras
       (map :valor compras)
       (reduce +)))

(defn compra-dentro-do-mes?
  [mes compra]
  (= mes (.getMonth (:data compra))))

(defn buscar-compras-mes
  [mes compras]
  (filter #(compra-dentro-do-mes? mes %) compras))

(defn compra-dentro-do-estabelecimento?
  [estabelecimento compra]
  (= estabelecimento (:estabelecimento compra)))

(defn buscar-compras-por-estabelecimento
  [estabelecimento compras]
  (filter #(compra-dentro-do-estabelecimento? estabelecimento %) compras))

(defn total-gasto-no-mes
  [mes compras]
  (->> compras
       (buscar-compras-mes mes)
       total-gasto))

(defn valor-dentro-do-intervalo?
  [valor-minimo valor-maximo compra]
  (if (< valor-maximo valor-minimo)
    (let [valor-maximo-atualizado valor-minimo
          valor-minimo-atualizado valor-maximo]
      (and (> (:valor compra) valor-minimo-atualizado) (< (:valor compra) valor-maximo-atualizado)))

    (and (> (:valor compra) valor-minimo) (< (:valor compra) valor-maximo)))
  )

(defn compras-por-intervalo-de-valor
  [valor-minimo valor-maximo compras]
  (filter #(valor-dentro-do-intervalo? valor-minimo valor-maximo %) compras))

(defn total-gasto-por-categoria
  [[nome estabelecimento]]
  {:estabelecimento nome
   :total           (->> estabelecimento
                         (map :valor)
                         (reduce +))})

(defn gastos-agrupados-por-categoria
  [compras]
  (->> compras
       (group-by :estabelecimento)
       (map total-gasto-por-categoria)))

(defn deaccent
  "Funcao que remove acentos de palavras"
  [str]
  (let [normalized (java.text.Normalizer/normalize str java.text.Normalizer$Form/NFD)]
    (clojure.string/replace normalized #"\p{InCombiningDiacriticalMarks}+" "")))

(defn csv-data->maps
  "Funcao que a partir de um arquivo, cria um map"
  [csv-data]
  (mapv zipmap
        (->> (first csv-data)
             (map str/lower-case)
             (map deaccent)
             (map keyword)
             repeat)
        (rest csv-data)))

(defn csv-to-map
  "Funcao que faz a leitura dos arquivos"
  [arquivo]
  (with-open [reader (io/reader arquivo)]
    (let [arquivo-lido (csv/read-csv reader)
          mapa (csv-data->maps arquivo-lido)]
      mapa)))

(defn string-to-java-date-completa
  [data]
  (let [format (java.text.SimpleDateFormat. "yyyy-MM-dd")]
    (.parse format data)))

(defn string-to-java-date-parcial
  [data]
  (let [format (java.text.SimpleDateFormat. "yyyy-MM")]
    (.format (java.text.SimpleDateFormat. "yyyy-MM") (.parse format data))))

(defn remove-caracteres-em-branco
  [numero]
  (str/replace numero #"[ ]" ""))

(defn remove-caracteres-do-cpf
  [cpf]
  (str/replace cpf #"(?i)[.-]" ""))

(defn string-to-long
  [numero]
  (Long/parseLong numero))

(defn formata-numero-cartao
  [numero]
  (string-to-long (remove-caracteres-em-branco numero)))

(defn extrai-dados-clientes
  [cliente]
  [(:nome cliente), (remove-caracteres-do-cpf (:cpf cliente)), (:email cliente)])

(defn extrai-dados-cartoes
  [cartao]
  [(formata-numero-cartao (:numero cartao)), (string-to-long (:cvv cartao)), (string-to-java-date-parcial (:validade cartao)),
   (bigdec (:limite cartao)), (remove-caracteres-do-cpf (:cliente cartao))])

(defn extrai-dados-compras
  [compra]
  [(string-to-java-date-completa (:data compra)), (bigdec (:valor compra)), (:estabelecimento compra),
   (:categoria compra), (formata-numero-cartao (:cartao compra))])