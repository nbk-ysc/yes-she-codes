(ns yes-she-codes.compras-atom
  (:require [yes-she-codes.model :as y.model]
            [yes-she-codes.logic :as y.logic])
  (:use [clojure.pprint]))

;1- Definir átomo como banco de dados em memória, vazio
(def repositorio-de-compras (atom []))
;(pprint (class @repositorio-de-compras))

;2- Criar Record para Compra
(defrecord Compras [^Long id, ^String data, ^BigDecimal valor, ^String estabelecimento, ^String categoria, ^Long cartao])

;3- Criar a função insere-compra. Ela vai atribuir um id a uma compra e armazená-la num vetor.
(defn insere-compra [vetor-compras-cadastradas record-compras]
  (->> vetor-compras-cadastradas
       (y.logic/proximo-id)
       (assoc record-compras :id)
       (conj vetor-compras-cadastradas)))



;;;;;;;;testes;;;;;;;;;;;
(def vetor-de-compras (y.model/lista-compras))
(def compra1 (map->Compras {:data "2022-05-10",
                            :valor 100.00M,
                            :estabelecimento "Alura",
                            :categoria "Educação",
                            :cartao 3939393939393939}))

(pprint (insere-compra vetor-de-compras compra1))
;(pprint (cria-atom-crompas "2022-01-02" "260.00" "Dentista" "Saúde" "1234123412341234"))



;(update vetor-compras-cadastradas :id conj (y.logic/proximo-id record-compras))

;(pprint (assoc (Paciente. nil "Guilherme" "18/09/1981") :id 38))
;(map (fn [[data valor estabelecimento categoria cartao]]
;                     (nova-compra data valor estabelecimento categoria cartao))
;                   (lista-registros-compras))

;(def paciente (->Compras nil "2022-01-02" "260.00" "Dentista" "Saúde" "1234123412341234"))
;(pprint (:data paciente))

;(def compras (map->Compras {}))



;(pprint (y.model/lista-compras))
;(pprint (insere-compra (map->Compras {}) (y.model/lista-compras)))

;(defn insere-compra! [record-compra atomo-repositorio-compras]
;  (swap! atomo-repositorio-compras insere-compra )
;  )
;
;(defn chega-sem-malvado! [hospital pessoa]
;  (swap! hospital h.logic/chega-em :espera pessoa)
;  (println "apos inserir" pessoa))
;
;(defn chega-em
;  [hospital departamento pessoa]
;  (if (cabe-na-fila? hospital departamento)
;    (update hospital departamento conj pessoa)
;    (throw (ex-info "Fila já está cheia" {:tentando-adicionar pessoa}))))