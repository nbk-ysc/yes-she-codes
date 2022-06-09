(ns yes-she-codes.semana2.clientes
  (:use [clojure pprint])
  (:require [yes-she-codes.semana2.logic :as y2.logic]))

(def repositorio-de-clientes (atom []))

(defrecord Cliente [id nome cpf email])

(def cliente1 (map->Cliente {:nome "Feiticeira Escarlate"
                             :cpf "000.111.222-33"
                             :email "feiticeira.poderosa@vingadoras.com.br"}))

(def cliente2 (map->Cliente {:nome "Viúva Negra"
                             :cpf "333.444.555-66"
                             :email "viuva.casca.grossa@vingadoras.com.br"}))

(def lista-vetor [{ :id 1
                   :nome "Feiticeira Escarlate"
                   :cpf "000.111.222-33"
                   :email "feiticeira.poderosa@vingadoras.com.br"}
                  { :id 2
                   :nome "Viúva Negra"
                   :cpf "333.444.555-66"
                   :email "viuva.casca.grossa@vingadoras.com.br"}])

(defn insere-cliente
  [lista-de-clientes cliente ]
  (conj lista-de-clientes (assoc cliente :id (y2.logic/proximo-id lista-de-clientes))))

(defn insere-cliente!
  [cliente lista-de-clientes]
  (swap! lista-de-clientes insere-cliente cliente))

(defn listar-clientes!
  [lista-de-clientes]
  (pprint @lista-de-clientes))

(defn pesquisa-cliente-por-id
  [lista-de-clientes id]
  (let [cliente (filter #(= (:id %) id) lista-de-clientes)]
    (if-not (= cliente ())
      (get (vec cliente) 0)
      nil)))

(defn pesquisa-cliente-por-id!
  [id lista]
  (swap! lista pesquisa-cliente-por-id id))

(defn exclui-cliente
  [lista-de-clientes id]
  (let [cliente-a-remover (pesquisa-cliente-por-id lista-de-clientes id)
        nova-lista (remove #{cliente-a-remover} lista-de-clientes)]
    (vec nova-lista)))

(defn exclui-cliente!
  [id lista]
  (swap! lista exclui-cliente id))


