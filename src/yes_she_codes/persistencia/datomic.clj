(ns yes-she-codes.persistencia.datomic
  (:require [datomic.api :as d]
            [yes-she-codes.db.datomic :as datomic]
            [yes-she-codes.csv.leitor-csv :as csv.leitor-csv]))


(defn- db-url []
  "datomic:dev://localhost:4334/she-codes")

(defn cria-conexao []
  (d/create-database (db-url))
  (d/connect (db-url)))

(defn apaga-banco! []
  (d/delete-database (db-url)))

(defn cria-schema! [conn]
  @(d/transact conn datomic/schema-datomic))


(defn cartao->registro-datomic [cartao]
    (-> cartao
        (clojure.set/rename-keys {:id       :db/id
                                  :numero   :cartao/numero
                                  :cvv      :cartao/cvv
                                  :validate :cartao/validate
                                  :limite   :cartao/limite
                                  :cliente  :cartao/cliente})))

(defn registro-datomic->cartao [registro]
  (-> registro
      (clojure.set/rename-keys {:db/id           :id
                                :cartao/numero   :numero
                                :cartao/cvv      :cvv
                                :cartao/validate :validate
                                :cartao/limite   :limite
                                :cartao/cliente  :cliente})))


(defn pesquisa-cartao [snapshot numero]
  (-> (d/q '[:find (pull ?id-cartao [*])
             :in $ ?numero
             :where [?id-cartao :cartao/numero ?numero]]
           snapshot numero)
      ffirst
      registro-datomic->cartao))


(defn salva-cartao! [conn cartao]
  (let [registro (cartao->registro-datomic cartao)]
    @(d/transact conn [registro])))


(defn lista-cartoes! [snapshot]
  (mapv registro-datomic->cartao
        (flatten (d/q '[:find (pull ?id-cartao [*])
                        :where [?id-cartao :cartao/numero]]
                      snapshot))))


(defn compra->registro-datomic [compra]
  (-> compra
      (clojure.set/rename-keys {:id              :db/id
                                :data            :compra/data
                                :valor           :compra/valor
                                :cartao          :compra/cartao
                                :categoria       :compra/categoria
                                :estabelecimento :compra/estabelecimento})))


(defn registro-datomic->compra [registro]
  (-> registro
      (clojure.set/rename-keys {:db/id                  :id
                                :compra/data            :data
                                :compra/valor           :valor
                                :compra/cartao          :cartao
                                :compra/categoria       :categoria
                                :compra/estabelecimento :estabelecimento})))



(defn salva-compra! [conn compra]
  (some->> (:cartao compra)
           (pesquisa-cartao (d/db conn))
           :id
           (assoc compra :cartao)                               ; relaciona compra com ID do cartão
           (compra->registro-datomic)
           (conj [])
           (d/transact conn)
           deref))


(defn lista-compras! [snapshot]
  (vec (map registro-datomic->compra
            (flatten (d/q '[:find (pull ?compra [*])
                            :where [?compra :compra/valor]]
                          snapshot)))))



(defn carrega-compras-no-banco! [conn]
  (mapv
    (partial salva-compra! conn)
    (csv.leitor-csv/processa-arquivo-de-compras!)))

(defn carrega-cartoes-no-banco! [conn]
  (mapv
    (partial salva-cartao! conn)
    (csv.leitor-csv/processa-arquivo-de-cartoes!)))


(defn lista-compras-por-cartao! [snapshot cartao]
  (->> (d/q '[:find (pull ?id-compra [*])
              :in $ ?cartao
              :where [?id-compra :compra/cartao ?cartao]]
            snapshot cartao)
       flatten
       (map registro-datomic->compra)
       vec))


(defn lista-compras-por-cartao-e-mes [snapshot cartao mes]
  (-> '[:find (pull ?id-compra [*])
        :in $ ?cartao ?mes
        :where [?id-compra :compra/cartao ?cartao]
        [?id-compra :compra/data ?data]
        [(yes-she-codes.persistencia.banco-de-dados-datomic/filtro-mes ?data ?mes)]]
      (d/q snapshot [:cartao/numero cartao] mes) ; usa lookup ref para pesquisar pelo número do cartão
      flatten
      (#(mapv registro-datomic->compra %))))


(def lista-gastos-por-categoria!
  (comp (partial group-by :categoria) lista-compras!))


(defn carrega-banco-de-dados! []
  (apaga-banco!)
  (let [conexao (cria-conexao)]
    (cria-schema! conexao)
    (carrega-cartoes-no-banco! conexao)
    (carrega-compras-no-banco! conexao)))
