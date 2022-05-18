(ns yes-she-codes.logic
  (:require [yes-she-codes.db :as y.db])
  (:require [clojure.string :as str]))

; recebe o array menor desestruturado e devolve os dados dos clientes;

(defn novo-cliente [nome, cpf, email]
  {:nome  nome
   :cpf   cpf
   :email email})

; recebe o array quebrado; e passa os dados para novo-cliente.
(defn transforma-cliente [[nome, cpf, email]]
  ;(println "nome" nome)
  ;(println "cpf" cpf)
  ;(println "email" email)
  ;(println "terminei")
  (novo-cliente nome, cpf, email))

; recebe o array, que é o meu banco de dados com todos os clientes. Quebra o array através do map
(defn transforma-clientes [array]
  ;(println "Array, pare 01\n" array)
  (map transforma-cliente array))

(defn lista-clientes []
  (transforma-clientes (y.db/busca-registros-de-clientes)))

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

(defn nova-compra [data, valor, estabelecimento, categoria, cartao]
  {:data            data,
   :valor           valor,
   :estabelecimento estabelecimento,
   :categoria       categoria,
   :cartao          cartao})

(defn transforma-compra [[data, valor, estabelecimento, categoria, cartao]]
  (nova-compra data, valor, estabelecimento, categoria, cartao))

(defn transforma-compras [array]
  (map transforma-compra array))

(defn lista-compras []
  (transforma-compras (y.db/busca-registros-de-compras)))

(println "\n=================================")             ;fiz essa etapa para somar todos os valores de todos os cartão

(defn compra [x y]
  (+ x (:valor y)))

(defn total-gasto [lista-de-compras]
  (reduce compra 0 lista-de-compras))

(println "Valor total das compras de todos os cartões" (total-gasto (lista-compras)))

(println "\n=================================")             ;fiz essa etapa para encontrar o mes

(defn split-data [data]
  (str/split data #"-"))

(defn get-mes [data]
  (get (split-data data) 1))

(defn get-mes-da-compra [compra]
  (get-mes (:data compra)))

(defn compra-realizada-no-mes? [mes compra]
  (= mes (get-mes-da-compra compra)))

(defn compras-no-mes [mes lista-de-compras]
  (filter #(compra-realizada-no-mes? mes %) lista-de-compras))

(println "achei o mes 04?" (compras-no-mes "04" (lista-compras)))

(println "\n=================================")             ;fiz essa etapa para somar valores de um mes da lista

;(defn total-gasto-no-mes [mes lista-de-compras-no-mes]
;  (total-gasto (compras-no-mes mes lista-de-compras-no-mes)))

(def total-gasto-no-mes (comp total-gasto compras-no-mes))
(println "Valor total das compras de um mes" (total-gasto-no-mes "04" (lista-compras)))

(println "\n=================================")             ;fiz essa etapa para encontrar o estabelecimento

(defn achei-o-estabelecimento? [local todas-as-compras]
  (= local (:estabelecimento todas-as-compras)))

(defn comprei-no-estabelecimento? [local lista-de-compras]
  (filter #(achei-o-estabelecimento? local %) lista-de-compras))

(println "achei o estabelecimento?" (comprei-no-estabelecimento? "Alura" (lista-compras)))

(println "\n=================================")             ;fiz essa etapa para encontrar o cartão dentro da lista de compras

(defn achei-o-cartao-na-lista-de-compras? [cartao todas-as-compras]
  (= cartao (:cartao todas-as-compras)))

(defn comprei-no-cartao? [cartao lista-de-cartoes]
  (filter #(achei-o-cartao-na-lista-de-compras? cartao %) lista-de-cartoes))

(comprei-no-cartao? 1234123412341234 (lista-compras))

;(println "achei algum cartao?" (comprei-no-cartao? 1234123412341234 (lista-compras)))

(println "\n=================================")             ;fiz essa etapa para somar valores de um o cartão na lista

;(defn total-gasto-no-cartao [cartao lista-de-compras-no-cartao]
;  (total-gasto (comprei-no-cartao? cartao lista-de-compras-no-cartao)))

(def total-gasto-no-cartao (comp total-gasto comprei-no-cartao?))

(println "Valor total das compras de um cartao" (total-gasto-no-cartao 1234123412341234 (lista-compras)))
