(ns yes-she-codes.dominio.compra
  (:require [yes-she-codes.util :as y.util]
            [java-time :as time]
            [schema.core :as s]
            [yes-she-codes.dominio.cartao :as y.cartao]))


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


(defn filtra-compras [predicado compras]
  (vec (filter predicado compras)))


(defn filtra-compras-no-mes [mes compras]
  (filtra-compras #(= mes (y.util/mes-da-data (:data %))) compras))


(defn filtra-compras-no-estabelecimento [estabelecimento compras]
  (filtra-compras #(= estabelecimento (:estabelecimento %)) compras))


(defn filtra-compras-por-valor [minimo maximo compras]
  (filtra-compras #(and (>= (:valor %) minimo)
                        (<= (:valor %) maximo))
                  compras))

(defn filtra-compras-por-cartao [cartao compras]
  (filtra-compras #(= cartao (:cartao %)) compras))


(defn total-gasto [compras]
  (reduce + (map :valor compras)))


(def total-gasto-no-mes (comp total-gasto filtra-compras-no-mes))


(defn agrupa-gastos-por-categoria [compras]
  (vec (map (fn [[categoria compras-da-categoria]]
              {:categoria   categoria
               :total-gasto (total-gasto compras-da-categoria)})
            (group-by :categoria compras))))