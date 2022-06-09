(ns yes-she-codes.semana4.db
  (:use [clojure pprint])
  (:require [datomic.api :as d]
            [yes-she-codes.semana4.model :as y.model]))

(def db-uri "datomic:dev://localhost:4334/she-codes")

(defn cria-conexao []
  (d/create-database db-uri)
  (d/connect db-uri))

(defn apaga-banco []
  (d/delete-database db-uri))

(def schema-datomic [
                     ;Compras
                     {:db/ident       :compra/id
                      :db/valueType   :db.type/uuid
                      :db/cardinality :db.cardinality/one
                      :db/unique      :db.unique/identity}
                     {:db/ident       :compra/data
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc          "Data da compra"}
                     {:db/ident       :compra/valor
                      :db/valueType   :db.type/bigdec
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Valor da compra"}
                     {:db/ident       :compra/estabelecimento
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Lugar da compra"}
                     {:db/ident       :compra/categoria
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Categoria da compra"}
                     {:db/ident       :compra/cartao
                      :db/valueType   :db.type/long
                      :db/cardinality :db.cardinality/one
                      :db/doc         "O cartão que efetuou a compra"}

                     ;Cartoes
                     {:db/ident       :cartao/numero
                      :db/valueType   :db.type/long
                      :db/cardinality :db.cardinality/one
                      :db/doc          "Numero do cartao"}
                     {:db/ident       :cartao/cvv
                      :db/valueType   :db.type/long
                      :db/cardinality :db.cardinality/one
                      :db/doc         "CVV do cartao"}
                     {:db/ident       :cartao/validade
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Validade do cartao"}
                     {:db/ident       :cartao/limite
                      :db/valueType   :db.type/bigdec
                      :db/cardinality :db.cardinality/one
                      :db/doc         "Limite do cartao"}
                     {:db/ident       :cartao/cliente
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/one
                      :db/doc         "O cliente dono do cartao"}
                     {:db/ident       :cartao/compra
                      :db/valueType   :db.type/string
                      :db/cardinality :db.cardinality/many
                      :db/doc         "Compras efetuadas pelo cartao"}
                     ])


(defn cria-schema [conn]
  (d/transact conn schema-datomic))

(defn salva-compra! [conn compra]
  (d/transact conn [compra]))

(defn lista-compras! [banco]
  (d/q '[:find  (pull ?entidade [*])
         :where [?entidade :compra/data]] banco))

(defn lista-compras-por-cartao!
 ([banco cartao]
  (d/q '[:find  (pull ?entidade [*])
         :in $ ?cartao-procurado
         :where [?entidade :compra/cartao ?cartao-procurado]] banco cartao))
  ([banco cartao mes]
   (d/q
     '[:find [(pull ?compra [*]) ...]
       :in $ ?cartao-procurado ?mes-procurado
       :where [?compra :compra/cartao ?cartao-procurado]
              [?compra :compra/data ?data]
              [((fn [data] (subs data 5 7)) ?data) ?mes]
              [(= ?mes ?mes-procurado)]] banco cartao mes )))

(def csv->compra [str
                  bigdec
                  str
                  str
                  #(Long/parseLong (clojure.string/replace % #" " ""))])

(defn- processa-csv [caminho-arquivo]
  (->> (slurp caminho-arquivo)
       clojure.string/split-lines
       rest
       (map #(clojure.string/split % #","))))

(defn- converte-valores-na-linha [funcoes-de-conversao linha]
  (map #(%1 %2) funcoes-de-conversao linha))

(defn carrega-compras-no-banco! [conn]
  (let [compras (->> (processa-csv "/Users/marta.lima/Desktop/YSC/yes-she-codes/src/yes_she_codes/semana1/compras.csv")
                (map #(converte-valores-na-linha csv->compra %))
                     (map y.model/nova-compra ))]
    (d/transact conn compras)))

(defn lista-compras-por-categoria! [banco]
  (let [compras (lista-compras! banco)]
    (group-by :compra/categoria (flatten compras))))

(def csv->cartao [#(Long/parseLong (clojure.string/replace % #" " ""))
                  #(Long/parseLong (clojure.string/replace % #" " ""))
                  str
                  bigdec
                  str])

(defn carrega-cartoes-no-banco! [conn]
  (let [cartoes (->> (processa-csv "/Users/marta.lima/Desktop/YSC/yes-she-codes/src/yes_she_codes/semana1/cartoes.csv")
                     (map #(converte-valores-na-linha csv->cartao %))
                     (map y.model/novo-cartao))]
    (d/transact conn cartoes)))

(defn lista-cartoes! [banco]
  (d/q '[:find  (pull ?entidade [*])
         :where [?entidade :cartao/numero]] banco))

















