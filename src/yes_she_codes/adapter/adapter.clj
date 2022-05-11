(ns yes-she-codes.adapter.adapter
  (:require [yes-she-codes.model.model :as m]
            [clojure.string :as str]
            [clojure.java.io :as io]))

(defn criar-cliente
  [[nome cpf email]]
  (m/novo-cliente nome cpf email))


(defn criar-cartao
  [[numero cvv validade limite cliente]]
  (m/novo-cartao numero cvv validade limite cliente))


(defn criar-compra
  [[data valor estabelecimento categoria cartao]]
  (m/nova-compra data valor estabelecimento categoria cartao))


(defn parse-input-cliente
  "transforma os dados lidos em string para os tipos primitivos adequados ao model"
  [[nome cpf email]]
  [nome cpf email])


(defn parse-input-cartao
  "transforma os dados lidos em string para os tipos primitivos adequados ao model"
  [[numero                                        cvv                   validade  limite           cliente]]
   [(Long/parseLong (str/replace numero " " ""))  (Long/parseLong cvv)  validade  (bigdec limite)  cliente])


(defn parse-input-compra
  "transforma os dados lidos em string para os tipos primitivos adequados ao model"
  [[data  valor           estabelecimento  categoria   cartao]]
   [data  (bigdec valor)  estabelecimento  categoria   (Long/parseLong (str/replace cartao " " ""))])


(defn arquivo->vetor [path-arquivo]
  (try
    (with-open [rdr (io/reader path-arquivo)]
      (into [] (line-seq rdr)))
    (catch Exception e
      (println "Error:" (.getMessage e)))))


(defn transformar-dados-arquivo-em-model
  [path-arquivo fn-parse fn-model]
  (let [input-vector (arquivo->vetor path-arquivo)]
    (->> input-vector
         rest
         (map #(str/split % #","))
         (map fn-parse)
         (mapv fn-model))))

