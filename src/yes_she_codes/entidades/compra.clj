(ns yes-she-codes.entidades.compra
  (:require [yes-she-codes.util :as y.util]
            [java-time :as time]
            [schema.core :as s]
            [yes-she-codes.entidades.cartao :as y.cartao]))

(def DataDeCompraValida (s/constrained java.time.LocalDate
                                       (fn [data]
                                         (let [data-atual (time/local-date)]
                                           (or (= data data-atual) (time/before? data data-atual))))))

(def EstabelecimentoValido (y.util/min-caracteres 2))
(def CategoriaValida (s/enum "Alimentação" "Automóvel" "Casa" "Educação" "Lazer" "Saúde"))

(def CompraSchema {(s/optional-key :id) y.util/IdOpcional
                   :data                DataDeCompraValida
                   :valor               y.util/ValorPositivo
                   :estabelecimento     EstabelecimentoValido
                   :categoria           CategoriaValida
                   :cartao              y.cartao/NumeroDeCartaoValido})


(s/defn ->Compra :- CompraSchema
  [id :- y.util/IdOpcional
   data :- DataDeCompraValida
   valor :- y.util/ValorPositivo
   estabelecimento :- EstabelecimentoValido
   categoria :- CategoriaValida
   cartao :- y.cartao/NumeroDeCartaoValido]

  {:id              id
   :data            data
   :valor           valor
   :estabelecimento estabelecimento
   :categoria       categoria
   :cartao          cartao})

(defn compra-valida? [compra]
  (let [valida-data (time/after? (time/local-date) (get compra :data))
        valida-valor (and (number? (get compra :valor)) (>= (get compra :valor) 0))
        valida-estabelecimento (>= (count (get compra :estabelecimento)) 2)
        categoria (get compra :categoria)
        valida-categoria (or (= "Alimentação" categoria)
                             (= "Automóvel" categoria)
                             (= "Casa" categoria)
                             (= "Educação" categoria)
                             (= "Lazer" categoria)
                             (= "Saúde" categoria))]

    (and valida-data valida-valor valida-estabelecimento valida-categoria)))

; quando estava usando a data como string
(defn lista-compras-por-mes-sub
  [mes compras]
  (filter #(if (= (subs (:data %) 5 7) mes) true) compras))

(defn lista-compras-por-mes
  [mes compras]
  (filter #(= (y.util/mes-da-data mes) (y.util/mes-da-data (:data %))) compras))

(defn lista-compras-por-cartao
  [cartao compras]
  (filter #(if (= (:cartao %) cartao) true) compras))

(defn lista-compras-por-estabelecimento
  [estabelecimento compras]
  (filter #(if (= (:estabelecimento %) estabelecimento) true) compras))

(defn total-gasto [compras]
  (reduce #(+ %1 (:valor %2)) 0 compras))

(defn total-gasto-por-mes
  [mes compras]
  (let [lista-compras-mes (lista-compras-por-mes mes compras)]
    (total-gasto lista-compras-mes)))

(defn total-gasto-por-mes-e-cartao
  [cartao mes compras]
  (let [lista-compras-cartao (lista-compras-por-cartao cartao compras)
        lista-compras-mes (lista-compras-por-mes mes lista-compras-cartao)]
    (total-gasto lista-compras-mes)))

(defn filtra-compras-por-valor
  [valor-minimo valor-maximo compras]
  (filter #(if (and (>= (:valor %) valor-minimo) (<= (:valor %) valor-maximo)) true) compras))

(defn agrupa-gastos-por-categoria [compras]
  (vec (map (fn [[categoria compras-da-categoria]]
              {:categoria   categoria
               :total-gasto (total-gasto compras-da-categoria)})
            (group-by :categoria compras))))
