(ns yes-she-codes.main
  (:use clojure.pprint)
  (:require [yes-she-codes.db :as y.bd]))

(defn novo-cliente [nome cpf email]
  (let [clientes (y.bd/todos-clientes)]
    (def cliente {:nome nome
                   :cpf cpf
                   :email email
                   })
    (pprint (conj clientes cliente ))))

;(novo-cliente "mariana" "034" "mari@")

(defn novo-cartao [numero cvv validade limite cliente]
  (let [cartoes (y.bd/todos-cartoes)]
    (def cartao {:numero numero
                  :cvv cvv
                  :validade validade
                  :limite limite
                  :cliente cliente
                  })
    (pprint (conj cartoes cartao))))

;(novo-cartao 1234 21 "2026-08" 8.00 "034")

(defn nova-compra []
  (let [compras (y.bd/todas-compras)]
  (def compra {:data ""
               :valor ""
               :estabelecimento ""
               :categoria ""
               :cartao ""
               }))
  )

(defn lista-clientes []
  (let [clientes (y.bd/todos-clientes)]
    (pprint clientes)))

(defn lista-cartoes []
  (let [cartoes (y.bd/todos-cartoes)]
    (pprint cartoes)))

(defn lista-compras []
  (let [compras (y.bd/todas-compras)]
    (pprint compras)))

;(lista-clientes)
;(lista-cartoes)
;(lista-compras)
