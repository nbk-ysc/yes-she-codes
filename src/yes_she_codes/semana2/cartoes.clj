(ns yes-she-codes.semana2.cartoes
  (:require [yes-she-codes.semana2.logic :as y.s2.logic])
  (:use [clojure pprint]))





(println "\n\n ---------- criando um atom ----------")
(def repositorio-de-cartoes (atom []))

(println repositorio-de-cartoes)   ; #object[clojure.lang.Atom 0x17161f91 {:status :ready, :val []}]
(pprint repositorio-de-cartoes)    ; #<Atom@17161f91: []>
(pprint @repositorio-de-cartoes)   ; [] -> acessando o valor dentro do atomo



;{:numero "1234 1234 1234 1234" :cvv 111 :validade "2023-01" :limite 1.000 :cliente "000.111.222-33"}


(println "\n\n ---------- criando um record ----------")

(defrecord Cartoes [^Long ID, ^Long numero, ^Long cvv, ^String validade, ^BigDecimal limite, ^String cliente])

(pprint (Cartoes. 1 1234123412341234 555 "2023-01" 1.000 "000.111.222-33"))

; printando cartoes sem o id
(pprint (map->Cartoes {:numero 1234123412341234 :cvv 111 :validade "2023-01" :limite 1.000 :cliente "000.111.222-33"}))





(println "\n\n ---------- criando a funcao insere-cartao ----------")

(defn insere-cartao
  [lista-cartoes novo-cartao]

  (let [id (y.s2.logic/proximo-id lista-cartoes)
        id-cartao-a-inserir (assoc novo-cartao :ID id)]
    (conj lista-cartoes id-cartao-a-inserir))
  )

(pprint (insere-cartao {} {:numero 1234123412341234 :cvv 111 :validade "2023-01" :limite 1.000 :cliente "000.111.222-33"}))





(println "\n\n ---------- criando a funcao insere-cartao! ----------")

(defn insere-cartao!
  [lista-cartoes novo-cartao]
  (swap! lista-cartoes insere-cartao novo-cartao)
  )

(let [novo-cartao (map->Cartoes  {:numero 1234123412341234 :cvv 222 :validade "2025-01" :limite 2.000 :cliente "777.111.222-33"})]
  (pprint (insere-cartao! repositorio-de-cartoes novo-cartao)))

(let [novo-cartao (map->Cartoes  {:numero 1234123412341234 :cvv 333 :validade "2027-01" :limite 3.000 :cliente "888.111.222-33"})]
  (pprint (insere-cartao! repositorio-de-cartoes novo-cartao)))





(println "\n\n ---------- repositorio-de-cartoes ----------")
(pprint @repositorio-de-cartoes)





(println "\n\n ---------- criando a funcao lista-cartoes! ----------")

(defn lista-cartoes!
  [repositorio-de-cartoes]
  (println @repositorio-de-cartoes))

(lista-cartoes! repositorio-de-cartoes)





(println "\n\n ---------- pesquisar-cartao-por-id ----------")

(defn pesquisa-cartao-por-id
  [repositorio-de-cartoes id ]
  (first (filter #(= id (get % :ID)) repositorio-de-cartoes)))

(pprint (pesquisa-cartao-por-id @repositorio-de-cartoes 5))
(pprint (pesquisa-cartao-por-id @repositorio-de-cartoes 1))





(println "\n\n ---------- pesquisar-cartao-por-id! ----------")
(defn pesquisar-cartao-por-id!
  [repositorio-de-cartoes id]
  (pesquisa-cartao-por-id @repositorio-de-cartoes id))

(pprint (pesquisar-cartao-por-id! repositorio-de-cartoes 5)) ; nil
(pprint (pesquisar-cartao-por-id! repositorio-de-cartoes 2))





(println "\n\n ---------- exclui-cartao ----------")

(defn exclui-cartao
  [repositorio-de-cartoes id]

  ; codigo para remover ‘item’ do vetor
  (let [cartao-a-excluir (pesquisa-cartao-por-id repositorio-de-cartoes id)
        nova-lista (remove #{cartao-a-excluir} repositorio-de-cartoes)]

    ; vec cria novo vector
    (vec nova-lista)
    ))

(pprint (exclui-cartao [{:ID 1 :numero 1234123412341234 :cvv 222 :validade "2025-01" :limite 2.000 :cliente "777.111.222-33"}
                        {:ID 2 :numero 1234123412341234 :cvv 333 :validade "2027-01" :limite 3.000 :cliente "888.111.222-33"}] 1))





(println "\n\n ---------- exclui-cartao! ----------")

(defn exclui-cartao!
  [repositorio-de-cartoes id]
  (swap! repositorio-de-cartoes exclui-cartao id)
  )


(pprint (exclui-cartao! repositorio-de-cartoes 1))
;(pprint @repositorio-de-compras)
