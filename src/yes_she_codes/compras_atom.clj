(ns yes-she-codes.compras-atom
  (:require [yes-she-codes.model :as y.model]
            [yes-she-codes.logic :as y.logic])
  (:use [clojure.pprint]))

(def vetor-de-compras (y.model/lista-compras))
(def compra1 (map->Compras {:data            "2022-05-10",
                            :valor           100.00M,
                            :estabelecimento "Alura",
                            :categoria       "Educação",
                            :cartao          3939393939393939}))
(def compra2 (map->Compras {:id              15
                            :data            "2022-03-10",
                            :valor           50.00M,
                            :estabelecimento "Drograria",
                            :categoria       "Saúde",
                            :cartao          1234123412341234}))
(def compra3 (map->Compras {:id              6
                            :data            "2022-05-10",
                            :valor           300.00M,
                            :estabelecimento "Cinema",
                            :categoria       "Lazer",
                            :cartao          3939393939393939}))
(def compra4 (map->Compras {:id              6
                            :data            "2021-05-10",
                            :valor           450.00M,
                            :estabelecimento "Ingles",
                            :categoria       "Educação",
                            :cartao          3939393939393939}))

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
       (conj vetor-compras-cadastradas)
       ))

;4- Inserir compra no átomo
(defn insere! [atomo-compras record-compras]
  (swap! atomo-compras into (insere-compra @atomo-compras record-compras)))

;5- Crie a função lista-compras!, que lista uma lista de compras de um átomo.
(defn lista-compras! [atomo-compras]
  (println "atomo" @atomo-compras))
'
;6- Crie a função pesquisa-compra-por-id, que pesquisa por uma compra de determinado id num vetor.
(defn pesquisa-compra-por-id [id vetor-compras]
  (->> vetor-compras
       (filter #(= (:id %) id))
       first))

;7- Crie a função pesquisa-compra-por-id!, que pesquisa por uma compra em um átomo.
(defn pesquisa-compra-por-id! [id atomo-compras]
  (pesquisa-compra-por-id id @atomo-compras)
  )

;8- Crie a função exclui-compra, que exclui uma compra de determinado id de um vetor.
;Parâmetros da função:
;id da compra a ser excluída;
;vetor de compras.
;Retorno da função:
;Novo vetor sem a compra excluída.
;Critérios de aceitação:
;Caso a compra não exista, retornar o vetor original recebido por parâmetro.





;;;;;;;;testes;;;;;;;;;;;
;teste 3
;(println "Teste3: ") (pprint (insere-compra vetor-de-compras compra1))
;teste 4
;(reset! repositorio-de-compras [])
;(pprint (insere! repositorio-de-compras compra2))
;teste 5
;(lista-compras! repositorio-de-compras)
;teste 6
;(pprint (pesquisa-compra-por-id 1 [{:id 1 :data "2022-04-10" :valor 150.00M} {:id 7} compra2]))
;teste 7
;(println "\n Pesquisa")(pprint (pesquisa-compra-por-id! 4  repositorio-de-compras))


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