(ns semana2.compras
  (:use clojure.pprint)
  (:require [java-time :as t]))

(def repositorio-de-compras (atom []))

(defrecord Compra [id, data, valor, estabelecimento, categoria, cartao])

(defn proximo-id [entidades]
  (if-not (empty? entidades)
    (+ 1 (apply max (map :id entidades)))
    0))

(defn insere-compra
  [compras nova-compra]
  (let [id (proximo-id compras)
        nova-compra-id (update nova-compra :id (constantly id))]
    (assoc compras id nova-compra-id)))

(defn valida-compra
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
  (if (valida-compra nova-compra)
    (swap! compras insere-compra nova-compra)))

(insere-compra!
  (->Compra nil, (t/local-date "2022-05-07"), (bigdec 129.90), "Outback", "Alimentação", 1234123412341234)
  repositorio-de-compras)

(insere-compra!
  (->Compra nil, (t/local-date "2022-01-02"), (bigdec 260.00), "Dentista", "Saúde", 1234123412341234)
  repositorio-de-compras)

(insere-compra!
  (->Compra nil, (t/local-date "2022-02-01"), (bigdec 20.00), "Cinema", "Lazer", 1234123412341234)
  repositorio-de-compras)


(defn lista-compras!
  [compras]
  (@compras))

(println "Lista compras:" lista-compras! repositorio-de-compras)


(defn pesquisa-compra-por-id
  [id vetor-de-compras]
  (if-not (empty? vetor-de-compras)
    (get vetor-de-compras id)
    nil))

(defn pesquisa-compra-por-id!
  [id compras]
  (pesquisa-compra-por-id id (deref compras)))

(println "Pesquisa por id 0:" (pesquisa-compra-por-id! 0 repositorio-de-compras))
;(println "Pesquisa por id 100:" (pesquisa-compra-por-id! 100 repositorio-de-compras))


(defn exclui-compra
  [vetor-de-compras id]
  (if-not (empty? vetor-de-compras)
    (remove #(= id (:id %)) vetor-de-compras)
    vetor-de-compras))

(defn exclui-compra!
  [id compras]
  (swap! compras exclui-compra id))

(println "Exclui compra de id 1:" (exclui-compra! 1 repositorio-de-compras))





