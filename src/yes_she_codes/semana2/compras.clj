(ns yes-she-codes.semana2.compras
  (:use [clojure pprint])
  (:require [yes-she-codes.semana2.logic :as y2.logic]))

(def repositorio-de-compras (atom []))

(defrecord Compra [id data valor estabelecimento categoria cartao])

(def compra1 (map->Compra {:data "2021-01-01"
                           :valor 50.00M
                           :estabelecimento "Alura"
                           :categoria "Educação"
                           :cartao 1234123412341234}))

(def compra2 (map->Compra {:data "02/02/2022"
                           :valor 100.00M
                           :estabelecimento "Lanches"
                           :categoria "Alimentação"
                           :cartao 1234123412341234}))

(def lista-vetor [{:data "01/01/2021"
                   :id 1
                   :valor 50.00M
                   :estabelecimento "Alura"
                   :categoria "Educação"
                   :cartao 1234123412341234}
                  {:data "02/02/2022"
                   :id 2
                   :valor 100.00M
                   :estabelecimento "Lanches"
                   :categoria "Alimentação"
                   :cartao 1234123412341234}
                  ])

(defn insere-compra
  [lista-de-compras compra ]
  (conj lista-de-compras (assoc compra :id (y2.logic/proximo-id lista-de-compras))))

(defn insere-compra!
  [compra lista-de-compras]
  (if (y2.logic/valida-compra compra)
    (swap! lista-de-compras insere-compra compra)
    (throw (ex-info "Verifique os dados da compra" {:favor-conferir compra}))))

(defn lista-compras!
  [lista-de-compras]
  (pprint @lista-de-compras))

(defn pesquisa-compra-por-id
  [lista-de-compras id]
  (let [compra (filter #(= (:id %) id) lista-de-compras)]
    (if-not (= compra ())
      (get (vec compra) 0)
      nil)))

(defn pesquisa-compra-por-id!
  [id lista]
  (swap! lista pesquisa-compra-por-id id))

(defn exclui-compra
  [lista-de-compras id]
  (let [compra-a-remover (pesquisa-compra-por-id lista-de-compras id)
        nova-lista (remove #{compra-a-remover} lista-de-compras)]
    (vec nova-lista)
    ))

(defn exclui-compra!
  [id lista]
  (swap! lista exclui-compra id))
