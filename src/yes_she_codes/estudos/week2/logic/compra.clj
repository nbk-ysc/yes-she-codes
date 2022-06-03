(ns yes-she-codes.estudos.week2.logic.compra
  (:require [yes-she-codes.estudos.week2.logic.common.common :as logic.common]
            [java-time :as time]))

(defn ^:private data-menor-igual-a-hoje?
  [data]
  (not (.isAfter data (time/local-date))))

(defn ^:private bigdec-positivo?
  [valor]
  (and (= (type valor) BigDecimal)
       (pos? valor)))

(defn ^:private pelo-menos-dois-chars?
  [estabelecimento]
  (>= (count estabelecimento) 2))

(defn ^:private pertence-as-opcoes?
  [categoria]
  (let [categorias-permitidas #{"Alimentação", "Automóvel", "Casa", "Educação", "Lazer", "Saúde"}]
    (contains? categorias-permitidas categoria )))

(defmethod logic.common/valida-entidade
  "yes_she_codes.estudos.week2.model.compra.Compra"
  [{:keys [data valor estabelecimento categoria]}]
  (and (data-menor-igual-a-hoje? data)
       (bigdec-positivo? valor)
       (pelo-menos-dois-chars? estabelecimento)
       (pertence-as-opcoes? categoria)))

(defn insere-compra!
  [compras record]
  (swap! compras logic.common/insere-record record))

(defn lista-compras!
  [compras]
  (logic.common/lista-entidade @compras))

(defn pesquisa-compra-por-id!
  [compras id]
  (logic.common/pesquisa-record-por-id @compras id))

(defn exclui-compra!
  [compras id]
  (swap! compras logic.common/exclui-record id))
