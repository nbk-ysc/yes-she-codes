(ns yes-she-codes.core
  (:require yes-she-codes.dominio.compras :as  y.compras)
  (:require yes-she-codes.atom :as y.atom)
  (:require yes-she-codes.dominio.clientes :as y.clientes)
  (:require yes-she-codes.dominio.cartao :as y.cartao))


(println "Lista de clientes\n" (y.clientes/lista-clientes))
(println "Lista de cartoes\n" (y.cartao/lista-cartoes))
(println "Lista de compras\n" (y.compras/lista-compras))
(println "Total gasto em compras de todos os cartões\n" (y.compras/total-gasto (y.compras/lista-compras)))
(println "Total gasto em compras de apenas um cartao\n" (y.cartao/total-gasto-no-cartao 1234123412341234 (y.logic/lista-compras)))
(println "Busca das compras de um mes\n " (y.compras/compras-no-mes "04" (y.compras/lista-compras)))
(println "Calcular o total da fatura de um mês\n" (y.compras/total-gasto-no-mes "04" (y.compras/lista-compras)))
(println "Busca de um estabelecimento\n" (y.compras/comprei-no-estabelecimento? "Alura" (y.compras/lista-compras)))
























































;(defn insere-compra
;  [compra compras]
;  (let [id (inc (count compras))
;        compras-inserir (assoc compra :ID id)]
;    (conj compras compras-inserir)))

;(defn insere-compra!
;  [compras compra]
;  (swap! compras insere-compra compra))

(defn id-uuid []
  (.toString (java.util.UUID/randomUUID)))

(defrecord Paciente [id nome nascimento])

;(pprint (->Paciente 15, "Guilherme", "19/09/1981"))
(pprint (Paciente. (id-uuid), "Guilherme", "19/09/1981"))





;(defn nova-compra [data, valor, estabelecimento, categoria, cartao]
;  {:data            data,
;   :valor           valor,
;   :estabelecimento estabelecimento,
;   :categoria       categoria,
;   :cartao          cartao})
;
;(defn transforma-compra []
;  (Compras))
;
;(defn transforma-compras [array]
;  (map transforma-compra array))
;
;(defn lista-compras []
;  (transforma-compras (y.db/busca-registros-de-compras)))
;
;(println "Lista de compras\n" (lista-compras))


;(defn insere-compra-atomo
;  [Compras @repositorio-de-compras]
;  (update Compras  @repositorio-de-compras pop))



