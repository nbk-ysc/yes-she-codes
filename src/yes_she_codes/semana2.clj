(ns yes-she-codes.semana2
  (:require [yes-she-codes.semana1 :as y.s1]
            [yes-she-codes.db :as y.db]
            [java-time :as t]))

(def repositorio-de-compras (atom []))

(defrecord Compra [^Long id ^String data ^BigDecimal valor ^String estabelecimento ^String categoria ^Long cartao])

(defn proximo-id [entidades]
  (if-not (empty? entidades)
    (+ 1 (apply max (map :id entidades))) 1))

(defn insere-compra [compras record]
  (let [id (proximo-id compras)]
  (conj compras (assoc record :id id))))

(defn insere-compra! [compra]
    (swap! repositorio-de-compras insere-compra compra))

(defn carrega-compra
  [compras]
  (->> compras
       (map (fn [[data valor estabelecimento categoria cartao]]
              (let [formatData (t/format "dd/MM/yyyy" (t/local-date data))]
              (Compra. nil formatData valor estabelecimento categoria cartao))))
       (map insere-compra!)))

(defn parametros-compras []
  (let [compras (y.db/param-compras)]
    (flatten (carrega-compra compras))))

(defn lista-compra []
  (parametros-compras))

(defn lista-compra! [repositorio-de-compras]
  (deref repositorio-de-compras))

(defn pesquisa-compra-por-id [id compras]
  (y.s1/filtra-data :id id compras))

(defn pesquisa-compra-por-id! [id compras]
  (y.s1/filtra-data :id id compras))

(defn exclui-compra [id compras]
  (remove #(= (:id %) id) compras))

(defn exclui-compra! [id compras]
  (swap! compras (remove #(= (:id %) id) compras) compras))

(println "Parametros Compras" (parametros-compras))
;(println (Compra. nil "12/12/1221" 121.0 "Extra" "Alimentacao" "218928192819"))

(println "Repositorio de compras" @repositorio-de-compras)
(println "lista compra" (lista-compra))
(println "lista compra!" (lista-compra! repositorio-de-compras))
(println "lista id" (pesquisa-compra-por-id 1 (parametros-compras)))
(println "Atom" (pesquisa-compra-por-id! 1 (deref repositorio-de-compras)))
;(println "Exclui" (exclui-compra 1 (parametros-compras)))
;(println "Exclui!" (exclui-compra! 1 repositorio-de-compras))
