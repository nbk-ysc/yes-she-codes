(ns yes-she-codes.week2
  (:use clojure.pprint))


(def repositorio-de-compras (atom []))
(def repositorio-de-clientes (atom []))
(def repositorio-de-cartoes (atom []))


(defrecord Compra [^Long id, ^String data, ^BigDecimal valor,
                   ^String estabelecimento, ^String categoria, ^Long cartao])
(defrecord Cliente [^Long id, ^String nome, ^String cpf, ^String email])
(defrecord Cartao [^Long id, ^Long numero, ^Long cvv, ^String validade,
                   ^BigDecimal limite ^String cliente])


(defn proximo-id [entidades]
  (if-not (empty? entidades)
    (inc (apply max (map :id entidades)))
    1))


(defn insere-no-vetor
  [repositorio-compras record-compra-sem-id]
  (let [id (proximo-id repositorio-compras)
        compra (assoc record-compra-sem-id :id id)]
    (conj repositorio-compras compra)))


(defn valida-compra
  [record-compra]
  (cond (not (some? (re-matches #"\d{4}-(\d{2})-\d{2}" (:data record-compra))))
            (throw (Exception. "Data fora do padrão yyyy-mm-dd"))
        (<= (:valor record-compra) 0)
            (throw (Exception. "Valor da compra não é positivo"))
        (< (count (:estabelecimento record-compra)) 2)
            (throw (Exception. "Nome do estabelecimento com menos de 2 caracteres"))
        (not (some? (#{"Alimentação", "Automóvel", "Casa", "Educação", "Lazer", "Saúde"}
                     (:categoria record-compra))))
            (throw (Exception. "Categoria não se enquadra nas opções"))))


(defn insere!
  [atomo-repositorio record-compra-sem-id]
  (if (= Compra (type record-compra-sem-id))
    (valida-compra record-compra-sem-id))
  (swap! atomo-repositorio insere-no-vetor record-compra-sem-id))


(defn lista!
  [atomo]
  (println "Lista no átomo:" @atomo))


(defn pesquisar-por-id
  [id vetor]
  (seq (filter #(->> %
                     :id
                     #{id}
                     )
               vetor)))


(defn pesquisar-por-id!
  [id atom]
  (pesquisar-por-id id @atom))


(defn exclui-item-do-vetor
  [vetor id]
  (remove #(#{id} (:id %))
          vetor))


(defn exclui-item!
  [atomo id]
  (swap! atomo exclui-item-do-vetor id))
