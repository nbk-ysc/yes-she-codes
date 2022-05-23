(ns yes-she-codes.atom
  (:use clojure.pprint)
  (:require [yes-she-codes.db :as y.db]
            [model.cliente :as y.cliente]
            [model.cartao :as y.cartao]
            [model.compra :as y.compra]
            [clojure.string :as str]))


;SEMANA 2

;Definir átomo como banco de dados em memória
(def repositorio-de-compras (atom {}))

;Criar Record para Compra
(defrecord Compra [^Long id
                   ^String data
                   ^BigDecimal valor
                   ^String estabelecimento
                   ^String categoria
                   ^Long cartao])

;(pprint (Compra. nil "2022-01-01" 10M "Alura" "Educação" 4321432143214321))
;(pprint compra-exemplo)

;DEFINE UMA COMPRA
(def compra-exemplo (Compra. nil "2022-01-01" 100 "Alura" "Educação" 4321432143214321))
(def compra-exemplo2 (Compra. nil "2022-01-01" 300 "Hamburguer" "Alimentação" 4321432143214321))

(def lista-de-compras (lista-compras))

;FUNÇÃO INSERIR COMPRA
(defn insere-compra [lista-de-compras compra]
  (conj lista-de-compras (assoc compra :id (count lista-de-compras))))

;(pprint (insere-compra lista-de-compras compra-exemplo))

;FUNÇÃO LISTAR COMPRA NO ÁTOMO
(defn lista-compras! [repositorio-de-compras]
  (pprint @repositorio-de-compras))

;(lista-compras! repositorio-de-compras)

;STOP

;FUNÇÃO INSERIR COMPRA NO ÁTOMO

(defn insere-compra! [lista-de-compras compra]
  (swap! lista-de-compras conj (insere-compra @lista-de-compras compra))
  ;(swap! lista-de-compras insere-compra compra)
  )

;(lista-compras! repositorio-de-compras)
;(insere-compra! repositorio-de-compras compra-exemplo)
;(insere-compra! repositorio-de-compras compra-exemplo2)
;(lista-compras! repositorio-de-compras )

;FUNÇÃO PESQUISAR COMPRA POR ID
(defn pesquisa-compra-por-id [id compras]
  (let [compra-id (-> :id
                      (group-by compras)
                      (get id))]
    (if compra-id
      (map->Compra compra-id))))

(defn pesquisa-compra-por-id2 [id compras]
  (map->Compra (filter (fn [compra]
                         (= id (:id compra))) compras)))

;(pprint (pesquisa-compra-por-id 19 (insere-compra lista-de-compras compra-exemplo)))
;(pprint (pesquisa-compra-por-id2 19 (insere-compra lista-de-compras compra)))

;FUNÇÃO PESQUISAR COMPRA POR ATOMO
(defn pesquisa-compra-por-id! [id repositorio-de-compras]
  (pesquisa-compra-por-id id @repositorio-de-compras)
  )

;(insere-compra! repositorio-de-compras compra)
;(lista-compras! repositorio-de-compras)
;(pprint (pesquisa-compra-por-id! 0 repositorio-de-compras))



;FUNÇÃO EXCLUIR COMPRA
(defn exclui-compra [id lista-compras]
  ;(pprint lista-compras)
  (remove (pesquisa-compra-por-id2 id lista-compras) lista-compras)
  )

;(pprint (insere-compra lista-de-compras compra-exemplo))
;(pprint (exclui-compra 19 (insere-compra lista-de-compras compra-exemplo)))
;(pprint (pesquisa-compra-por-id 19 (insere-compra lista-de-compras compra-exemplo)))

(defn exclui-compra! [id lista-compras]
  (swap! lista-compras exclui-compra id))
