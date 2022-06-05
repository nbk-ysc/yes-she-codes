(ns yes-she-codes.project.adapter.compra
  (:require [schema.core :as s]
            [yes-she-codes.project.model.compra :as model.compra]
            [yes-she-codes.project.adapter.cartao :as adapter.cartao]
            [yes-she-codes.project.wire.in.csv :as in.csv]
            [yes-she-codes.project.wire.out.compra :as out.compra]
            [yes-she-codes.project.adapter.common.csv :as common.csv]
            [yes-she-codes.project.adapter.common.util :as common.util]))

(s/defn ^:private csv-map->model :- model.compra/Compra
  [{:keys [data valor estabelecimento categoria cartao]} :- in.csv/CsvMapa]
  (when (not-any? nil? [data valor estabelecimento categoria cartao])
    #:compra{:id              (common.util/uuid)
             :data            (common.util/str->local-date data)
             :valor           (adapter.cartao/str->valor-financeiro valor)
             :estabelecimento estabelecimento
             :categoria       categoria
             :cartao          (adapter.cartao/str->num-cartao cartao)}))

(s/defn csv->model :- [model.compra/Compra]
  [csv-data :- in.csv/RawCsv]
  (common.csv/csv->maps
    csv-data
    csv-map->model))

(s/defn model->datomic
  [{:compra/keys [data] :as compra} :- model.compra/Compra]
  (-> compra
      (assoc :compra/data (common.util/local-date->inst data))))

(s/defn datomic->model-with-components :- model.compra/CompraComComponentes
  [{:compra/keys [data cartao] :as compra}]
  (-> compra
      (assoc :compra/data (common.util/inst->local-date data))
      (assoc :compra/cartao (adapter.cartao/datomic->model-with-components cartao))
      (dissoc :db/id)))

(s/defn datomic->model :- model.compra/Compra
  [{:compra/keys [data cartao] :as compra}]
  (-> compra
      (assoc :compra/data (common.util/inst->local-date data))
      (assoc :compra/cartao (:cartao/numero cartao))
      (dissoc :db/id)))

(s/defn ^:private model-with-components->wire-out :- out.compra/CompraCsv
  [compra :- model.compra/CompraComComponentes]
  (let [{:compra/keys [data valor estabelecimento categoria cartao]} compra
        {:cartao/keys [numero validade limite cliente]} cartao
        {:cliente/keys [nome cpf email]} cliente]
    {:data            (common.util/local-date->string data)
     :valor           (common.util/num->string valor)
     :estabelecimento estabelecimento
     :categoria       categoria
     :cartao          (common.util/num->string numero)
     :validade        (common.util/num->string validade)
     :limite          (common.util/num->string limite)
     :nome            nome
     :cpf             cpf
     :email           email}))

(s/defn model->csv :- in.csv/RawCsv
  [model-data :- [model.compra/CompraComComponentes]]
  (common.csv/maps->csv-data
    (map model-with-components->wire-out model-data)))
