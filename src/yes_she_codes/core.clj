(ns yes-she-codes.core)

(defn str-to-long [valor]
  (Long/parseLong (clojure.string/replace valor #" " "")))

; outra forma de pegar a data sem ser com o substr
(defn retorna-mes [data]
  (second (re-matches #"\d{4}-(\d{2})-\d{2}" data)))

; função intermediária genérica
(defn filtra-compras [funcao compras]
  (vec (filter funcao compras)))