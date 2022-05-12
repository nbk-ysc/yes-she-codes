(ns yes-she-codes.core
  (:require [yes-she-codes.db :as l.db]))

(def conta-clients {})

(def cartoes-clients {})

(def compras-clients {})

(defn novo-cliente
  [[nome cpf email]]
  (let [client (conj {:nome nome :cpf cpf :email email})]
    client))

(defn novo-cartao
  [[numero cvv validate limite cpf]]
  (let [client (conj {:numero numero, :cvv cvv, :validate validate :limite limite :cpf cpf})]
    client))

(defn nova-compra
  [[data valor estabelecimento categoria cartao]]
  (let [client (conj {:data data, :valor valor, :estabelecimento estabelecimento :categoria categoria :cartao cartao})]
    client))


(defn parametros-clients []
  (let [clients (l.db/param-clientes)]
    (assoc-in conta-clients [:clients] (vec (map novo-cliente clients)))))


(defn parametros-cartoes []
  (let [cartoes (l.db/param-cartoes)]
    (assoc-in cartoes-clients [:cartoes] (vec (map novo-cartao cartoes)))))

(defn parametros-compras []
  (let [compras (l.db/param-compras)]
    (assoc-in compras-clients [:compras] (vec (map nova-compra compras)))))

(defn lista-clientes []
  (:clients (parametros-clients)))

(defn lista-cartoes []
  (:cartoes (parametros-cartoes)))

(defn lista-compras []
  (:compras (parametros-compras)))


(defn total-gasto [compras]
  (reduce + (map #(:valor %) compras)))

(defn filtra-compras
  [field value data]
  (filter #(= value (field %)) data))

(defn filtra-compras-data
  [field value data]
  (filter #(= (re-matches (re-pattern (str "[0-9]{4}-" value "-[0-9]{2}")) (field %)) (field %)) data))


(defn buscar-por-mes
  [mes compras]
  (filtra-compras-data :data mes compras))


(defn total-gasto-mes
  [mes cartao compras]
  (let [client-cartao (filtra-compras :cartao cartao compras)]
    (let [data (filtra-compras-data :data mes client-cartao)]
      (total-gasto data))))


(defn buscar-por-estabelecimento
  [estabelecimento compras]
  (filtra-compras :estabelecimento estabelecimento compras))

(defn filtrar-intervalo-compras
  [minimo maximo compras]
  (filter #(and (> (compare (% :data) minimo) 0) (< (compare (% :data) maximo) 0)) compras))


(defn total-compras-por-categoria
  [[categoria values]]
  {:categoria categoria
   :R$ (total-gasto values)})


(defn agrupar-por-categoria [compras]
  (map total-compras-por-categoria
     (group-by :categoria compras)))



(println (lista-compras))

(println (total-gasto (:compras (parametros-compras))))

(println (buscar-por-mes "01" (:compras (parametros-compras))))

(println "total-gasto-mes" (total-gasto-mes "01" "1234 1234 1234 1234" (:compras (parametros-compras))))

(println (agrupar-por-categoria (:compras (parametros-compras))))

(println (buscar-por-estabelecimento "Alura" (:compras (parametros-compras))))

(println (filtrar-intervalo-compras "2022-01-01" "2022-03-01" (:compras (parametros-compras))))







