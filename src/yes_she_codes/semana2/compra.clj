(ns yes-she-codes.semana2.compra
  (require yes-she-codes.semana2.db :as y.db)
  (:use [clojure.pprint]))

(def repositorio-de-compras  (atom []))

(defrecord Compras-com-id [^Long id, ^String data, ^BigDecimal valor,
                    ^String estabelecimento, ^String categoria, ^Long cartao, ^String operacao])

; exemplo de uma compra
(def compra-debito-outback (map->Compras-com-id {:data            "2022-01-01",
                              :valor           129.90,
                              :estabelecimento "Outback",
                              :categoria       "Alimentação",
                              :cartao          1234123412341234,
                              :operacao        "debito"}))
; jogar no utils
; Define o id da compra
(defn proximo-id [entidades]
  (if-not (empty? entidades)
    (+ 1 (apply max (map :id entidades)))
    1))

; Insere compra associando o id dessa compra
(defn insere-compra [lista-de-compras compra]
  (let [id-da-compra (proximo-id lista-de-compras)
        compra-com-id (assoc compra :id id-da-compra)]
    (conj lista-de-compras compra)))

; Insere as compras no atomo
(defn insere-compra! [compra atomo-das-compras]
  (swap! atomo-das-compras insere-compra compra))

; Lista os valores dentro do atomo
(defn listar-compras! [atomo-das-compras]
  @atomo-das-compras)

;Carrega compras atraves de um csv
(defn carrega-compras! [atomo-das-compras]
  (doseq [compra (y.db/processa-csv "dados/csv"
                                    (fn [[data valor estabelecimento categoria cartao]]
                                      (Compras-com-id. nil data valor estabelecimento categoria cartao)))]
    (insere-compra! compra atomo-das-compras)))

;Pesquisa a compra por id
(defn pesquisa-compra-id [compras id]
  (first
    (filter #(= id (:id %))
               compras)))

;Pesquisa por id do atomo
(defn pesquisa-por-id! [id atomo-de-compras]
  (pesquisa-compra-id @atomo-de-compras id))
