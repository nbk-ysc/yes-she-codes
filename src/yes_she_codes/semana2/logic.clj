(ns yes-she-codes.semana2.logic
  (:require [yes-she-codes.semana2.model :as ysc.model]))

(defn proximo-id [entidades]
  (if-not (empty? entidades)
    (inc (apply max (map :id entidades)))
    1))

(defn insere-item-no-vetor
  "Atribui um id ao item e o armazena no vetor."
  [vetor item]
  (let [id (proximo-id vetor)
        item-com-id (assoc item :id id)]
    (conj vetor item-com-id)))

(defn insere-item!
  "Inclui um novo item no átomo."
  [atomo item]
  (if (ysc.model/valido? item)
    (swap! atomo insere-item-no-vetor item)))

(defn lista-itens!
  "Lista os itens a partir de um átomo."
  [atomo]
  (println @atomo))

(defn pesquisa-item-por-id
  "Retorna o item com o identificador 'id' no vetor."
  [vetor id]
  (->> vetor
       (filter #(= id (:id %)))
       first))

(defn pesquisa-item-por-id!
  "Pesquisa por um item em um átomo a partir do seu id."
  [atomo id]
  (pesquisa-item-por-id @atomo id))

(defn exclui-item
  "Exclui um item com o identificador 'id' de um vetor."
  [vetor id]
  (remove #(= id (:id %)) vetor))

(defn exclui-item!
  "Exclui um item com o identificador 'id' do átomo."
  [atomo id]
  (swap! atomo exclui-item id))