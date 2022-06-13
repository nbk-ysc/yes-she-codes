(ns yes-she-codes.persistencia.compra
  (:use [clojure.pprint])
  (:require [yes-she-codes.logic :as logic]
            [schema.core :as s]
            [yes-she-codes.dominio.compra :as dominio.compra]
            [yes-she-codes.db.compra :as db.compra]
            [yes-she-codes.db.atom :as db.atom]
            [java-time :as t]))


;SEMANA 1/SEMANA 3
(s/defn nova-compra :- dominio.compra/CompraSchema
  [[data, valor, estabelecimento, categoria, cartao]]
  (let [format-data (logic/format-data-compra data)
        format-long (logic/format-numero cartao)]
    (dominio.compra/->Compra nil format-data valor estabelecimento categoria format-long)))

(defn parametros-compras []
  (let [compras (db.compra/param-compras)]
    (vec (map nova-compra compras))))

(defn lista-compras []
  (parametros-compras))

(defn total-gasto [compras]
  (reduce + (map #(:valor %) compras)))

(defn total-por-cartao [[cartao values]]
  {:cartao cartao
   :R$ (total-gasto values)})

(defn total-compras-por-cartao [compras]
  (map total-por-cartao (group-by :cartao compras)))


(defn filtra-compras-data
  [field value data]
  (let [pattern (re-pattern (str "[0-9]{2}/" value "/[0-9]{4}"))
        match-month #(re-matches pattern (field %))]
    (filter #(= (match-month %) (field %)) data)))

(defn buscar-por-mes
  [mes compras]
  (filtra-compras-data :data mes compras))


(defn total-gasto-mes
  [mes cartao compras]
  (let [client-cartao (logic/filtra-data :cartao cartao compras)
        data (buscar-por-mes mes client-cartao)]
    (total-gasto data)))


(defn total-compras-por-categoria
  [[categoria values]]
  {:categoria categoria
   :R$        (format "%.2f" (total-gasto values))})

(defn agrupar-por-categoria [compras]
  (map total-compras-por-categoria
       (group-by :categoria compras)))

(defn filtrar-intervalo-compras
  [minimo maximo compras]
  (filter #(and (> (compare (% :valor) minimo) 0) (< (compare (% :valor) maximo) 0)) compras))

(defn agregate-compras [cartao]
  (vec (logic/filtra-data :cartao cartao (lista-compras))))


;SEMANA 2
(defrecord Compra [^Long id ^String data ^BigDecimal valor ^String estabelecimento ^String categoria ^Long cartao])

(defn proximo-id [entidades]
  (if-not (empty? entidades)
    (+ 1 (apply max (map :id entidades))) 1))

(defn insere-compra [compras record]
  (let [id (proximo-id compras)]
    (conj compras (assoc record :id id))))

(defn insere-compra! [compra]
  (swap! db.atom/repositorio-de-compras insere-compra compra))

(defn carrega-compra []
  (->> (db.compra/param-compras)
       (map (fn [[data valor estabelecimento categoria cartao]]
              (let [formatData (t/format "dd/MM/yyyy" (t/local-date data))]
                (Compra. nil formatData valor estabelecimento categoria cartao))))
       (map insere-compra!)))

(defn lista-compra! []
  (deref db.atom/repositorio-de-compras))

(defn pesquisa-compra-por-id! [id compras]
  (logic/filtra-data :id id compras))

(defn exclui-entidade [entidades id]
  (remove #(= id (:id %)) entidades))

(defn exclui-compra! [id]
  (swap! db.atom/repositorio-de-compras exclui-entidade id))

(defn id->index [id]
  (->> @db.atom/repositorio-de-compras
       (map-indexed (fn [i v] [i v]))
       (filter (fn [[_ v]] (= (:id v) id)))
       ffirst))

(defn update-compra [id field value]
  (swap! db.atom/repositorio-de-compras assoc-in [(id->index id) field] value))

(defn limpa-atom []
  (reset! db.atom/repositorio-de-compras []))
