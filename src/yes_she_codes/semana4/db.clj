(ns yes-she-codes.semana4.db
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [yes-she-codes.semana4.logic :as logic]
            [yes-she-codes.semana4.model :as model]))


(def db-uri "datomic:dev://localhost:4334/she-codes")

(defn cria-conexao
  []
  (d/create-database db-uri)       ; criando o banco -> retorna true
  (d/connect db-uri)               ; conectando no banco
  )
;(pprint cria-conexao)


; defininco o schema
 (def schema-datomic
  [{:db/ident        :compra/data
    :db/doc          "A data de uma compra"
    :db/valueType    :db.type/string
    :db/cardinality  :db.cardinality/one    ; cardinality esta definindo que so podemos ter UMA :data
    }
   {
    :db/ident        :compra/valor
    :db/doc          "O valor de uma compra"
    :db/valueType    :db.type/bigdec
    :db/cardinality  :db.cardinality/one    ; cardinality esta definindo que so podemos ter UM :valor
    }
   {:db/ident        :compra/estabelecimento
    :db/doc          "O estabelecimento em que a compra foi feita"
    :db/valueType    :db.type/string
    :db/cardinality  :db.cardinality/one    ; cardinality esta definindo que so podemos ter UM :estabelecimento
    }
   {:db/ident        :compra/categoria
    :db/doc          "A categoria da compra"
    :db/valueType    :db.type/string
    :db/cardinality  :db.cardinality/one    ; cardinality esta definindo que so podemos ter UMA :categoria
    }
   {:db/ident        :compra/cartao
    :db/doc          "O cartao com que a compra foi feita"
    :db/valueType    :db.type/long
    :db/cardinality  :db.cardinality/one    ; cardinality esta definindo que so podemos ter UM :cartao
    }
   ]
  )


(defn cria-schema
  [conn]
  (d/transact conn schema-datomic))


(defn salva-compra!
  [conn compra]
  @(d/transact conn [compra]))


(defn carrega-compras-no-banco!
  [conn]
  (let [lista-compras (logic/processa-csv "src/yes_she_codes/semana1/csv/compras.csv" (fn [[data valor estabelecimento categoria cartao]]
                                                                                        (model/nova-compra data valor estabelecimento categoria cartao)))]
    (doseq [compra lista-compras] (salva-compra! conn compra))
    ))

(defn lista-compras!
  [conn]
  (d/q '[:find ?entidade
         :where [?entidade :compra/cartao]] conn))


(defn apaga-banco
  []
  (d/delete-database db-uri)           ; deletando db -> retorna true
  )


;(defn lista-compras-por-cartao!
;  [conn cartao]
;  ; operacoes
;  )

