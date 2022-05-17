(ns yes-she-codes.core
  (:require [yes-she-codes.db :as l.db])
  (:require [java-time :as t]))

(defn novo-cliente
  [[nome cpf email]]
  (let [client (conj {:nome nome :cpf cpf :email email})]
    client))

(defn novo-cartao
  [[numero cvv limite validate cpf]]
  (let [formatValidate (t/format "MM/yyyy" (t/year-month validate))]
    (let [client (conj {:numero numero, :cvv cvv, :limite limite :validate formatValidate :cpf cpf})]
      client)))

(defn nova-compra
  [[data valor estabelecimento categoria cartao]]
  (let [formatData (t/format "dd/MM/yyyy" (t/local-date data))]
    (conj {:data formatData, :valor valor, :estabelecimento estabelecimento :categoria categoria :cartao cartao})))


(defn parametros-clients []
  (let [clients (l.db/param-clientes)]
    (vec (map novo-cliente clients))))


(defn parametros-cartoes []
  (let [cartoes (l.db/param-cartoes)]
    (vec (map novo-cartao cartoes))))

(defn parametros-compras []
  (let [compras (l.db/param-compras)]
    (vec (map nova-compra compras))))

(defn lista-clientes []
  (parametros-clients))

(defn lista-cartoes []
  (parametros-cartoes))

(defn lista-compras []
  (parametros-compras))


(defn total-gasto [compras]
  (reduce + (map #(:valor %) compras)))

(defn filtra-compras
  [field value data]
  (filter #(= value (field %)) data))

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
  (let [client-cartao (filtra-compras :cartao cartao compras)]
    (let [data (filtra-compras-data :data mes client-cartao)]
      (total-gasto data))))


(defn buscar-por-estabelecimento
  [estabelecimento compras]
  (filtra-compras :estabelecimento estabelecimento compras))

(defn filtrar-intervalo-compras
  [minimo maximo compras]
  (filter #(and (> (compare (% :valor) minimo) 0) (< (compare (% :valor) maximo) 0)) compras))


(defn total-compras-por-categoria
  [[categoria values]]
  {:categoria categoria
   :R$        (format "%.2f" (total-gasto values))})


(defn agrupar-por-categoria [compras]
  (map total-compras-por-categoria
       (group-by :categoria compras)))







