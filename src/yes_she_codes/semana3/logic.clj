(ns yes-she-codes.semana3.logic
  (:use clojure.pprint))

(defn count-maior-ou-igual-a-dois?
  [x]
  (>= (count x) 2))

(def cpf-valido? (partial re-matches #"\d{3}.\d{3}.\d{3}-\d{2}"))

(def email-valido? (partial re-matches #"\S+@\S+\.\S+"))

(defn numero-valido?
  [numero-cartao]
  (and (>= numero-cartao 0) (<= numero-cartao 10000000000000000)))

(defn cvv-valido?
  [cvv]
  (and (>= cvv 0) (<= cvv 999)))

(def validade-valida? (partial re-matches #"\d{4}-\d{2}"))

(defn limite-valido?
  [limite]
  (and (>= limite 0) (decimal? limite)))

(def data-valida? (partial re-matches #"\d{4}-\d{2}-\d{2}"))

(defn valor-valido?
  [valor]
  (and (> valor 0) (decimal? valor)))

(defn categoria-valida?
  [categoria]
  (let [categorias ["Alimentação" "Automóvel" "Casa" "Educação" "Lazer" "Saúde"]]
   (some #(= categoria %) categorias)))




