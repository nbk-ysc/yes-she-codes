(ns yes-she-codes.respostas_semana2
  (:use clojure.pprint)
  (:require [yes-she-codes.db :as y.db]
            [model.cliente :as y.cliente]
            [model.cartao :as y.cartao]
            [model.compra :as y.compra]
            [yes-she-codes.util :as util]
            [clojure.string :as str]))


;LISTA A PARTIR DO CSV
(defn lista-clientes []
  (y.db/processa-csv "dados/clientes.csv" (fn [[nome cpf email]] )))

(defn lista-cartoes []
  (y.db/processa-csv "dados/cartoes.csv" (fn [[numero cvv validade limite cliente]]
                                              (y.cartao/novo-cartao numero cvv validade limite cliente))))
(defn lista-compras []
  (y.db/processa-csv "dados/compras.csv" (fn [[data valor estabelecimento categoria cartao]]
                                      (Compra. nil data valor estabelecimento categoria cartao))))


(def repositorio-de-compras (atom []))

;Criar Record para Compra
(defrecord Compra [^Long id
                   ^String data
                   ^BigDecimal valor
                   ^String estabelecimento
                   ^String categoria
                   ^Long cartao])

;DEFINE UMA COMPRA
(def compra-exemplo (Compra. nil "2022-01-01" 100 "Alura" "Educação" 4321432143214321))

;FUNÇÃO INSERIR COMPRA
(defn insere-compra [lista-de-compras compra]
  (let [id (util/proximo-id lista-de-compras)
        compra-com-id (assoc compra :id id)]
    (conj lista-de-compras compra-com-id)))


;FUNÇÃO INSERIR COMPRA NO ÁTOMO
(defn insere-compra! [compra atomo-das-compras]
  (swap! atomo-das-compras insere-compra compra))

;FUNÇÃO LISTAR COMPRA NO ÁTOMO
(defn lista-compras! [atomo-de-compras]
  @atomo-de-compras)


(defn carrega-compras! [atomo-de-compras]
  (doseq [compra (y.db/processa-csv "dados/compras.csv" (fn [[data valor estabelecimento categoria cartao]]
                                                     (Compra. nil data valor estabelecimento categoria cartao)))]
    (insere-compra! compra atomo-de-compras)))


;FUNÇÃO PESQUISAR COMPRA POR ID
(defn pesquisa-compra-por-id [compras id]
  (first (filter #(= id (:id %)) compras)))

;FUNÇÃO PESQUISAR COMPRA POR ATOMO
(defn pesquisa-compra-por-id! [id atomo-de-compras]
  (pesquisa-compra-por-id @atomo-de-compras id))


;FUNÇÃO EXCLUIR COMPRA POR ID
(defn exclui-compra [compras id]
  (remove #(util/deve-remover? id %) compras))

;FUNÇÃO EXCLUIR COMPRA POR ATOMO
(defn exclui-compra! [atomo-de-compras id ]
  (swap! atomo-de-compras exclui-compra id))

(carrega-compras! repositorio-de-compras)