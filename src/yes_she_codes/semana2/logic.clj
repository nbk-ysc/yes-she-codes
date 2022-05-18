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

(defn lista-compras!
  [repositorio-de-compras]
  (mapv println @repositorio-de-compras))

(defn compra-possui-id?
  [compra id]
  (= id (:id compra)))

(defn pesquisa-compra-por-id
  [compras id]
  (let [compra (filter #(compra-possui-id? % id) compras)]
    (if (not (empty? compra))
      compra)))

(defn pesquisa-compra-por-id!
  [compras id]
  (println (pesquisa-compra-por-id @compras id)))

(defn exclui-compra
  [compras id]
  (remove #(compra-possui-id? % id) compras))

(defn exclui-compra!
  [compras id]
  (println (swap! compras exclui-compra id)))