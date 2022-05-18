(ns yes_she_codes.semana1.logic
  (:require [clojure.string :as str]
            [clojure.data.csv :as csv]
            [clojure.java.io :as io]))

(defn total-gasto
  "Funcao responsavel por calcular todo o valor gasto em uma lista de compras"
  [compras]
  (->> compras
       (map :valor compras)
       (reduce +)))

(defn compra-dentro-do-mes?
  "Funcao que compara o mes de uma compra com o mes buscado"
  [mes compra]
  (= mes (.getMonth (:data compra))))

(defn buscar-compras-mes
  "Funcao responsavel por buscar quais compras foram feitas em determinado mes passado como parametro"
  [mes compras]
  (filter #(compra-dentro-do-mes? mes %) compras))

(defn compra-dentro-do-estabelecimento?
  "Funcao responsavel por comparar se o nome do estabelecimento da compra
  eh igual ao nome passado como parametro"
  [estabelecimento compra]
  (= estabelecimento (:estabelecimento compra)))

(defn buscar-compras-por-estabelecimento
  "Funcao responsavel por buscar quais compras foram feitas no estabecelimento passado como parametro"
  [estabelecimento compras]
  (filter #(compra-dentro-do-estabelecimento? estabelecimento %) compras))

(defn total-gasto-no-mes
  "Funcao responsavel por calcular todo o gasto de uma lista de compras de um determinado mes"
  [mes compras]
  (->> compras
       (buscar-compras-mes mes)
       total-gasto))

(defn valor-dentro-do-intervalo?
  "Funcao responsavel por comparar se o valor de uma compra esta entre dois intervalos"
  [valor-minimo valor-maximo compra]
  (if (< valor-maximo valor-minimo)
    (let [valor-maximo-atualizado valor-minimo
          valor-minimo-atualizado valor-maximo]
      (and (> (:valor compra) valor-minimo-atualizado) (< (:valor compra) valor-maximo-atualizado)))

    (and (> (:valor compra) valor-minimo) (< (:valor compra) valor-maximo))))

(defn compras-por-intervalo-de-valor
  "Funcao responsavel por retornar compras que tenham o valor entre dois intervalos passados como parametro"
  [valor-minimo valor-maximo compras]
  (filter #(valor-dentro-do-intervalo? valor-minimo valor-maximo %) compras))

(defn total-gasto-por-categoria
  "Funcao responsavel por calcular o total gasto de uma determinada categoria de compras"
  [[nome estabelecimento]]
  {:estabelecimento nome
   :total           (->> estabelecimento
                         (map :valor)
                         (reduce +))})

(defn gastos-agrupados-por-categoria
  "Funcao responsavel por agrupar uma lista de compras por categoria"
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
  "Funcao que transforma uma data em string para o tipo Date em java"
  [data]
  (let [format (java.text.SimpleDateFormat. "yyyy-MM-dd")]
    (.parse format data)))

(defn string-to-java-date-parcial
  "Funcao que transforma uma data em string para o tipo Date em java em formato reduzido (yyyy-MM)"
  [data]
  (let [format (java.text.SimpleDateFormat. "yyyy-MM")]
    (.format (java.text.SimpleDateFormat. "yyyy-MM") (.parse format data))))

(defn remove-caracteres-em-branco
  "Funcao que remove espacos em brancos dentro de uma string"
  [numero]
  (str/replace numero #"[ ]" ""))

(defn remove-caracteres-do-cpf
  "Funcao que remove os caracteres . e - de uma string"
  [cpf]
  (str/replace cpf #"[.-]" ""))

(defn string-to-long
  "Funcao responsavel por transformar o tipo string em Long"
  [numero]
  (Long/parseLong numero))

(defn formata-numero-cartao
  "Funcao responsavel por combinar a remocao de espacoes em branco de uma string e depois transforma-la em Long"
  [numero]
  (string-to-long (remove-caracteres-em-branco numero)))

(defn extrai-dados-clientes
  "Funcao responsavel por extrair os dados de um cliente a partir de um hashmap e
  chamar funcoes que fazem o tratamento dos dados"
  [cliente]
  [(:nome cliente), (remove-caracteres-do-cpf (:cpf cliente)), (:email cliente)])

(defn extrai-dados-cartoes
  "Funcao responsavel por extrair os dados de um cartao a partir de um hashmap e
  chamar funcoes que fazem o tratamento dos dados"
  [cartao]
  [(formata-numero-cartao (:numero cartao)), (string-to-long (:cvv cartao)), (string-to-java-date-parcial (:validade cartao)),
   (bigdec (:limite cartao)), (remove-caracteres-do-cpf (:cliente cartao))])

(defn extrai-dados-compras
  "Funcao responsavel por extrair os dados de uma compra a partir de um hashmap e
  chamar funcoes que fazem o tratamento dos dados"
  [compra]
  [(string-to-java-date-completa (:data compra)), (bigdec (:valor compra)), (:estabelecimento compra),
   (:categoria compra), (formata-numero-cartao (:cartao compra))])