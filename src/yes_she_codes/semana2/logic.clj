(ns yes-she-codes.semana2.logic)

(defn proximo-id [entidades]
  (if-not (empty? entidades)
    (inc (apply max (map :id entidades)))
    1))

(defn insere-compra
  "Atribui um id à compra e a armazena no vetor compras."
  [compras compra]
  (let [id (proximo-id compras)
        compra-com-id (assoc compra :id id)]
    (conj compras compra-com-id)))

(defn insere-compra!
  "Inclui uma nova compra no átomo compras."
  [compras compra]
  (swap! compras insere-compra compra))

(defn lista-compras!
  "Lista as compras a partir de um átomo."
  [compras]
  (println @compras))

(defn pesquisa-compra-por-id
  "Retorna a compra com o identificador 'id' no vetor compras."
  [compras id]
  (->> compras
       (filter #(= id (:id %)))
       first))

(defn pesquisa-compra-por-id!
  "Pesquisa por uma compra em um átomo a partir do seu id."
  [compras id]
  (pesquisa-compra-por-id @compras id))

(defn exclui-compra
  "Exclui uma compra com o identificador 'id' do vetor compras."
  [compras id]
  (remove #(= id (:id %)) compras))

(defn exclui-compra!
  "Exclui uma compra com o identificador 'id' do átomo compras."
  [compras id]
  (swap! compras exclui-compra id))