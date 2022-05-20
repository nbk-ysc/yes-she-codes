(ns yes-she-codes.semana2
  (:require [yes-she-codes.semana1 :as y.s1]
            [yes-she-codes.db :as y.db]
            [java-time :as t]))

(def repositorio-de-compras (atom []))

(defrecord Compra [^Long id ^String data ^BigDecimal valor ^String estabelecimento ^String categoria ^Long Cartao])

(defn nova-compra
  [[data valor estabelecimento categoria cartao] index]
  (let [formatData (t/format "dd/MM/yyyy" (t/local-date data))]
    (Compra. index formatData valor estabelecimento categoria cartao)))

(defn parametros-compras []
  (let [compras (y.db/param-compras)]
    (map-indexed (fn [index compra] (nova-compra compra (inc index))) compras)))

(defn proximo-id [entidades]
  (if-not (empty? entidades)
    (+ 1 (apply max (map :id entidades))) 1))

(defn insere-compra [record compras]
  (let [id (proximo-id compras)]
  (conj compras (assoc record :id id))))

;entender porque ao enviar um objeto vazio nao fica o namespace
(defn insere-compra! [record compras-atom]
  (let [compras (insere-compra record compras-atom)]
  (swap! repositorio-de-compras conj compras)))

(defn lista-compra! [repositorio-de-compras]
  (deref repositorio-de-compras))

(defn pesquisa-compra-por-id [id compras]
  (y.s1/filtra-data :id id compras))

(defn pesquisa-compra-por-id! [id compras]
  (y.s1/filtra-data :id id compras))

(defn exclui-compra [id compras]
  (remove #(= (:id %) id) compras))

;entender o motivo de nao funcionar com o filtro-daata
;porque foi transformado em uma lista
(defn exclui-compra! [id compras]
  (swap! compras (remove #(= (:id %) id) compras)))

(println (parametros-compras))
(println (Compra. nil "12/12/1221" 121.0 "Extra" "Alimentacao" "218928192819"))
(println "Inserindo nova compra"
         (insere-compra (Compra. nil "12/12/1221" 121.0 "Extra" "Alimentacao" "218928192819") (parametros-compras)))

(println (insere-compra! (Compra. nil "12/12/1221" 121.0 "Extra" "Alimentacao" "218928192819") (parametros-compras)))
(println (insere-compra! (Compra. nil "12/12/1221" 121.0 "Extra" "Alimentacao" "218928192819") (parametros-compras)))

(println @repositorio-de-compras)
(println "lista compra" (lista-compra! repositorio-de-compras))
(println (pesquisa-compra-por-id 1 (parametros-compras)))
(println "Atom" (pesquisa-compra-por-id! 1 @repositorio-de-compras))
(println "Exclui" (exclui-compra 1 (parametros-compras)))
;(println "Exclui" (exclui-compra! 1 repositorio-de-compras))
;(println (insere-compra! (Compra. nil "12/12/1221" 121.0 "Extra" "Alimentacao" "218928192819") {}))
;(println @repositorio-de-compras)