(ns yes-she-codes.semana3.logic
  (:use clojure.pprint))

(defn count-maior-ou-igual-a-dois?
  [x]
  (>= (count x) 2))

(defn cpf-valido?
  [cpf]
  (when (re-matches #"\d{3}.\d{3}.\d{3}-\d{2}" cpf)
    true))

(defn email-valido?
  [email]
  (when (re-matches #".+\@.+\..+" email)
    true))

(defn numero-valido?
  [numero-cartao]
  (and (>= numero-cartao 0) (<= numero-cartao 10000000000000000)))

(defn cvv-valido?
  [cvv]
  (and (>= cvv 0) (<= cvv 999)))

(defn validade-valida?
  [validade]
  (when (re-matches #"\d{4}-\d{2}" validade)
    true))

(defn limite-valido?
  [limite]
  (and (>= limite 0) (decimal? limite)))

(defn data-valida?
  [data]
  (when (re-matches #"\d{4}-\d{2}-\d{2}" data)
    true))

(defn valor-valido?
  [valor]
  (and (> valor 0) (decimal? valor)))

(defn categoria-valida?
  [categoria]
  (let [categorias ["Alimentação" "Automóvel" "Casa" "Educação" "Lazer" "Saúde"]]
    (when (some #(= categoria %) categorias)
      true)))




