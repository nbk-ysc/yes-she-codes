(ns semana2.nubank
  (:use clojure.pprint))

(def repositorio-de-compras (atom []))
(def repositorio-de-clientes (atom []))
(def repositorio-de-cartoes (atom []))

(defrecord Compra [id, data, valor, estabelecimento, categoria, cartao])
(defrecord Clientes [nome, cpf, email])
(defrecord Cartoes [numero, cvv, validade, limite, cliente])

(defn proximo-id [entidades]
  (if-not (empty? entidades)
    (+ 1 (apply max (map :id entidades)))
    0))

(defn insere-compra
  [compras nova-compra]
  (let [id (proximo-id compras)
        nova-compra-id (update nova-compra :id (constantly id))]
    (assoc compras id nova-compra-id)))

(defn insere-compra!
  [nova-compra compras]
  (swap! compras insere-compra nova-compra))

(insere-compra!
  (->Compra nil, "2022-05-07", 129.90, "Outback", "Alimentação", 1234123412341234)
  repositorio-de-compras)

(insere-compra!
  (->Compra nil, "2022-01-02", 260.00, "Dentista", "Saúde", 1234123412341234)
  repositorio-de-compras)

(insere-compra!
  (->Compra nil, "2022-02-01", 20.00, "Cinema", "Lazer", 1234123412341234)
  repositorio-de-compras)


(defn lista-compras!
  [compras]
  (println "Lista compras:" (deref compras)))

(lista-compras! repositorio-de-compras)


(defn pesquisa-compra-por-id
  [id vetor-de-compras]
  (if-not (empty? vetor-de-compras)
    (get vetor-de-compras id)
    nil))

(defn pesquisa-compra-por-id!
  [id compras]
  (pesquisa-compra-por-id id (deref compras)))

(println "Pesquisa por id 0:" (pesquisa-compra-por-id! 0 repositorio-de-compras))
(println "Pesquisa por id 100:" (pesquisa-compra-por-id! 100 repositorio-de-compras))


(defn exclui-compra
  [vetor-de-compras id]
  (if-not (empty? vetor-de-compras)
    (remove #(= id (:id %)) vetor-de-compras)
    vetor-de-compras))

(defn exclui-compra!
  [id compras]
  (swap! compras exclui-compra id))

(println "Exclui compra de id 1:" (exclui-compra! 1 repositorio-de-compras))





