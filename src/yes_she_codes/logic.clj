(ns yes_she_codes.logic
  (:require [yes_she_codes.db :as ysc.db]))


;;CLIENTE
(defn novo-cliente
  [nome cpf email]
  {:nome nome :cpf cpf :email email})

(defn transforma-cliente
  [[nome cpf email]]
  (novo-cliente nome, cpf, email))

(defn lista-clientes
  [registro]
  (map transforma-cliente registro))


;;CARTAO
(defn novo-cartao
  [numero cvv validade limite cliente]
  {:numero numero :cvv cvv :validade validade :limite limite :cliente cliente})

(defn transforma-cartao
  [[numero cvv validade limite cliente]]
  (novo-cartao numero cvv validade limite cliente))

(defn lista-cartoes
  [registro]
  (map transforma-cartao registro))

;COMPRA
(defn nova-compra
  [data valor estabelecimento categoria cartao]
  {:data data :valor valor :estabelecimento estabelecimento :categoria categoria :cartao cartao})

(defn transforma-compra
  [[data valor estabelecimento categoria cartao]]
  (nova-compra data valor estabelecimento categoria cartao))


(defn lista-compras
  [registro]
  (map transforma-compra registro))


;;OUTRAS FUNÇÕES
(defn total-gastos
  [lista-compras]
  (reduce + (map :valor lista-compras)))

(defn mesmo-mes?
  [mes compra]
  (.contains (get compra :data) mes))

(defn compra-por-mes
  [mes lista]
  (filter #(mesmo-mes? mes %) lista))

(defn mesmo-estabelecimento?
  [estabelecimento compra]
  (= (get compra :estabelecimento) estabelecimento))

(defn compra-por-estabelecimento
  [estabelecimento lista]
  (filter #(mesmo-estabelecimento? estabelecimento %) lista))

(defn mesmo-cartao?
  [numero compra]
  (= (get compra :cartao) numero))

(defn compra-por-cartao
  [numero lista]
  (filter #(mesmo-cartao? numero %) lista))

(defn total-gasto-por-mes
  [lista mes]
  (->> lista
       (compra-por-mes mes)
       (map :valor)
       (reduce +)))

(defn total-gasto-por-mes-por-cartao
  [lista mes numero]
  (->> lista
       (compra-por-mes mes)
       (compra-por-cartao numero)
       (map :valor)
       (reduce +)))

(def clientes (lista-clientes ysc.db/clientes-bruto))
(def cartoes (lista-cartoes ysc.db/cartoes-bruto))
(def compras (lista-compras ysc.db/compras-bruto))

(println (total-gasto-por-mes compras "2022-03"))
(println (total-gastos compras))
(println (total-gasto-por-mes-por-cartao compras "2022-03" 6655665566556655))