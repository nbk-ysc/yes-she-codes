(ns yes-she-codes.semana2.clientes
  (:require [yes-she-codes.semana2.logic :as y.s2.logic])
  (:use [clojure pprint]))





(println "\n\n ---------- criando um atom ----------")
(def repositorio-de-clientes (atom []))

(println repositorio-de-clientes)   ; #object[clojure.lang.Atom 0x17161f91 {:status :ready, :val []}]
(pprint repositorio-de-clientes)    ; #<Atom@17161f91: []>
(pprint @repositorio-de-clientes)   ; [] -> acessando o valor dentro do atomo



;{:nome "Feiticeira Escarlate" :cpf "000.111.222-33" :email "feiticeira.poderosa@vingadoras.com.br"}


(println "\n\n ---------- criando um record ----------")

(defrecord Clientes [^Long ID, ^String nome, ^Long cpf, ^String email])

(pprint (Clientes. 2 "Maria Alice Guimaraes Carneiro" 11531450652 "mguimaraes.carneiro@gmail.comS"))

; printando Compras sem o id
(pprint (map->Clientes  {:nome "Feiticeira Escarlate" :cpf "000.111.222-33" :email "feiticeira.poderosa@vingadoras.com.br"}))





(println "\n\n ---------- criando a funcao insere-cliente ----------")

(defn insere-cliente
  [lista-clientes novo-cliente]

  (let [id (y.s2.logic/proximo-id lista-clientes)
        id-cliente-a-inserir (assoc novo-cliente :ID id)]
    (conj lista-clientes id-cliente-a-inserir))
  )

(pprint (insere-cliente {} {:nome "Feiticeira Escarlate" :cpf "000.111.222-33" :email "feiticeira.poderosa@vingadoras.com.br"}))





(println "\n\n ---------- criando a funcao insere-cliente! ----------")

(defn insere-cliente!
  [lista-clientes novo-cliente]
  (swap! lista-clientes insere-cliente novo-cliente)
  )

(let [novo-cliente (map->Clientes  {:nome "Capitao America" :cpf "770.111.222-33" :email "capitao.america@vingadores.com.br"})]
  (pprint (insere-cliente! repositorio-de-clientes novo-cliente)))

(let [novo-cliente (map->Clientes  {:nome "Homem de Ferro" :cpf "888.111.222-33" :email "capitao.america@vingadores.com.br"})]
  (pprint (insere-cliente! repositorio-de-clientes novo-cliente)))





(println "\n\n ---------- repositorio-de-cliente ----------")
(pprint @repositorio-de-clientes)





(println "\n\n ---------- criando a funcao lista-clientes! ----------")

(defn lista-clientes!
  [repositorio-de-clientes]
  (println @repositorio-de-clientes))

(lista-compras! repositorio-de-clientes)





(println "\n\n ---------- pesquisar-cliente-por-id ----------")

(defn pesquisa-cliente-por-id
  [repositorio-de-clientes id ]
  (first (filter #(= id (get % :ID)) repositorio-de-clientes)))

(pprint (pesquisa-cliente-por-id @repositorio-de-clientes 5))
(pprint (pesquisa-cliente-por-id @repositorio-de-clientes 1))





(println "\n\n ---------- pesquisar-compra-por-id! ----------")
(defn pesquisa-cliente-por-id!
  [repositorio-de-clientes id]
  (pesquisa-cliente-por-id @repositorio-de-clientes id))

(pprint (pesquisa-cliente-por-id! repositorio-de-clientes 5)) ; nil
(pprint (pesquisa-cliente-por-id! repositorio-de-clientes 2))





(println "\n\n ---------- exclui-cliente ----------")

(defn exclui-cliente
  [repositorio-de-clientes id]

  ; codigo para remover ‘item’ do vetor
  (let [cliente-a-excluir (pesquisa-cliente-por-id repositorio-de-clientes id)
        nova-lista (remove #{cliente-a-excluir} repositorio-de-clientes)]

    ; vec cria novo vector
    (vec nova-lista)
    ))

(pprint (exclui-cliente [{:ID 1 :nome "Capitao America" :cpf "770.111.222-33" :email "capitao.america@vingadores.com.br"}
                         {:ID 2 :nome "Homem de Ferro" :cpf "888.111.222-33" :email "capitao.america@vingadores.com.br"}] 1))





(println "\n\n ---------- exclui-cliente! ----------")

(defn exclui-cliente!
  [repositorio-de-clientes id]
  (swap! repositorio-de-clientes exclui-cliente id)
  )


(pprint (exclui-cliente! repositorio-de-clientes 1))
;(pprint @repositorio-de-compras)
