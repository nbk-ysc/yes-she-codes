(ns yes-she-codes.logic.logic
  (:require [yes-she-codes.model.model :as m]
            [yes-she-codes.model.data :as d])
  (:use [clojure.pprint])
  )

;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;Criar a função total-gasto, que recebe uma lista de compras e retorna a soma dos valores gastos.
;
;Exemplo:
;
;compra 1: R$ 100,00
;compra 2: R$ 250,00
;compra 3: R$ 400,00
;TOTAL: R$ 750,00

(defn total-gasto
  [lista-compras]
  (reduce + (map :valor lista-compras)))




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;Criar uma função que, dado um mês e uma lista de compras, retorne uma lista de compras feitas somente naquele mês.

(defn- mes-da-data
  [data]
  (second (re-seq #"[0-9]+" data))
  )

(defn lista-de-compras-do-mes
  [mes lista-compras]
  (filter #(= mes (mes-da-data (:data %))) lista-compras)
  )




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;Criar uma função que, dado um estabelecimento e uma lista de compras, retorne uma lista de compras feitas somente naquele estabelecimento.


(defn lista-de-compras-do-estabelecimento
  [estabelecimento lista-compras]
  (filter #(= estabelecimento (:estabelecimento %)) lista-compras)
  )




;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;;
;Criar a função total-gasto-no-mes, que calcule a soma dos valores gastos num determinado cartão em um mês.
;Para facilitar, considere que todas as compras consideradas sejam de um mesmo cartão.


(defn total-gasto-no-mes
  [mes lista-compras]
  (let [lista-compras-mes (lista-de-compras-do-mes mes lista-compras)]
    (total-gasto lista-compras-mes)
    )
  )



