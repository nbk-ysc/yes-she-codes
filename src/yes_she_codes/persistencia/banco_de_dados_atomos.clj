(ns yes-she-codes.persistencia.banco-de-dados-atomos
  (:require [yes-she-codes.dominio.compra :as y.compra]
            [yes-she-codes.dominio.cliente :as y.cliente]
            [yes-she-codes.dominio.cartao :as y.cartao]
            [yes-she-codes.util :as y.util]
            [yes-she-codes.csv.leitor-csv :as y.csv]
            [java-time :as time]))

;;;;; Átomos que persistem cada uma das entidades
(def repositorio-de-clientes (atom []))
(def repositorio-de-cartoes (atom []))
(def repositorio-de-compras (atom []))



;;;;; Lógicas genéricas de persistência de entidades
(defn- insere-entidade [entidades entidade]
  (let [id-da-entidade (y.util/proximo-id entidades)
        entidade-com-id (assoc entidade :id id-da-entidade)]
    (conj entidades entidade-com-id)))


(defn- pesquisa-por-id [entidades id]
  (first (filter #(= id (:id %)) entidades)))


(defn- deve-remover? [id entidade]
  (->> entidade
       :id
       (= id)))


(defn- exclui-entidade [entidades id]
  (remove #(deve-remover? id %) entidades))


(defn- carrega-registros! [insere registros]
  (doseq [registro registros]
        (insere registro)))



;;;;; Lógicas de persistência de COMPRAS
(defn insere-compra! [compra]
  (if (y.compra/compra-valida? compra)
    (swap! repositorio-de-compras insere-entidade compra)
    (throw (ex-info "Compra inválida!" {:compra compra}))))


(defn- carrega-compras-no-atomo! []
  (carrega-registros! insere-compra! (y.csv/processa-arquivo-de-compras!)))


(defn exclui-compra! [id]
  (swap! repositorio-de-compras exclui-entidade id))


(defn pesquisa-compra-por-id! [id]
  (pesquisa-por-id @repositorio-de-compras id))


(defn lista-compras! []
  @repositorio-de-compras)



;;;;; Lógicas de persistência de CLIENTES
(defn insere-cliente! [cliente]
  (swap! repositorio-de-clientes insere-entidade cliente))


(defn- carrega-clientes-no-atomo! []
  (carrega-registros! insere-cliente! (y.csv/processa-arquivo-de-clientes!)))


(defn pesquisa-cliente-por-id! [id]
  (pesquisa-por-id @repositorio-de-clientes id))


(defn lista-clientes! []
  @repositorio-de-clientes)


(defn exclui-cliente! [id]
  (swap! repositorio-de-clientes exclui-entidade id))



;;;;; Lógicas de persistência de CARTÕES
(defn insere-cartao! [cartao]
  (swap! repositorio-de-cartoes insere-entidade cartao))


(defn- carrega-cartoes-no-atomo! []
  (carrega-registros! insere-cartao! (y.csv/processa-arquivo-de-cartoes!)))


(defn pesquisa-cartao-por-id! [id]
  (pesquisa-por-id @repositorio-de-cartoes id))


(defn lista-cartoes! []
  @repositorio-de-cartoes)


(defn exclui-cartao! [id]
  (swap! repositorio-de-cartoes exclui-entidade id))



;;;;; Limpa e carrega registros nos átomos
(defn limpa-banco-de-dados! []
  (doseq [atomo [repositorio-de-clientes repositorio-de-cartoes repositorio-de-compras]]
    (swap! atomo (fn [entidades]
                   []))))

(defn carrega-banco-de-dados! []
  (limpa-banco-de-dados!)
  (carrega-clientes-no-atomo!)
  (carrega-cartoes-no-atomo!)
  (carrega-compras-no-atomo!))