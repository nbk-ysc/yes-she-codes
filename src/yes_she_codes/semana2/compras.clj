(ns yes-she-codes.semana2.compras
  (:require [yes-she-codes.semana2.logic :as y.s2.logic])
  (:use [clojure pprint]))





(println "\n\n ---------- criando um atom ----------")
(def repositorio-de-compras (atom []))

(println repositorio-de-compras)   ; #object[clojure.lang.Atom 0x17161f91 {:status :ready, :val []}]
(pprint repositorio-de-compras)    ; #<Atom@17161f91: []>
(pprint @repositorio-de-compras)   ; [] -> acessando o valor dentro do atomo



(println "\n\n ---------- criando um record ----------")

(defrecord Compras [^Long ID, ^String Data, ^BigDecimal Valor, ^String Estabelecimento, ^String Categoria, ^Long Cartao])

(pprint (Compras. 2 "18/05/2022" 10 "Araujo" "Saude" 1111222233334444))

; printando Compras sem o id
(pprint (map->Compras  {:Data "18/05/2022" :Valor 10 :Estabelecimento "Araujo" :Categoria "Saude" :Cartao 1111222233334444}))



(println "\n\n ---------- criando a funcao insere-compra ----------")

;(defn insere-compra
;  [lista-compras nova-compra]
;
;  (let [id (inc (count lista-compras))
;        id-compra-a-inserir (assoc nova-compra :ID id)]
;    (conj lista-compras id-compra-a-inserir))
;  )


(defn insere-compra
  [lista-compras nova-compra]

  (let [id (y.s2.logic/proximo-id lista-compras)
        id-compra-a-inserir (assoc nova-compra :ID id)]
    (conj lista-compras id-compra-a-inserir))
  )

(pprint (insere-compra {} {:Data "18/05/2022" :Valor 10 :Estabelecimento "Araujo" :Categoria "Saude" :Cartao 1111222233334444}))



(println "\n\n ---------- criando a funcao insere-compra! ----------")

(defn insere-compra!
  [lista-compras nova-compra]
  (swap! lista-compras insere-compra nova-compra)
  )

(let [nova-compra (map->Compras  {:Data "18/05/2022" :Valor 10 :Estabelecimento "Araujo" :Categoria "Saude" :Cartao 1111222233334444})]
  (pprint (insere-compra! repositorio-de-compras nova-compra)))

(let [nova-compra (map->Compras  {:Data "19/05/2022" :Valor 20 :Estabelecimento "Nucleo Artistico" :Categoria "Lazer" :Cartao 4444333322221111})]
  (pprint (insere-compra! repositorio-de-compras nova-compra)))



(println "\n\n ---------- repositorio-de-compras ----------")
(pprint @repositorio-de-compras)



(println "\n\n ---------- criando a funcao lista-compras! ----------")

(defn lista-compras!
  [repositorio-de-compras]
  (println @repositorio-de-compras))

(lista-compras! repositorio-de-compras)



(println "\n\n ---------- pesquisar-compra-por-id ----------")

(defn pesquisa-compra-por-id
  [repositorio-de-compras id ]
  (first (filter #(= id (get % :ID)) repositorio-de-compras)))

(pprint (pesquisa-compra-por-id @repositorio-de-compras 5))
(pprint (pesquisa-compra-por-id @repositorio-de-compras 1))



(println "\n\n ---------- pesquisar-compra-por-id! ----------")
(defn pesquisa-compra-por-id!
  [repositorio-de-compras id]
  (pesquisa-compra-por-id @repositorio-de-compras id))

(pprint (pesquisa-compra-por-id! repositorio-de-compras 5))
(pprint (pesquisa-compra-por-id! repositorio-de-compras 2))




(println "\n\n ---------- exclui-compra ----------")

(defn exclui-compra
  [repositorio-de-compras id]

  ; codigo para remover ‘item’ do vetor
  (let [compra-a-excluir (pesquisa-compra-por-id repositorio-de-compras id)
        nova-lista (remove #{compra-a-excluir} repositorio-de-compras)]

    ; vec cria novo vector
    (vec nova-lista)
  ))

(pprint (exclui-compra [{:ID 1 :Data "18/05/2022" :Valor 10 :Estabelecimento "Araujo" :Categoria "Saude" :Cartao 1111222233334444}
                        {:ID 2 :Data "19/05/2022" :Valor 10 :Estabelecimento "Nucleo Artistico" :Categoria "Lazer" :Cartao 1111222233334444}] 1))



(println "\n\n ---------- exclui-compra! ----------")

(defn exclui-compra!
  [repositorio-de-compras id]
  (swap! repositorio-de-compras exclui-compra id)
  )


(pprint (exclui-compra! repositorio-de-compras 1))
;(pprint @repositorio-de-compras)
