(ns yes-she-codes.semana1
  (:require [yes-she-codes.db :as l.db]
            [java-time :as t]))

(defn novo-cliente
  [[nome cpf email]]
  (let [client (conj {:nome nome :cpf cpf :email email})]
    client))

(defn novo-cartao
  [[numero cvv limite validate cpf]]
  (let [formatValidate (t/format "MM/yyyy" (t/year-month validate))
        client (conj {:numero numero, :cvv cvv, :limite limite :validate formatValidate :cpf cpf})]
      client))

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

(defn filtra-data
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
  (let [client-cartao (filtra-data :cartao cartao compras)
        data (filtra-compras-data :data mes client-cartao)]
      (total-gasto data)))


(defn buscar-por-estabelecimento
  [estabelecimento compras]
  (filtra-data :estabelecimento estabelecimento compras))

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

(defn agregate-cartao [cpf]
  {:cartao (into {} (filtra-data :cpf cpf (lista-cartoes)))})

(defn agregate-compras [cartao]
  (vec (filtra-data :cartao cartao (lista-compras))))

(defn agregate-client-cpf [cpf]
  (let [client (into {} (filtra-data :cpf cpf (lista-clientes)))
        cartao (agregate-cartao cpf)
        compras  (agregate-compras (:numero (:cartao cartao)))
        conta (assoc-in cartao [:cartao :compras] compras)]
    (conj client conta)))

(defn agregate-clients [clients]
  (map (fn [client] (agregate-client-cpf (:cpf client))) clients))