(ns yes-she-codes.semana2.logic
  (:use [clojure pprint]))

(defn proximo-id [entidades]
  (if-not (empty? entidades)
    (+ 1 (apply max (map :id entidades)))
    1))


(defn valida-data
  [compra]
  (if-not (re-matches #"\d{4}-\d{2}-\d{2}" (:data compra))
    (throw (ex-info "Fomato da data está errado, deve ser YYY-MM-DD" {:data (:data compra)}))
    compra))

(defn valida-valor
  [compra]
  (if-not (and (= (class (:valor compra)) java.math.BigDecimal) (> (:valor compra) 0))
    (throw (ex-info "Valor deve ser bigdecimal e maior que 0" {:valor (:valor compra)}))
    compra))

(defn valida-estabelecimento
  [compra]
  (if-not (>= (count (:estabelecimento compra)) 2)
    (throw (ex-info "Estabelecimento deve ter ao menos dois caracteres" {:estabelecimento (:estabelecimento compra)}))
    compra))


(defn valida-categoria
  [compra]
  (let [categorias ["Alimentação" "Automóvel" "Casa" "Educação" "Lazer" "Saúde"]]
    (if-not (some #(= (:categoria compra) %) categorias)
      (throw (ex-info "Categoria inválida" {:categorias-validas categorias}))
      compra)))

(defn valida-compra
  [compra]
  (if (and (valida-data compra)
           (valida-valor compra)
           (valida-estabelecimento compra)
           (valida-categoria compra))
    true))


























