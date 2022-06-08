(ns yes_she_codes.semana4.db
  (:use clojure.pprint)
  (:require [datomic.api :as d]
            [yes_she_codes.semana4.utils :as y.utils]))

(def db-uri "datomic:dev://localhost:4334/she-codes")

(defn abre-conexao []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn apaga-banco []
  (d/delete-database db-uri))

(def schema-datomic [
                     ;COMPRAS
                     {:db/ident       :compra/data
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "A data de uma compra"}
                     {:db/ident       :compra/valor
                      :db/valueType   :db.type/bigdec
                      :db/cardinality :db.cardinality/one
                      :db/doc         "O valor de uma compra"}
                     {:db/ident       :compra/estabelecimento
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "O estabelecimento de uma compra"}
                     {:db/ident       :compra/categoria
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "A categoria de uma compra"}
                     {:db/ident       :compra/cartao
                      :db/valueType   :db.type/ref
                      :db/cardinality :db.cardinality/one
                      :db/doc         "O cartao de uma compra"}

                     ;CARTÕES
                     {:db/ident       :cartao/numero
                      :db/valueType   :db.type/long
                      :db/cardinality :db.cardinality/one
                      :db/doc         "O número de um cartão"}
                     {:db/ident       :cartao/cvv
                      :db/valueType   :db.type/long
                      :db/cardinality :db.cardinality/one
                      :db/doc         "O cvv de um cartão"}
                     {:db/ident       :cartao/validade
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "A validade de um cartão"}
                     {:db/ident       :cartao/limite
                      :db/valueType   :db.type/bigdec
                      :db/cardinality :db.cardinality/one
                      :db/doc         "O limite de um cartão"}
                     {:db/ident       :cartao/cliente
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Um cliente de um cartão"}])

(defn cria-schema [conn]
  (d/transact conn schema-datomic))

(defn compra->datomic-instant
  [compra]
  (-> compra
      (clojure.set/rename-keys {:data            :compra/data
                                :valor           :compra/valor
                                :estabelecimento :compra/estabelecimento
                                :categoria       :compra/categoria
                                :cartao          :compra/cartao})
      y.utils/ajusta-compra-local-para-java-date))

(defn datomic->compra-instant
  [compra]
  (-> compra
      (clojure.set/rename-keys {:db/id                  :id
                                :compra/data            :data
                                :compra/valor           :valor
                                :compra/estabelecimento :estabelecimento
                                :compra/categoria       :categoria
                                :compra/cartao          :cartao})
      y.utils/ajusta-compra-java-date-para-local))

(defn compra->datomic
  [compra]
  (-> compra
      (clojure.set/rename-keys {:data            :compra/data
                                :valor           :compra/valor
                                :estabelecimento :compra/estabelecimento
                                :categoria       :compra/categoria
                                :cartao          :compra/cartao})))

(defn datomic->compra
  [compra]
  (-> compra
      (clojure.set/rename-keys {:db/id                  :id
                                :compra/data            :data
                                :compra/valor           :valor
                                :compra/estabelecimento :estabelecimento
                                :compra/categoria       :categoria
                                :compra/cartao          :cartao})))

(defn cartao->datomic
  [cartao]
  (-> cartao
      (clojure.set/rename-keys {:numero   :cartao/numero
                                :cvv      :cartao/cvv
                                :validade :cartao/validade
                                :limite   :cartao/limite
                                :cliente  :cartao/cliente})))

(defn datomic->cartao
  [cartao]
  (-> cartao
      (clojure.set/rename-keys {:db/id           :id
                                :cartao/numero   :numero
                                :cartao/cvv      :cvv
                                :cartao/validade :validade
                                :cartao/limite   :limite
                                :cartao/cliente  :cliente})))

(defn salva-compras!
  [conn compras]
  (let [registros (map compra->datomic compras)]
    (d/transact conn registros)))

(defn salva-cartoes!
  [conn cartoes]
  (let [registros (map cartao->datomic cartoes)]
    (println registros)
    (d/transact conn registros)))

(defn lista-compras! [db]
  (let [resultado (d/q '[:find (pull ?entidade [*])
                         :where [?entidade :compra/data]] db)]
    (->> (vec (flatten resultado))
         (map datomic->compra))))

(defn lista-cartoes! [db]
  (let [resultado (d/q '[:find (pull ?entidade [*])
                         :where [?entidade :cartao/numero]] db)]
    (->> (vec (flatten resultado))
         (map datomic->cartao))))

(defn carrega-compras-no-banco!
  [conn caminho funcao-mapeamento]
  (let [entidades (y.utils/processa-csv caminho funcao-mapeamento)]
    (salva-compras! conn entidades)))

(defn carrega-cartoes-no-banco!
  [conn caminho funcao-mapeamento]
  (let [entidades (y.utils/processa-csv caminho funcao-mapeamento)]
    (salva-cartoes! conn entidades)))

(defn lista-compras-por-cartao!
  ([db numero-cartao]
   (let [resultado (d/q '[:find (pull ?compra [*])
                          :in $ ?numero-cartao
                          :where [?compra :compra/cartao ?numero-cartao]]
                        db numero-cartao)]
     (->> (vec (flatten resultado))
          (map datomic->compra))))

  ;; SÓ FUNCIONA COM A DATA COMO STRING:
  ([db numero-cartao mes]
   (let [resultado (d/q '[:find (pull ?compra [*])
                          :in $ ?numero-cartao ?mes
                          :where [?compra :compra/cartao ?numero-cartao]
                          [?compra :compra/data ?data]
                          [((fn [dt] (get (clojure.string/split dt #"-") 1)) ?data) ?month]
                          [(= ?month ?mes)]]
                        db numero-cartao mes)]
     (->> (vec (flatten resultado))
          (map datomic->compra)))))

; ORGANIZA POR CATEGORIA MAS NÃO AGRUPA
(defn lista-gastos-por-categoria-query!
  [db]
  (let [resultado (d/q '[:find ?categoria (pull ?compra [*])
                         :keys :compra/categoria compra
                         :where [?compra :compra/valor]
                         [?compra :compra/categoria ?categoria]]
                       db)]
    (->> (vec (flatten resultado))
         (map datomic->compra))))

;AGRUPA GASTOS POR CATEGORIA, MAS SÓ DEPOIS DA QUERY
(defn lista-gastos-por-categoria!
  [db]
  (let [resultado (d/q '[:find (pull ?compra [*])
                         :where [?compra :compra/valor]]
                       db)]
    (->> (vec (flatten resultado))
         (map datomic->compra)
         (group-by :categoria))))

(defn busca-cartao-por-numero!
  [db numero-cartao]
  (let [resultado (d/q '[:find (pull ?cartao [*])
                         :in $ ?numero-cartao
                         :where [?cartao :cartao/numero ?numero-cartao]]
                       db numero-cartao)]
    (datomic->cartao (ffirst resultado))))


(defn ref-cartao-na-compra
  [db compra]
  (let [cartao (busca-cartao-por-numero! db (:cartao compra))
        id-do-cartao (:id cartao)]
    (assoc compra :cartao id-do-cartao)))

(defn carrega-compras-no-banco-com-ref!
  [conn caminho funcao-mapeamento]
  (let [entidades (y.utils/processa-csv caminho funcao-mapeamento)
        entidades-com-ref (map (partial ref-cartao-na-compra (d/db conn)) entidades)]
    (salva-compras! conn entidades-com-ref)))