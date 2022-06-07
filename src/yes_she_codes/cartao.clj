(ns yes-she-codes2.cartao
  (:require [yes-she-codes2.db :as y.db])
  (:require [yes-she-codes2.compras :as y.compras]))

(defn novo-cartao [numero, cvv, validade, limite, cliente]
  {:numero   numero,
   :cvv      cvv,
   :validade validade,
   :limite   limite,
   :cliente  cliente})

(defn transforma-cartao [[numero, cvv, validade, limite, cliente]]
  (novo-cartao numero, cvv, validade, limite, cliente))

(defn transforma-cartoes [array]
  (map transforma-cartao array))

(defn lista-cartoes []
  (transforma-cartoes (y.db/busca-registros-de-cartoes)))

;fiz essa etapa para encontrar o cartão dentro da lista de compras

(defn achei-o-cartao-na-lista-de-compras? [cartao todas-as-compras]
  (= cartao (:cartao todas-as-compras)))

(defn comprei-no-cartao? [cartao lista-de-cartoes]
  (filter #(achei-o-cartao-na-lista-de-compras? cartao %) lista-de-cartoes))

(comprei-no-cartao? 1234123412341234 (y.compras/lista-compras))

;fiz essa etapa para somar valores de um o cartão na lista

(defn total-gasto [lista-de-compras]
  (reduce compra 0 lista-de-compras))

(def total-gasto-no-cartao (comp total-gasto comprei-no-cartao?))

(println "Valor total das compras de um cartao" (total-gasto-no-cartao 1234123412341234 (y.compras/lista-compras)))
