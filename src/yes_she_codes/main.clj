(ns yes-she-codes.main
  (:use clojure.pprint)
  (:require [yes-she-codes.db :as y.db]))

(defn novo-cliente [nome cpf email]
  (let [clientes (y.db/todos-clientes)]
    (pprint (conj clientes {:nome nome
                            :cpf cpf
                            :email email
                            }))))

;(novo-cliente "mariana" "034" "mari@")

(defn novo-cartao [numero cvv validade limite cliente]
  (let [cartoes (y.db/todos-cartoes)]
    (pprint (conj cartoes {:numero numero
                           :cvv cvv
                           :validade validade
                           :limite limite
                           :cliente cliente
                           }))))

;(novo-cartao 1234 21 "2026-08" 8.00 "034")

(defn nova-compra [data valor estabelecimento categoria cartao]
  (let [compras (y.db/todas-compras)]
  (conj compras {:data data
                 :valor valor
                 :estabelecimento estabelecimento
                 :categoria categoria
                 :cartao cartao
                 })))

(defn lista-clientes []
    (pprint (y.db/todos-clientes)))

(defn lista-cartoes []
    (pprint (y.db/todos-cartoes)))

(defn lista-compras []
  (pprint (y.db/todas-compras)))

;(lista-clientes)
;(lista-cartoes)
;(lista-compras)

(def lista-compras (y.db/todas-compras))

(defn total-gasto [lista-compras]
  (reduce + (map :valor lista-compras)))

;(pprint (total-gasto lista-compras))


(defn lista-compras-por-estabelecimento [estabelecimento lista-compras]
  (println "Todas as compras n@" estabelecimento)
  (get (group-by  :estabelecimento lista-compras) estabelecimento))

;(pprint (lista-compras-por-estabelecimento "Alura" lista-compras))


(defn filtro-maximo-minimo [lista-compras valormax valormin]
  (println "Compras realizadas entre R$" valormin "e R$" valormax)
  (filter #(< (:valor %) valormax) (filter #(> (:valor %) valormin) lista-compras))
  )

;(pprint (filtro-maximo-minimo lista-compras 130.0 84.0))

(defn total-categoria [compra]
  (get compra :valor 0)
  )

(defn gastos-por-categoria [lista-compras]
  ;(get compra :valor 0)
  ; (reduce + :valor )
  ;(map total-categoria lista-compras)
  (group-by :categoria lista-compras)
  ;(reduce +(map total-categoria lista-compras))

  ;(println "Todos os gastos por categoria")
  ;(reduce + (group-by :categoria lista-compras))
  ;(get lista-compras :categoria)
  )

;(pprint (gastos-por-categoria lista-compras))



