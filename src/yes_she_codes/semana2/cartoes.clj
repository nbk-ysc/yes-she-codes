(ns yes-she-codes.semana2.cartoes
  (:use [clojure pprint])
  (:require [yes-she-codes.semana2.logic :as y2.logic]))

(def repositorio-de-cartoes (atom []))

(defrecord Cartao [id numero cvv validade limite cliente])

(def cartao1 (map->Cartao {:numero 1234123412341234
                           :cvv 111
                           :validade "2023-01"
                           :limite 1000
                           :cliente "000.111.222-33"}))

(def cartao2 (map->Cartao {:numero 4321432143214321
                           :cvv 222
                           :validade "2024-02"
                           :limite 2000
                           :cliente "333.444.555-66"}))

(def lista-vetor [{ :id 1
                   :numero 1234123412341234
                   :cvv 111
                   :validade "2023-01"
                   :limite 1000
                   :cliente "000.111.222-33"}
                  { :id 2
                   :numero 4321432143214321
                   :cvv 222
                   :validade "2024-02"
                   :limite 2000
                   :cliente "333.444.555-66"}
                  ])

(defn insere-cartao
  [lista-de-cartoes cartao ]
  (conj lista-de-cartoes (assoc cartao :id (y2.logic/proximo-id lista-de-cartoes))))

(defn insere-cartao!
  [cartao lista-de-cartoes]
    (swap! lista-de-cartoes insere-cartao cartao))

(defn lista-cartoes!
  [lista-de-cartoes]
  (pprint @lista-de-cartoes))

(defn pesquisa-cartao-por-id
  [lista-de-cartoes id]
  (let [cartao (filter #(= (:id %) id) lista-de-cartoes)]
    (if-not (= cartao ())
      (get (vec cartao) 0)
      nil)))

(defn pesquisa-cartao-por-id!
  [id lista]
  (swap! lista pesquisa-cartao-por-id id))

(defn exclui-cartao
  [lista-de-cartoes id]
  (let [cartao-a-remover (pesquisa-cartao-por-id lista-de-cartoes id)
        nova-lista (remove #{cartao-a-remover} lista-de-cartoes)]
    (vec nova-lista)))

(defn exclui-cartao!
  [id lista]
  (swap! lista exclui-cartao id))




