(ns yes_she_codes.semana2.logic)

(defn proximo-id
  [entidades]
  (if-not (empty? entidades)
    (+ 1 (apply max (map :id entidades)))
    1))

(defn insere-compra
  [lista-de-compras compra]
  (let [id (proximo-id lista-de-compras)
        compra-com-id (assoc compra :id id)]
    (conj lista-de-compras compra-com-id)))

(defn insere-compra!
  [lista-de-compras compra]
  (swap! lista-de-compras insere-compra compra))