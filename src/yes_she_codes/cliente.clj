(ns yes-she-codes.cliente
  (:use clojure.pprint)
  (:require [yes-she-codes.bd :as y.bd]))

(defn novo-cliente [nome cpf email]
  (let [clientes (y.bd/todos-clientes)]
    (def cliente {:nome nome
                   :cpf cpf
                   :email email
                   })
    (pprint (conj clientes cliente ))))

(novo-cliente "mariana" "034" "mari@")

