(ns semana2.compras
  (:use clojure.pprint)
  (:require [java-time :as t]
            [semana1.db :as y.db]))

(def repositorio-de-compras (atom []))

(defrecord Compra [id, data, valor, estabelecimento, categoria, cartao])

(defn proximo-id [entidades]
  (if-not (empty? entidades)
    (+ 1 (apply max (map :id entidades)))
    1))

(defn insere-compra
  [compras nova-compra]
  (let [id (proximo-id compras)
        compra-com-id (assoc nova-compra :id id)]
    (conj compras compra-com-id)))

(defn compra-valida?
  [nova-compra]
  (if (and (> (:valor nova-compra) 0)
           (t/before? (:data nova-compra) (t/local-date))
           (= (class (:valor nova-compra)) BigDecimal)
           (> (count (:estabelecimento nova-compra)) 2)
           (contains? #{"Alimentação", "Automóvel", "Casa", "Educação", "Lazer", "Saúde"} (:categoria nova-compra)))
    true
    (throw (ex-info "Não foi possível inserir a compra" {:compra nova-compra}))))

(defn insere-compra!
  [nova-compra compras]
  (if (compra-valida? nova-compra)
    (swap! compras insere-compra nova-compra)))


(defn carrega-registros! [insere compras repositorio-de-compras]
  (doseq [registro compras]
    (insere registro repositorio-de-compras)))

(defn carrega-compras-no-atomo []
  (carrega-registros! insere-compra! (y.db/lista-compras) repositorio-de-compras))

(carrega-compras-no-atomo)


(defn lista-compras!
  [compras]
  (@compras))

(println "Lista compras:" lista-compras! repositorio-de-compras)


(defn pesquisa-compra-por-id
  [id vetor-de-compras]
  (first (filter #(= id (:id %)) vetor-de-compras)))

(defn pesquisa-compra-por-id!
  [id compras]
  (pesquisa-compra-por-id id (deref compras)))

(println "Pesquisa por id 2:" (pesquisa-compra-por-id! 2 repositorio-de-compras))


(defn exclui-compra
  [vetor-de-compras id]
  (if-not (empty? vetor-de-compras)
    (remove #(= id (:id %)) vetor-de-compras)
    vetor-de-compras))

(defn exclui-compra!
  [id compras]
  (swap! compras exclui-compra id))

(println "Exclui compra de id 1:" (exclui-compra! 1 repositorio-de-compras))
(println "Pesquisa por id 2:" (pesquisa-compra-por-id! 2 repositorio-de-compras))


